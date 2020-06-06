package com.grgbanking.nettydemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class HelloClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(9999));

        ByteBuffer byteBuffer = ByteBuffer.allocate(102400);

        byteBuffer.put("vsvfsdv".getBytes());

        socketChannel.write(byteBuffer);

        socketChannel.close();
    }
}
