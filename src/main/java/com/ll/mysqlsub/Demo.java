package com.ll.mysqlsub;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author Lei
 * @date 2023/3/31 17:34
 */
public class Demo {

    // 系统缓存
    SocketChannel socketChannel;


    // 用户态 缓存
    private ByteBuffer sendBuffer;
    private ByteBuffer receiveBuffer;


    private Selector selector;

    public Demo(){
        try {
            // 连接 mysql
            socketChannel = SocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress( 8081));
            socketChannel.socket().connect(new InetSocketAddress("127.0.0.1", 3306));
            socketChannel.configureBlocking(false);
            selector= Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendBuffer = ByteBuffer.allocate(1024);
        receiveBuffer = ByteBuffer.allocate(1024);
    }


    public static void main(String[] args) {

    }

}
