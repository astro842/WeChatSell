package com.astro.Util;

import java.util.Random;

public class KeyUtil {

    public static synchronized  String genUniqueKey(){
        Random random=new Random();
        //六位随机数
        Integer number =random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);

    }


}
