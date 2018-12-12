package com.daji.myCaculator.frame;

import com.daji.myCaculator.core.CoreOperation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


//����javax.swing
public class MainWin  extends JFrame implements ActionListener{

	private JButton[] jbs = new JButton[]{
		new JButton("����"),new JButton("("),new JButton(")"),new JButton("/"),	
		new JButton("7"),new JButton("8"),new JButton("9"),new JButton("*"),
		new JButton("4"),new JButton("5"),new JButton("6"),new JButton("-"),
		new JButton("1"),new JButton("2"),new JButton("3"),new JButton("+"),
		new JButton("0"),new JButton("."),new JButton("="),new JButton("����")
	};
	private JPanel jpl1=new JPanel();
	private JPanel jpl2=new JPanel();
	private JTextField jtf=new JTextField("");
	//flag �����ж��Ƿ��Ѿ���������
	private boolean flag = false;
	public MainWin(){
		init();
	}
	//��ʼ������
	private void init(){
		//��ʼ���ı������
		jpl1.setLayout(new BorderLayout());
		jpl1.add(jtf);
		this.add(jpl1,BorderLayout.NORTH);
		jpl2.setLayout(new GridLayout(5,4));
		for(int i=0;i<jbs.length;i++){
			//Ϊÿ����ť���Ӽ�����
			jbs[i].addActionListener(this);
			
			jpl2.add(jbs[i]);
//			if(i==3||i==7||i==11||i==15||i==14)
//				jbs[i].addActionListener(new setOperate_Act());
//			else
//				jbs[i].addActionListener(new setLabel_Act());
		}
		this.add(jpl2,BorderLayout.CENTER);
		this.setSize(250, 200);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String text = e.getActionCommand();
		if(text=="����"){
			jtf.setText("");
		}else if(text=="="){
			if(jtf.getText()!=null && !jtf.getText().trim().equals("")){
                System.out.println(jtf.getText());
                CoreOperation coreOperation = new CoreOperation(jtf.getText());
                double v = coreOperation.doCalculation();
                jtf.setText(String.valueOf(v));
                flag=true;
            }
		}else if(text=="����"){
			String str = jtf.getText();
			if(str.length()==1){
				return;
			}
			jtf.setText(str.substring(0,str.length()-1));
		}else{
			//���ı�����׷������
            if(flag){
                jtf.setText("");
                flag=false;
            }
			jtf.setText(jtf.getText()+text);
		}
		
	}
	public static void main(String args[]){
		new MainWin();
		
	}
}
