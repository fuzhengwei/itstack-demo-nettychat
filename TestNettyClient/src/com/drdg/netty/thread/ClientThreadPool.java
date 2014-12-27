package com.drdg.netty.thread;

import com.drdg.netty.client.NettyClient;

public class ClientThreadPool implements Runnable{
	
	public void run() {

		String inetHost = "127.0.0.1";
		int inetPort = 7397;
		
		NettyClient nc = new NettyClient();
		try {
			nc.connect(inetPort, inetHost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
