package com.ll.designPattern.produceAndConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/15
 */
public class Produce implements Runnable{
    private final BlockingQueue sharedQueue;

    public Produce(BlockingQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }


    @Override
    public void run() {
        for (int i = 1;i <= 10;i++){
            try {
                System.out.println("生产：" + i);
                sharedQueue.put(i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
