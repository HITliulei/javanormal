package com.ll.queue.disruptorSystem.demo;

/**
 * @author Lei
 * @date 2023/3/23 19:09
 */
import com.ll.queue.disruptorSystem.Order;
import com.ll.queue.disruptorSystem.OrderHandler;
import com.ll.queue.disruptorSystem.Producer;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.concurrent.Executors;
public class OnePNCandDepend {
    //单生产者，多消费者，但多消费者间形成依赖关系，每个依赖节点单线程。
    public static void main(String[] args) throws Exception {
        int ringBufferSize = 1024 * 1024;
        Disruptor<Order> disruptor =
                new Disruptor<Order>(Order::new, ringBufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new YieldingWaitStrategy());
        //多个消费者间形成依赖关系，每个依赖节点的消费者为单线程。
        disruptor.handleEventsWith(new OrderHandler("1")).then(new OrderHandler("2"), new OrderHandler("3")).then(new OrderHandler("4"));
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
    //消费者C2、C3只有在C1消费完消息m后，才能消费m。消费者C4只有在C2、C3消费完m后，才能消费该消息。
}