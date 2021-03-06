package com.grgbanking.helloNetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.boot.convert.Delimiter;

public class MyServerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast("framer",new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
        channelPipeline.addLast("decoder",new StringDecoder());
        channelPipeline.addLast("encoder",new StringEncoder());
        channelPipeline.addLast("handler",new MyHandler());
    }
}
