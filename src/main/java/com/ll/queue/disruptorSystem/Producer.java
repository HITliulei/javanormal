package com.ll.queue.disruptorSystem;

import com.lmax.disruptor.RingBuffer;

/**
 * @author Lei
 * @date 2023/3/23 18:44
 */
public class Producer {
    private final RingBuffer<Order> ringBuffer;
    public Producer(RingBuffer<Order> ringBuffer){
        this.ringBuffer = ringBuffer;
    }
    public void onData(String data){
        long sequence = ringBuffer.next();
        try {
            Order order = ringBuffer.get(sequence);
            order.setId(data);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
