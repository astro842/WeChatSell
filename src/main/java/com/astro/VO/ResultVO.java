package com.astro.VO;

import lombok.Data;

/**
 * Created by astro on 2017/10/24.
 */
@Data
public class ResultVO<T> {

    private Integer code;
    private String msg;
    private T data;

}
