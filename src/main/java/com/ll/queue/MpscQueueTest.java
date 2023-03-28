package com.ll.queue;

import org.jctools.queues.MpscArrayQueue;
import org.jctools.queues.MpscUnboundedArrayQueue;

import static org.jctools.util.UnsafeAccess.UNSAFE;

/**
 * @author Lei
 * @date 2023/3/27 17:13
 */

public class MpscQueueTest {


    public static void main(String[] args) {
        MpscUnboundedArrayQueue mpscUnboundedArrayQueue = new MpscUnboundedArrayQueue(4);
        mpscUnboundedArrayQueue.offer("2");
        System.out.println(mpscUnboundedArrayQueue.size());
        mpscUnboundedArrayQueue.poll();
    }
}
