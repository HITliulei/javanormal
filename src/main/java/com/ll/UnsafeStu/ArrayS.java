package com.ll.UnsafeStu;

import sun.misc.Unsafe;

/**
 * @author Lei
 * @date 2023/3/29 10:36
 */
public class ArrayS {
    private static String[] names = {"多线程", "Java", "并发编程"};


    public static void main(String[] args) {
        Unsafe unsafe = BaseGet.getUnsafe();
        Class<?> a = String[].class;
        int base = unsafe.arrayBaseOffset(a);
        int scale = unsafe.arrayIndexScale(a);
        System.out.println(base);
        System.out.println(scale);
        // base + i * scale 即为字符串数组下标 i 在对象的内存中的偏移地址
        System.out.println(unsafe.getObject(names, (long) base + 2 * scale));
    }
}
