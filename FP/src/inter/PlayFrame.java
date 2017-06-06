package inter;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PlayFrame extends JFrame {
	String command;
	public PlayFrame(){
		String address="back-undo-return-button-png-5.png";
		ImageIcon ori=new ImageIcon(address);
		Image oriI=ori.getImage();
		Image newI=oriI.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ori=new ImageIcon(newI);
		setTitle("Blackjack with the computer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(300,200,900,600);
		
		JButton returning=new JButton(ori);
		returning.setBackground(Color.white);
		returning.setBounds(0,0,50,50);
		returning.setActionCommand("return");
		returning.addActionListener(new ButtonClickListener());
		
		add(returning);
		
		setVisible(true);
	}
	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String command=e.getActionCommand();
			if(command.equals("return")){
				
				dispose();
			}
		}
	}
	
	
	public void exit() {
		System.exit(0);
	}
}
