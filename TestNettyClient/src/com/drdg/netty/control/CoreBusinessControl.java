package com.drdg.netty.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.channel.socket.SocketChannel;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.client.NettyClient;
import com.drdg.netty.thread.ClientThreadPool;
import com.drdg.netty.view.GroupChat;
import com.drdg.netty.view.MM;

public class CoreBusinessControl {

	private NettyClient nettyClient;
	private String inetHost = "127.0.0.1";
	private int inetPort = 7397;
	private MM mm;
	private GroupChat groupChat;
	private MsgAgreement msgAgree = new MsgAgreement(true);
	public static SocketChannel socketChannel;
	private ExecutorService es = Executors.newCachedThreadPool();// 线程池
	private ClientThreadPool clientThread;

	private CoreBusinessControl() {
	};

	public CoreBusinessControl(MM mm) {
		nettyClient = new NettyClient();
		this.mm = mm;
	}

	/**
	 * 校验登录
	 */
	public void doCheckLogin() {

		doConnectServer();

		// 切换界面
		mm.dispose();
		groupChat = new GroupChat();

	}

	/**
	 * 连接服务端
	 * 
	 * @return
	 */
	public void doConnectServer() {
		clientThread = new ClientThreadPool();
		es.execute(clientThread);
	}

	/**
	 * 通过用户信息进行登录
	 * 
	 * @return
	 */
	public void doLoginByUserMsg() {
		// 连接服务端后组织信息发送
		socketChannel.writeAndFlush(msgAgree.doGetLoginInfoPacket("54235045",
				"Qq12345."));
	}

	/**
	 * 开启聊天界面
	 */
	public void doOpenChatView() {
		mm.dispose();
		groupChat = new GroupChat();
	}

}
