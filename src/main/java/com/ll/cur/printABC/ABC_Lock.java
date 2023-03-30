package com.ll.cur.printABC;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/13
 */
public class ABC_Lock {
    private static ReentrantLock lock = new ReentrantLock();

    private static int state = 0;

    static class ThreadA extends Thread {
        @Override
        public void run(){
            for (int i = 0; i < 10;) {
                lock.lock();
                try {
                    while (state % 3 == 0) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("A");
                        state++;
                        i++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run(){
            for (int i = 0; i < 10;) {
                lock.lock();
                try {
                    while (state % 3 == 1) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("B");
                        state++;
                        i++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run(){
            for (int i = 0; i < 10;) {
                lock.lock();
                try {
                    while (state % 3 == 2) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("C");
                        state++;
                        i++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }


    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
