package com.ll.queue;


import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * @author Lei
 * @date 2023/3/23 18:16
 */
public class DistuporTest {
    static class Element {

        private int value;

        public int get(){
            return value;
        }

        public void set(int value){
            this.value= value;
        }

    }
    public static void main(String[] args) throws InterruptedException {
        // 队列中的元素


        // 还声明了一个EventFactory来创建Event对象
        EventFactory<Element> factory = new EventFactory<Element>() {
            @Override
            public Element newInstance() {
                return new Element();
            }
        };

        /**
         * 类似于消费者
         *  disruptor会回调此处理器的方法
         */
        EventHandler<Element> handler = new EventHandler<Element>(){
            @Override
            public void onEvent(Element element, long sequence, boolean endOfBatch) {
                try {
                    System.out.println("准备消费");
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                System.out.println("Element: " + element.get());
            }
        };

        // 生产者的线程工厂
        ThreadFactory threadFactory = new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "simpleThread");
            }
        };




        // 阻塞策略
        BlockingWaitStrategy strategy = new BlockingWaitStrategy();

        // 指定RingBuffer的大小
        int bufferSize = 16;

        // 创建disruptor，采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.SINGLE, strategy);

        // 连接 消费者 处理器
        disruptor.handleEventsWith(handler);

//        disruptor.handleEventsWith(LongEventDemo::handleEvent);

        // 启动disruptor的线程
        disruptor.start();


        // 获取环形队列，用于生产 事件
        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        for (int l = 0; true; l++)
        {
            // 1. 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 2. 返回可用位置的元素
                Element event = ringBuffer.get(sequence);
                // 3. 设置该位置元素的值
                event.set(l);
            }
            finally{
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
        }
    }


    //Disruptor3.0以后 , 提供了事件转换器， 帮助填充 LongEvent 的业务数据
    static class LongEventProducerWithTranslator {
        //一个translator可以看做一个事件初始化器，publicEvent方法会调用它
        //填充Event
        private static final EventTranslatorOneArg<Element, Integer> TRANSLATOR =
                new EventTranslatorOneArg<Element, Integer>() {
                    @Override
                    public void translateTo(Element event, long sequence, Integer data) {
                        event.set(data);
                    }
                };

        private final RingBuffer<Element> ringBuffer;

        public LongEventProducerWithTranslator(RingBuffer<Element> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void onData(Integer data) {
            ringBuffer.publishEvent(TRANSLATOR, data);
        }
    }

}
