package com.drdg.netty.server;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.bean.InformationPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	//群发
	ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void initChannel(SocketChannel sc) throws Exception {

		System.out.println("服务端开启... ...");
		
		sc.pipeline().addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
		sc.pipeline().addLast("protobufDecoder", new ProtobufDecoder(InformationPacket.Group.getDefaultInstance()));
		sc.pipeline().addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
		sc.pipeline().addLast("protobufEncoder", new ProtobufEncoder());
		sc.pipeline().addLast(new TimeServerHandler());
		
		channelGroup.add(sc);

		//客户端连接
		System.out.println("客户端连接");
		System.out.println(sc.id());
		
		//发送连接成功包给客户端
		MsgAgreement msgAgreement = new MsgAgreement(true);
		sc.writeAndFlush(msgAgreement.doGetConnectServerInfoPacket(InformationPacket.Group.ServerConnectEnum.Success));
		System.out.println("向客户端发送连接消息包");
	}

}
