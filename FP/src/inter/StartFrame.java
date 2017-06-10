package inter;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class StartFrame extends JFrame{
	private int signal=0;
	public StartFrame(){
		setTitle("Blackjack with the computer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBounds(300,200,900,600);
		JPanel p=new JPanel();
		p.setLayout(null);
		p.setBackground(Color.WHITE);
		p.setBounds(0,0,900,600);
		Font font=new Font("Courier",Font.BOLD,40);
		
		JLabel label=new JLabel("The game");
		label.setBounds(350, 0, 300, 300);
		label.setBackground(Color.WHITE);
		label.setFont(font);
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

		p.add(label,"Center");
		p.add(b1);
		p.add(b2);
		p.add(b3);

		getContentPane().add(p);

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
