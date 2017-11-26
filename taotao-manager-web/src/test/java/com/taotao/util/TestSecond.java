package com.taotao.util;

import org.junit.Test;

/**
 * Created by ASUS on 2017/11/1.
 */
public class TestSecond {
    @Test
    public void testSecond(){

        for (int i = 0;i<100;i++){
            long currentTimeMillis = System.currentTimeMillis();
            System.out.println(currentTimeMillis);
        }
    }
}
