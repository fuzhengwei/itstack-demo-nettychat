package com.drdg.netty.client;

import com.drdg.netty.bean.InformationPacket;
import com.drdg.netty.control.CoreBusinessControl;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel sc) throws Exception {

		sc.pipeline().addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
		sc.pipeline().addLast("protobufDecoder", new ProtobufDecoder(InformationPacket.Group.getDefaultInstance()));
		sc.pipeline().addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
		sc.pipeline().addLast("protobufEncoder", new ProtobufEncoder());
		sc.pipeline().addLast("handler", new TimeClientHandler());
		
		CoreBusinessControl.socketChannel = sc;
	}

}
