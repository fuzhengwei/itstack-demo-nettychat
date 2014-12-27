package com.drdg.netty.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.channel.socket.SocketChannel;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.bean.UserBean;
import com.drdg.netty.client.NettyClient;
import com.drdg.netty.service.MsgHandleService;
import com.drdg.netty.thread.ClientThreadPool;
import com.drdg.netty.view.GroupChat;
import com.drdg.netty.view.MM;

public class CoreBusinessControl {

	private MM mm;
	private GroupChat groupChat;
	private ExecutorService es = Executors.newCachedThreadPool();// 线程池
	private ClientThreadPool clientThread;
	private UserBean userBean;
	
	private CoreBusinessControl() {
	};

	public CoreBusinessControl(MM mm) {
		this.mm = mm;
	}

	/**
	 * 校验登录
	 */
	public void doCheckConnectLogin(String userName,String userPwd) {
		
		doConnectServer();

		userBean = new UserBean();
		userBean.setUserName(userName);
		userBean.setUserPwd(userPwd);
		
	}
	
	public void doCheckLogin(){
		
		MsgHandleService.doCheckLogin(userBean);
		
		// 切换界面
//		mm.dispose();
//		groupChat = new GroupChat();
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
	}

	/**
	 * 开启聊天界面
	 */
	public void doOpenChatView() {
		mm.dispose();
		groupChat = new GroupChat();
	}

}
