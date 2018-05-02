package com.astro.repository;

import com.astro.dataobject.BuyerCar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by astro on 2018/4/12.
 */
@Mapper
public interface BuyerCarDao {

    @Select("select c.id,c.openid,c.product_id,p.product_name,p.product_icon as product_img,c.product_num,c.state from car c,product_info p \n" +
            "where c.state = 0 and c.openid = #{openId} and p.product_id=c.product_id and c.state=0")
   List<BuyerCar> getBuyerCarByOpenid(String openId);

//    select c.id,c.openid,c.product_id,p.product_name,p.product_icon as product_img,c.product_num,c.state from car c,product_info p
//    where c.state = 0 and c.openid = "111" and p.product_id=c.product_id

    @Insert("insert into car (openid,product_id,product_num,state) " +
            "values(#{openid,jdbcType=VARCHAR},#{productId,jdbcType=VARCHAR}" +
            ",#{productNum,jdbcType=VARCHAR},#{state,jdbcType=INTEGER})")
    int addBuyerCar(BuyerCar buyerCar);

    @Update("update car set state=1 where openid= #{openId} and state = 0")
    int updateCarState(String openId);



}
