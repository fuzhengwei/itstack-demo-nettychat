package com.drdg.netty.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.drdg.netty.control.CoreBusinessControl;
import com.drdg.netty.service.MsgHandleService;

public class MM extends JFrame implements ActionListener,MouseListener,MouseMotionListener{

	private static CoreBusinessControl coreBusinessConntrol;
	
	public static void main(String[] args) {
		
		MM mm = new MM();
		coreBusinessConntrol = new CoreBusinessControl(mm);
		MsgHandleService.coreBusinessControl = coreBusinessConntrol;
	}
	
	/**
	 * 构造函数
	 */
	public MM(){
		
		//JLabel
		lblTop = new JLabel(new ImageIcon("resources/LoginUi/morning.jpg"));
		lblTop.setSize(430, 184);
		lblTop.setLocation(0, 0);
		lblTop.addMouseMotionListener(this);
		
		lblTopClose = new JLabel(new ImageIcon("resources/LoginUi/close.png"));
		lblTopClose.setSize(12, 12);
		lblTopClose.setLocation(415, 2);
		lblTopClose.addMouseListener(this);
		lblTop.add(lblTopClose);
		
		this.add(lblTop);
		
		//Center
		pnlCenter = new JPanel(null);
		pnlCenter.setSize(430, 146);
		pnlCenter.setLocation(0, 184);
		pnlCenter.setBackground(new Color(235,242,249));
		this.add(pnlCenter);
		
		//二维码
		lblBottom = new JLabel(new ImageIcon("resources/LoginUi/generate.png"));
		lblBottom.setSize(74,74);
		lblBottom.setLocation(356, 70);
		pnlCenter.add(lblBottom);
		
		lblBottomVersion = new JLabel("V1.0");
		lblBottomVersion.setSize(30,15);
		lblBottomVersion.setLocation(2, 130);
		lblBottomVersion.setFont(new Font("微软雅黑",Font.BOLD,12));
		lblBottomVersion.setForeground(Color.GRAY);
		pnlCenter.add(lblBottomVersion);
		
		//头像
		lblCenter = new JLabel(new ImageIcon("resources/LoginUi/head.png"));
		lblCenter.setSize(80, 80);
		lblCenter.setLocation(40, 15);
		pnlCenter.add(lblCenter);
		//用户名
		lblJtfUserName = new JLabel(new ImageIcon("resources/LoginUi/edit_frame_normal_reversed.png"));
		lblJtfUserName.setSize(194, 30);
		lblJtfUserName.setLocation(136, 25);
		pnlCenter.add(lblJtfUserName);
		
		lblJtfUserImg = new JLabel(new ImageIcon("resources/LoginUi/user.png"));
		lblJtfUserImg.setSize(30, 30);
		lblJtfUserImg.setLocation(165, 0);
		lblJtfUserName.add(lblJtfUserImg);
		
		jtfUserName = new JTextField();
		jtfUserName.setBorder(new EmptyBorder(0, 0, 0, 0));
		jtfUserName.setOpaque(false);
		jtfUserName.setSize(165, 30);
		jtfUserName.setLocation(2, 0);
		jtfUserName.addMouseListener(this);
		lblJtfUserName.add(jtfUserName);
		
		//密码
		lblJpfPassword = new JLabel(new ImageIcon("resources/LoginUi/edit_frame_normal.png"));
		lblJpfPassword.setSize(194, 30);
		lblJpfPassword.setLocation(136, 55);
		pnlCenter.add(lblJpfPassword); 
		
		lblJtfPwdImg = new JLabel(new ImageIcon("resources/LoginUi/pwd.png"));
		lblJtfPwdImg.setSize(30, 30);
		lblJtfPwdImg.setLocation(165, 0);
		lblJpfPassword.add(lblJtfPwdImg);
		
		jpfPassword = new JPasswordField();
		jpfPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		jpfPassword.setOpaque(false);
		jpfPassword.setSize(165, 30);
		jpfPassword.setLocation(2, 0);
		jpfPassword.addMouseListener(this);
		lblJpfPassword.add(jpfPassword);
		
		//登录
		jbLogin = new JButton(new ImageIcon("resources/LoginUi/button_login_normal.png"));
		jbLogin.setLayout(null);
		jbLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
		jbLogin.setSize(194, 30);
		jbLogin.setLocation(136, 110);
		jbLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jbLogin.addActionListener(this);
		jbLogin.addMouseListener(this);
		pnlCenter.add(jbLogin); 
		
		lblJbLogin = new JLabel("登 录");
		lblJbLogin.setFont(new Font("微软雅黑",Font.BOLD,12));
		lblJbLogin.setForeground(Color.white);
		lblJbLogin.setLocation(85, 0);
		lblJbLogin.setSize(30, 30);
		jbLogin.add(lblJbLogin);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		
		this.setLayout(null);
		this.setUndecorated(true);
		this.setLocation(width / 2 - 215 , height / 2 - 165);
		this.setSize(430, 330);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	private JPanel pnlCenter;

	private JLabel lblTop,lblTopClose,
	               lblCenter,lblJtfUserName,lblJpfPassword,lblJtfUserImg,lblJtfPwdImg,
	               lblJbLogin,
	               lblBottom,lblBottomVersion;
	
	private JTextField jtfUserName;
	
	private JPasswordField jpfPassword;
	
	private JButton jbLogin;
	
	private Point origin = new Point();
	
	/**
	 * 事件
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == jbLogin){
			
			coreBusinessConntrol.doCheckConnectLogin(jtfUserName.getText(),jpfPassword.getText());
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource() == lblTopClose){
			lblTopClose.setIcon(new ImageIcon("resources/LoginUi/close_hover.png"));
		}
		
		if(arg0.getSource() == jtfUserName)
		{
			lblJtfUserName.setIcon(new ImageIcon("resources/LoginUi/edit_frame_hover_reversed.png"));
		}
		
		if(arg0.getSource() == jpfPassword)
		{
			lblJpfPassword.setIcon(new ImageIcon("resources/LoginUi/edit_frame_hover.png"));
		}
		
		if(arg0.getSource() == jbLogin){
			jbLogin.setIcon(new ImageIcon("resources/LoginUi/button_login_hover.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource() == lblTopClose){
			lblTopClose.setIcon(new ImageIcon("resources/LoginUi/close.png"));
		}
		
		if(arg0.getSource() == jtfUserName)
		{
			lblJtfUserName.setIcon(new ImageIcon("resources/LoginUi/edit_frame_normal_reversed.png"));
		}
		
		if(arg0.getSource() == jpfPassword)
		{
			lblJpfPassword.setIcon(new ImageIcon("resources/LoginUi/edit_frame_normal.png"));
		}
		
		if(arg0.getSource() == jbLogin){
			jbLogin.setIcon(new ImageIcon("resources/LoginUi/button_login_normal.png"));
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		if(arg0.getSource() == lblTopClose){
			System.exit(0);
		}
		
		if(arg0.getSource() == jbLogin){
			jbLogin.setIcon(new ImageIcon("resources/LoginUi/button_login_down.png"));
		}
		
		if(arg0.getSource() == lblTop){
			// 当鼠标按下的时候获得窗口当前的位置
			origin.x = arg0.getX();
			origin.y = arg0.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (arg0.getSource() == lblTop) {
			Point p = this.getLocation();
			this.setLocation(p.x + arg0.getX() - origin.x, p.y + arg0.getY()
					- origin.y);
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

}


