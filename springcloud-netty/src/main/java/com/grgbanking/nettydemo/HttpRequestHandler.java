package com.grgbanking.nettydemo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HttpRequestHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s){
        System.out.println("客户端发来的信息:" + s);
        channelHandlerContext.writeAndFlush("服务器端返回的信息:" + s.toUpperCase());
        Channel channel = channelHandlerContext.channel();
        if(channel.isActive())channelHandlerContext.close();
    }
}
