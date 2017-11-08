package com.astro.exception;

import com.astro.enums.ResultEnum;

/**
 * Created by astro on 2017/10/31.
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String msg){
        super(msg);
        this.code=code;

    }
}
