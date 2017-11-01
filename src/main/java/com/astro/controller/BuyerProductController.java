package com.astro.controller;

import com.astro.Util.ResultVOUtil;
import com.astro.VO.ProductInfoVO;
import com.astro.VO.ProductVO;
import com.astro.VO.ResultVO;
import com.astro.dataobject.ProductCategory;
import com.astro.dataobject.ProductInfo;
import com.astro.service.CategoryService;
import com.astro.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList=productService.findUpAll();

        //2.查询类目
        List<Integer> categoryTypeList=new ArrayList<>();
        for (ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }

        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼接
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());


            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
//        ResultVO resultVO=new ResultVO();
//        resultVO.setCode(100);
//        resultVO.setMsg("成功");
//        resultVO.setData(productVOList);
        return ResultVOUtil.success(productVOList);


    }

}

