package com.ll.queue.disruptorSystem;

/**
 * @author Lei
 * @date 2023/3/23 18:45
 */
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
//EventHandler用于EventHandlerGroup，WorkHandler用于WorkPool。同时实现两接口，该类对象可同时用于EventHandlerGroup和WorkPool
public class OrderHandler implements EventHandler<Order>, WorkHandler<Order> {
    private String consumerId;
    public OrderHandler(String consumerId){
        this.consumerId = consumerId;
    }
    //EventHandler的方法
    @Override
    public void onEvent(Order order, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("OrderHandler1 " + this.consumerId + "，消费信息：" + order.getId());
    }
    //WorkHandler的方法
    @Override
    public void onEvent(Order order) throws Exception {
        System.out.println("OrderHandler1 " + this.consumerId + "，消费信息：" + order.getId());
    }
}
