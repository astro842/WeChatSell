package com.astro.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class MyForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

}
