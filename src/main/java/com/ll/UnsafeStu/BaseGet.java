package com.ll.UnsafeStu;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Lei
 * @date 2023/3/29 10:36
 */
public class BaseGet {

    public static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
