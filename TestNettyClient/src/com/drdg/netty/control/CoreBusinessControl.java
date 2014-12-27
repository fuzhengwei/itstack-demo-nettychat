package com.drdg.netty.control;

import com.drdg.netty.client.NettyClient;
import com.drdg.netty.view.GroupChat;
import com.drdg.netty.view.MM;

public class CoreBusinessControl {

	private NettyClient nettyClient;
	private String inetHost = "127.0.0.1";
	private int inetPort = 8080;
	private MM mm;
	private GroupChat groupChat;
	
	private CoreBusinessControl(){};
	
	public CoreBusinessControl(MM mm){
		nettyClient = new NettyClient();
		this.mm = mm;
	}
	
	/**
	 * 连接服务端
	 * @return
	 */
	public boolean doConnectServer(){
		
		try {
			nettyClient.connect(inetPort, inetHost);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 开启聊天界面
	 */
	public void doOpenChatView(){
		mm.dispose();
		
		groupChat = new GroupChat();
		
	}
	
}
