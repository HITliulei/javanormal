package com.ll.cur;

import javax.jws.Oneway;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/12
 */
public class PrintABC {


    public static class ThreadPrinter implements Runnable{

        private String name;
        private Object pre;
        private Object now;


        public ThreadPrinter(String name, Object pre, Object now){
            this.name = name;
            this.pre = pre;
            this.now = now;
        }
        @Override
        public void run(){
            int count = 10;
            while (count>0){
                synchronized(pre){
                    synchronized (now){
                        System.out.println(name);
                        count--;
                        now.notifyAll();
                    }
                    try {
                        if (count == 0){
                            pre.notifyAll();
                        }else{
                            pre.wait();
                        }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object A = new Object();
        Object B = new Object();
        Object C = new Object();

        new Thread(new ThreadPrinter("A", C, A)).start();
        Thread.sleep(10);
        new Thread(new ThreadPrinter("B", A, B)).start();
        Thread.sleep(10);
        new Thread(new ThreadPrinter("C", B, C)).start();

    }
}
