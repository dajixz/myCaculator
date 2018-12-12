package com.daji.myCaculator.test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class CalFrame extends JFrame {
	private JTextField text = null;
	private JButton button = null; //´æ´¢±ê¼Ç
	private JPanel panel,panel1,panel2;
	private String[] NB = {"7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/"};
	private String[] RB = {"Backspace","C"};
	private CalService service = new CalService();
	
	public CalFrame(){
		this.setTitle("·ÂWindows¼ÆËãÆ÷");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	    panel = new JPanel();
	    panel1 = new JPanel();
	    panel2 = new JPanel();
	    panel.setLayout(new BorderLayout());
	    panel1.setLayout(new GridLayout(1,2));
	    panel2.setLayout(new GridLayout(4,4));
	    JButton[] NB = getNButton();
	    for(JButton button : NB){
	    	panel2.add(button);
	    }
		JButton[] RB = getRButton();
		for(JButton button : RB){
		    panel1.add(button);
		}
		panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel2,BorderLayout.CENTER);	
        this.add(getTextField(),BorderLayout.NORTH);
	    this.add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
		this.setVisible(true);
	}
	
	private JTextField getTextField(){
		text = new JTextField("0");
		return text;
	}
	
	private JButton[] getNButton(){
      JButton[] button = new JButton[NB.length];
		for(int i = 0; i < this.NB.length; i++){
			JButton b = new JButton(this.NB[i]);
			b.addActionListener(getActionListener());
            button[i] = b;
		}
		return button;
	}
	
	private JButton[] getRButton(){
		JButton[] button = new JButton[RB.length];
		for(int i = 0; i < this.RB.length; i++){
			JButton b = new JButton(this.RB[i]);
			b.addActionListener(getActionListener());
			button[i] = b;
		}
		return button;
	}
   private ActionListener getActionListener(){
	   ActionListener actionListener = new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   String cmd = e.getActionCommand();
			   String result = null;
			   try {
				   result = service.callMethod(cmd, text.getText());
			   } catch (Exception e2) {
				   e2.printStackTrace();
			   }
			   if(result != null){
				   text.setText(result);
			   }
		   }
		};
		return actionListener;
	}
   
	public static void main(String[] args) {
		new CalFrame();
	}
}
