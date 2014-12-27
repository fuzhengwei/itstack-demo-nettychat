package com.drdg.netty.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.drdg.netty.bean.InformationPacket.Group.User;

public class GroupChat extends JFrame implements ActionListener,MouseListener,MouseMotionListener{

	public static void main(String[] args) {
		new GroupChat();
	}
	
	
    public void refreshFriendsList(List<User> userList){
		
    	System.out.println(userList.size());
    	
    	for (User user : userList) {
    		
    		System.out.println(user.getId()+" "+user.getUserName());
    		
    		jlFriend = new JLabel(user.getId()+" "+user.getUserName());
    		jlFriend.addMouseListener(this);
    		
    		jpFl.add(jlFriend);
    		jpFl.updateUI();
    		jpFl.invalidate(); 
    		jpFl.validate();
    		jpFl.repaint();
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == jpBack){
			// 当鼠标按下的时候获得窗口当前的位置
			origin.x = e.getX();
			origin.y = e.getY();
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getSource() == jpBack){
			Point p = this.getLocation();
			this.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public GroupChat(){
		//背景设置
		jpBack = new JPanel(null);
		jpBack.setSize(600, 500);
		jpBack.setLocation(0, 0);
		jpBack.setBackground(Color.gray);
		jpBack.addMouseListener(this);
		jpBack.addMouseMotionListener(this);
		this.add(jpBack);
		
		//顶部菜单
		jpTopMenu = new JPanel(null);
		jpTopMenu.setSize(600, 30);
		jpTopMenu.setLocation(0, 0);
		jpTopMenu.setBackground(Color.DARK_GRAY);
		jpBack.add(jpTopMenu);
		
		jlMini = new JLabel("Mini");
		jlMini.setForeground(Color.white);
		jlMini.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jlMini.setSize(25, 20);
		jlMini.setLocation(538, 10);
		jpTopMenu.add(jlMini);
		
		jlExit = new JLabel("Exit");
		jlExit.setForeground(Color.red);
		jlExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jlExit.setSize(25, 20);
		jlExit.setLocation(568, 10);
		jpTopMenu.add(jlExit);
		
		//聊天面板
		jtpChat = new JTextPane();
		jspChat = new JScrollPane(jtpChat);
		jspChat.setSize(400, 320);
		jspChat.setLocation(10, 40);
		jpBack.add(jspChat);
		
		//功能栏
		jpFunction = new JPanel(null);
		jpFunction.setSize(400, 30);
		jpFunction.setLocation(10, 370);
		jpBack.add(jpFunction);
		
		//聊天信息输入
		jtpInChat = new JTextPane();
		jtpInChat.setSize(400, 80);
		jtpInChat.setLocation(10, 410);
		jpBack.add(jtpInChat);
		
		//好友列表
		jpFriendList = new JPanel(null);
		jpFriendList.setSize(170, 450);
		jpFriendList.setLocation(420, 40);
		jpBack.add(jpFriendList);
		
		jpFl = new JPanel(new GridLayout(20,1));
		jspFl = new JScrollPane(jpFl);
		jspFl.setSize(168, 420);
		jspFl.setLocation(2, 30);
		jpFriendList.add(jspFl);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(Color.blue);
		this.setLocation(width / 2 - 300 , height / 2 - 250);
		this.setSize(600, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	private JLabel jlExit,jlMini;

	private JLabel jlFriend;
	
	private JPanel jpBack,
			       jpTopMenu,
			       jpFunction,
			       jpFriendList,jpFl;
	
	private JTextPane jtpChat,jtpInChat;
	
	private JScrollPane jspChat,
						jspFl;
	
	private Point origin = new Point();

	
}
