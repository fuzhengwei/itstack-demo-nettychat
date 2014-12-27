package com.drdg.netty.control;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.channel.socket.SocketChannel;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.bean.InformationPacket.MsgInfo;
import com.drdg.netty.bean.UserBean;
import com.drdg.netty.bean.InformationPacket.Login;
import com.drdg.netty.bean.InformationPacket.Group.User;
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
	}
	
	/**
	 * 
	 * @param infoMsg
	 */
	public void doSendMsg(String msgStr){
		
		MsgHandleService.doSendMsgStr(userBean.getUserName(), msgStr);
	}
	
	/**
	 * 
	 * @param login
	 */
	public void doChangeGroupChatView(Login login){
		mm.dispose();
		groupChat = new GroupChat();
	}

	/**
	 * 
	 * @param userListList
	 */
	public void doRefreshFriendList(List<User> userListList){
		groupChat.refreshFriendsList(userListList);
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
	 * 开启聊天界面
	 */
	public void doOpenChatView() {
		mm.dispose();
		groupChat = new GroupChat();
	}

	/**
	 * 
	 * @param msgInfo
	 */
	public void doReceivedMsgInfo(MsgInfo msgInfo) {
		groupChat.refreshReceivedMsg(msgInfo);
	}

}
