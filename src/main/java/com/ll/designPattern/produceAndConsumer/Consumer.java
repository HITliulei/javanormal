package com.ll.designPattern.produceAndConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/15
 */
public class Consumer implements Runnable{

    private final BlockingQueue sharedQueue;

    public Consumer(BlockingQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        for (int i = 1;i <= 10;i++){
            try {
                System.out.println("消费：" + sharedQueue.take());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
