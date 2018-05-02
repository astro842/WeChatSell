package com.astro.dataobject;

import com.astro.Util.EnumUtil;
import com.astro.enums.ProductStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by astro on 2017/10/24.
 */
@Entity
@Data
@DynamicUpdate
public class BuyerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String openid;
    private String nickName;
    private String city;
    private String headImgUrl;
    private Date createTime;



}
