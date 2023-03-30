package com.ll.UnsafeStu;

import sun.misc.Unsafe;

/**
 * @author Lei
 * @date 2023/3/29 11:34
 */
public class Instance {
    class A {
        private int a;
        private int b;
        public int getA() {
            return a;
        }
        public int getB() {
            return b;
        }
    }
    public static void main(String[] args) {
        Unsafe unsafe = BaseGet.getUnsafe();
        try {
            A a = (A)unsafe.allocateInstance(A.class);
            System.out.println(a.getA());
            System.out.println(a.getB());
            long offsetA = unsafe.objectFieldOffset(A.class.getDeclaredField("a"));
            long offsetB =  unsafe.objectFieldOffset(A.class.getDeclaredField("b"));
            unsafe.putInt(a, offsetA, 2);
            unsafe.putInt(a, offsetB, 3);
            System.out.println(a.getA() + " " + unsafe.getInt(a, offsetA));
            System.out.println(a.getB() + " " + unsafe.getInt(a, offsetB));
            System.out.println(unsafe.getInt(a, 1));
        } catch (NoSuchFieldException noSuchFieldException){
            noSuchFieldException.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
