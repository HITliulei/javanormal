package com.ll.queue.disruptorSystem.demo;

import com.ll.queue.disruptorSystem.Order;
import com.ll.queue.disruptorSystem.OrderHandler;
import com.ll.queue.disruptorSystem.Producer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.concurrent.Executors;

/**
 * 单生产者，多消费者模式。多消费者对于消息不重复消费。
 * @author Lei
 * @date 2023/3/23 19:11
 */
public class OnePNCandNoCompeat {
    public static void main(String[] args) throws Exception {
        int ringBufferSize = 1024 * 1024;
        Disruptor<Order> disruptor =
                new Disruptor<Order>(Order::new, ringBufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new YieldingWaitStrategy());
        /*
         * 该方法传入的消费者需要实现WorkHandler接口，方法的内部实现是：先创建WorkPool，然后封装WorkPool为EventHandlerPool返回。
         * 消费者1、2对于消息的消费有时有竞争，保证同一消息只能有一个消费者消费
         */
        disruptor.handleEventsWithWorkerPool(new OrderHandler("1"), new OrderHandler("2"));
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
        //调用handleEventsWithWorkerPool形成WorkerPool，并进一步封装成EventHandlerGroup。对于同一条消息，两消费者不重复消费。
    }

}
