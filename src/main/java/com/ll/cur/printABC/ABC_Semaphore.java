package com.ll.cur.printABC;

import java.util.concurrent.Semaphore;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/13
 */
public class ABC_Semaphore {


    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);


    static class ThreadA extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoreA.acquire(); // A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                semaphoreB.release();
            }
        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoreB.acquire(); // A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                semaphoreC.release();
            }
        }
    }

    static class ThreadC extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoreC.acquire(); // A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
                semaphoreA.release();
            }
        }
    }


    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
