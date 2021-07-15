package com.ll.designPattern.produceAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/15
 */
public class Main {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingQueue();

        Thread pThread = new Thread(new Produce(blockingQueue));
        Thread cThread = new Thread(new Consumer(blockingQueue));

        pThread.start();
        System.out.println("=============");
        cThread.start();

    }
}
