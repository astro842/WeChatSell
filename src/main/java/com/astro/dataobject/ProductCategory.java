package com.astro.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by astro on 2017/10/23.
 */
@Entity
@Data
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    public  ProductCategory(){}

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName=" + categoryName +
                ", categoryType=" + categoryType +
                '}';
    }
}
