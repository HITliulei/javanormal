package com.ll.UnsafeStu;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

/**
 * @author Lei
 * @date 2023/3/30 11:10
 */

public class ThreadUnsafe {

    private static Thread mainThread;

    public void testPark() throws Exception {
        Unsafe unsafe = BaseGet.getUnsafe();
        mainThread = Thread.currentThread();

        System.out.println(String.format("park %s", mainThread.getName()));
        unsafe.park(false, TimeUnit.SECONDS.toNanos(3));

        new Thread(() -> {
            System.out.println(String.format("%s unpark %s", Thread.currentThread().getName(),
                    mainThread.getName()));
            unsafe.unpark(mainThread);
        }).start();
        System.out.println("main thread is done");

    }

    public static void main(String[] args) throws Exception {
        ThreadUnsafe testUsafe = new ThreadUnsafe();
        testUsafe.testPark();
    }
}
