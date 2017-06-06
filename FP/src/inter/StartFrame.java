package inter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame{
	private int signal=0;
	public StartFrame(){
		setTitle("Blackjack with the computer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(300,200,900,600);
		
		JLabel label=new JLabel("The test form");
		label.setBounds(200, 50, 500, 100);
		JButton b1=new JButton("1. 게임 시작");
		b1.setBounds(250,250,400,80);
		b1.setActionCommand("1");
		b1.addActionListener(new ButtonClickListener());
		JButton b2=new JButton("2. 기록 조회");
		b2.setBounds(250,330,400,80);
		b2.setActionCommand("2");
		b2.addActionListener(new ButtonClickListener());
		JButton b3=new JButton("3. 그만하기");
		b3.setBounds(250, 410, 400, 80);
		b3.setActionCommand("3");
		b3.addActionListener(new ButtonClickListener());

		add(label);
		add(b1);
		add(b2);
		add(b3);

		setVisible(true);
	}
	
	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String command=e.getActionCommand();
			if(command.equals("1")){
				signal=1;
				dispose();
			}else if(command.equals("2")){
				signal=2;
				dispose();
			}else if(command.equals("3")){
				signal=3;
				dispose();
			}
		}
	}
	
	public int getSig(){
		return signal;
	}
}
