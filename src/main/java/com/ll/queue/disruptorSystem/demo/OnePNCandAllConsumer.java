package com.ll.queue.disruptorSystem.demo;

import com.ll.queue.disruptorSystem.Order;
import com.ll.queue.disruptorSystem.OrderHandler;
import com.ll.queue.disruptorSystem.Producer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * 单生产者多消费者，多消费者对于消息m独立消费。
 * @author Lei
 * @date 2023/3/23 19:13
 */
public class OnePNCandAllConsumer {

    public static void main(String[] args) throws Exception {
        int ringBufferSize = 1024 * 1024;
        Disruptor<Order> disruptor =
                new Disruptor<Order>(Order::new, ringBufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new YieldingWaitStrategy());
        //多个消费者间形成依赖关系，每个依赖节点的消费者为单线程。
        disruptor.handleEventsWith(new OrderHandler("1"),new OrderHandler("2"));
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
