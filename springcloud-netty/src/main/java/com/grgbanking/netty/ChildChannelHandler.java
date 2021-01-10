package com.grgbanking.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    private static int count = 0;

    @Resource
    private DiscardServerHandler discardServerHandler;

    public void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("------------输出连接数量------------");
        System.out.println(count);
        count++;
        socketChannel.pipeline().addLast(discardServerHandler);
    }
}
