package com.grgbanking.helloNetty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestNio {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8888));
        socketChannel.write(ByteBuffer.wrap("mymymmy".getBytes()));
    }
}
