package com.astro.dataobject;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * Created by astro on 2018/4/4.
 */
@Mapper
public interface ProductMapper {

    //@Select("select pi.product_id,pi.product_name,pi.product_price,pi.product_description,pi.product_icon from product_info pi,product_category pc where pc.category_type = pi.category_type and pc.category_name = #{cName}")
   //@Select("select pi.* from product_info pi,product_category pc where pc.category_type = pi.category_type and pc.category_name = #{cName}")
   @Select("select pi.* from product_info pi,product_category pc where pc.category_type = pi.category_type and pc.category_name = #{cName}")
   List<ProductInfo> selectByCategory(@Param("cName") String cName);


}
