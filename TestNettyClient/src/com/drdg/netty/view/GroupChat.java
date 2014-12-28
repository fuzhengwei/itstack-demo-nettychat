package com.drdg.netty.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import com.drdg.netty.bean.InformationPacket.MsgInfo;
import com.drdg.netty.bean.InformationPacket.Group.User;
import com.drdg.netty.bean.UserBean;
import com.drdg.netty.control.CoreBusinessControl;
import com.drdg.netty.service.MsgHandleService;

public class GroupChat extends JFrame implements ActionListener, MouseListener,
		MouseMotionListener, KeyListener {

	private UserBean userBean;

	public UserBean getUser() {
		return userBean;
	}

	public void setUser(UserBean userBean) {
		jlLoginUser.setText(userBean.getUserName());
		this.userBean = userBean;
	}

	public void refreshFriendsList(List<User> userList) {

		jpFl.removeAll();

		for (User user : userList) {
			jlFriend = new JLabel(user.getId() + " " + user.getUserName());
			jlFriend.setFont(new Font("微软雅黑", 1, 12));
			jlFriend.addMouseListener(this);

			jpFl.add(jlFriend);
			jpFl.updateUI();
			jpFl.invalidate();
			jpFl.validate();
			jpFl.repaint();
		}

	}

	public void refreshReceivedMsg(MsgInfo msgInfo) {
		try {
			if(msgInfo.getSendUser().equals(userBean.getUserName())){
				styledDoc.insertString(styledDoc.getLength(),
						msgInfo.getSendUser()+"\r\n", styledDoc.getStyle("Style08"));
				styledDoc.insertString(styledDoc.getLength(), "\t"
						+ msgInfo.getSendInfo() + "\r\n",
						styledDoc.getStyle("Style01"));
			}else{
				styledDoc.insertString(styledDoc.getLength(),
						msgInfo.getSendUser()+"\r\n", styledDoc.getStyle("Style02"));
				styledDoc.insertString(styledDoc.getLength(), "\t"
						+ msgInfo.getSendInfo() + "\r\n",
						styledDoc.getStyle("Style01"));
			}
			
			jtpChat.setCaretPosition(jtpChat.getDocument().getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void createStyle(String style, StyledDocument doc, int size,
			int bold, int italic, int underline, Color color, String fontName) {
		Style sys = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);
		try {
			doc.removeStyle(style);
		} catch (Exception e) {
		} // 先删除这种Style,假使他存在

		Style s = doc.addStyle(style, sys); // 加入
		StyleConstants.setFontSize(s, size); // 大小
		StyleConstants.setBold(s, (bold == 1) ? true : false); // 粗体
		StyleConstants.setItalic(s, (italic == 1) ? true : false); // 斜体
		StyleConstants.setUnderline(s, (underline == 1) ? true : false); // 下划线
		StyleConstants.setForeground(s, color); // 颜色
		StyleConstants.setFontFamily(s, fontName); // 字体
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == jpBack) {
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
		if (e.getSource() == jpBack) {
			Point p = this.getLocation();
			this.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
					- origin.y);
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getModifiers() == InputEvent.CTRL_MASK
				&& e.getKeyCode() == KeyEvent.VK_ENTER) {
			MsgHandleService.coreBusinessControl.doSendMsg(jtpInChat.getText());
			jtpInChat.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public GroupChat() {
		// 背景设置
		jpBack = new JPanel(null);
		jpBack.setSize(600, 500);
		jpBack.setLocation(0, 0);
		jpBack.setBackground(Color.gray);
		jpBack.addMouseListener(this);
		jpBack.addMouseMotionListener(this);
		this.add(jpBack);

		// 顶部菜单
		jpTopMenu = new JPanel(null);
		jpTopMenu.setSize(600, 30);
		jpTopMenu.setLocation(0, 0);
		jpTopMenu.setBackground(Color.DARK_GRAY);
		jpBack.add(jpTopMenu);

		jlLoginUser = new JLabel();
		jlLoginUser.setForeground(Color.white);
		jlLoginUser.setFont(new Font("微软雅黑", 1, 12));
		jlLoginUser.setSize(50, 20);
		jlLoginUser.setLocation(10, 10);
		jpTopMenu.add(jlLoginUser);

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

		// 聊天面板
		jtpChat = new JTextPane(styledDoc);
		jspChat = new JScrollPane(jtpChat);
		jspChat.setSize(400, 320);
		jspChat.setLocation(10, 40);
		jpBack.add(jspChat);

		// 功能栏
		jpFunction = new JPanel(null);
		jpFunction.setSize(400, 30);
		jpFunction.setLocation(10, 370);
		jpBack.add(jpFunction);

		// 聊天信息输入
		jtpInChat = new JTextPane();
		jtpInChat.setSize(400, 80);
		jtpInChat.setLocation(10, 410);
		jtpInChat.addKeyListener(this);
		jpBack.add(jtpInChat);

		// 好友列表
		jpFriendList = new JPanel(null);
		jpFriendList.setSize(170, 450);
		jpFriendList.setLocation(420, 40);
		jpBack.add(jpFriendList);

		jpFl = new JPanel(new GridLayout(20, 1));
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
		this.setLocation(width / 2 - 300, height / 2 - 250);
		this.setSize(600, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		// Content
		createStyle("Style01", styledDoc, 12, 0, 1, 0, Color.BLACK, "微软雅黑");
		createStyle("Style02", styledDoc, 14, 1, 1, 1, Color.GREEN, "微软雅黑");
		createStyle("Style03", styledDoc, 25, 1, 0, 0, Color.BLUE, "隶书");
		createStyle("Style04", styledDoc, 18, 1, 0, 0, new Color(0, 128, 128),
				fontNames[0]);
		createStyle("Style05", styledDoc, 20, 0, 1, 0, new Color(128, 128, 0),
				fontNames[7]);
		createStyle("Style06", styledDoc, 22, 1, 0, 1, new Color(128, 0, 128),
				fontNames[16]);
		createStyle("Style07", styledDoc, 14, 1, 1, 0, Color.RED, "华文彩云");
		createStyle("Style08", styledDoc, 12, 0, 1, 0, Color.blue, "微软雅黑");

	}

	private JLabel jlLoginUser, jlExit, jlMini, jlFriend;

	private JPanel jpBack, jpTopMenu, jpFunction, jpFriendList, jpFl;

	private JTextPane jtpChat, jtpInChat;

	private JScrollPane jspChat, jspFl;

	private StyledDocument styledDoc = new DefaultStyledDocument();

	String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getAvailableFontFamilyNames();

	private Point origin = new Point();

}
