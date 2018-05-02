package com.astro.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by astro on 2018/4/12.
 */

@Entity
@Data
@DynamicUpdate
public class BuyerCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String openid;
    private String productId;
    private String productNum;
    private String productName;
    private String productImg;
    private Integer state; //0 未支付  1：已完成
    private Date createTime;

}
