package com.ll.cur.printABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/13
 *
 * private Lock lock = new ReentrantLock();
 * private Condition condition = lock.newCondition();
 * condition.await();//this.wait();
 * condition.signal();//this.notify();
 * condition.signalAll();//this.notifyAll();
 *
 */
public class ABC_Lock_Condition {
    private static Lock lock = new ReentrantLock();
    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();


    private static int count = 0;

    static class ThreadA extends Thread {
        @Override
        public void run(){
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 0){
                        A.await();
                    }
                    System.out.print("A");
                    count++;
                    B.signal();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run(){
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 1){
                        B.await();
                    }
                    System.out.print("B");
                    count++;
                    C.signal();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    static class ThreadC extends Thread {
        @Override
        public void run(){
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2){
                        C.await();
                    }
                    System.out.print("C");
                    count++;
                    A.signal();// C执行完唤醒A线程
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }

}
