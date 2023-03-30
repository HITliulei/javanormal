package com.ll.queue.disruptorSystem.demo;

import com.ll.queue.disruptorSystem.Order;
import com.ll.queue.disruptorSystem.OrderHandler;
import com.ll.queue.disruptorSystem.Producer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Lei
 * @date 2023/3/23 18:46
 */
public class OnePOneC {


    //单生产者模式，单消费者模式
    //
    public static void main(String[] args) throws Exception {
        Executor executor = Executors.newCachedThreadPool();
        Disruptor<Order> disruptor = new Disruptor<Order>(Order::new, 1024, executor);
        disruptor.handleEventsWith(new OrderHandler("1"));
        disruptor.start();
        RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        //单生产者，生产3条数据
        for (int l = 0; l < 3; l++) {
            producer.onData(l + "");
        }
        //为了保证消费者线程已经启动，留足足够的时间。具体原因详见另一篇博客：disruptor的shutdown失效问题
        Thread.sleep(1000);
        disruptor.shutdown();

    }
}
