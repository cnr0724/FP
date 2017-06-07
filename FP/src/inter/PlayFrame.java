package inter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import playGame.*;
import player.Player;
import card.*;

import javax.swing.*;

public class PlayFrame extends JFrame {
	String command=null;
	JTextField nameTextField;
	JTextField betTF;
	JPanel total;
	public JPanel panel;
	JPanel cardPanel;
	JPanel comCard;
	JPanel plaCard;
	JPanel ButCard;
	public Player pl;
	
	public PlayFrame(Player p){
		pl=p;
		total=new JPanel();
		panel=new JPanel();
		cardPanel=new JPanel();
		comCard=new JPanel();
		plaCard=new JPanel();
		ButCard=new JPanel();
		String address="back-undo-return-button-png-5.png";
		ImageIcon ori=new ImageIcon(address);
		Image newI=imageChange(ori,"button");
		ori=new ImageIcon(newI);
		setTitle("Blackjack with the computer");
		total.setLayout(new BorderLayout());
		panel.setLayout(new FlowLayout());
		cardPanel.setLayout(new BorderLayout());
		comCard.setLayout(new FlowLayout());
		comCard.setBackground(Color.BLACK);
		plaCard.setLayout(new FlowLayout());
		plaCard.setBackground(Color.GRAY);
		ButCard.setLayout(new GridLayout(1,2));
		
		//메인화면으로 가는 버튼
		JButton returning=new JButton(ori);
		returning.setBackground(Color.white);
		returning.setBounds(0,0,50,50);
		returning.setActionCommand("return");
		returning.addActionListener(new ButtonClickListener());
		
		JButton hit=new JButton("Hit");
		hit.setBackground(Color.WHITE);
		hit.setActionCommand("Hit");
		hit.addActionListener(new ButtonClickListener());
		
		JButton stay=new JButton("Stay");
		stay.setBackground(Color.WHITE);
		stay.setActionCommand("Stay");
		stay.addActionListener(new ButtonClickListener());
		
		//이름 입력받기
		JLabel name=new JLabel("Enter your name: ");
		nameTextField=new JTextField(20);
		nameTextField.requestFocus();
		nameTextField.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent evt){
				pl.setPlayerName(nameTextField.getText());
				name.hide();
				nameTextField.hide();
		}});
		
		JLabel bet=new JLabel("Enter how much you are going to bet: ");
		betTF=new JTextField(20);
		betTF.requestFocus();
		betTF.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent evt){
				if(Integer.parseInt(betTF.getText())<=pl.getMon()){
					pl.setBet(Integer.parseInt(betTF.getText()));
					bet.hide();
					betTF.hide();
				}
		}});
		
		ButCard.add(hit);
		ButCard.add(stay);
		cardPanel.add(comCard,"North");
		cardPanel.add(ButCard);
		cardPanel.add(plaCard,"South");
//		cardPanel.add(returning);
		panel.add(name,"South");
		panel.add(nameTextField,"South");
		panel.add(bet,"South");
		panel.add(betTF,"South");
		total.add(cardPanel,"Center");
		total.add(panel,"South");
		getContentPane().add(total,"Center");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,200,900,600);
		setVisible(true);
	}
	
	
	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			command=e.getActionCommand();
			if(command.equals("return")){
				dispose();
			}else if(command.equals("Hit")){
				try {
					wait(20);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				
			}
		}
	}

	
	//화면 상에 추가된 카드들 보이기
	public void addCard(String type,card c,int turn){
		String suit=c.getSuit();
		int num=c.getNum();
		int pad=num-1;
		ImageIcon card = null;
		
		if(type=="c"&((turn==1)|(turn>2))){
			card=new ImageIcon("playing-card-back-side.jpg");
		}else{
			if(num==1){
				card=new ImageIcon("ace_of_"+suit+"s.png");
			}else if(num==11){
				card=new ImageIcon("jack_of_"+suit+"s2.png");
			}else if(num==12){
				card=new ImageIcon("queen_of_"+suit+"s2.png");
			}else if(num==13){
				card=new ImageIcon("king_of_"+suit+"s2.png");
			}else{
				card=new ImageIcon(num+"_of_"+suit+"s.png");
			}
		}
		
		Image i=imageChange(card,"card");
		card=new ImageIcon(i);
		
		JLabel l=new JLabel(card);
		
		if(turn<=2){
			if(type=="c"){
				l.setBounds(100*turn+pad*20, 50, 100, 114);
				comCard.add(l);
				comCard.revalidate();
				comCard.validate();
			}
			else if(type=="p"){
				l.setBounds(100*turn+pad*20, 436, 100, 114);
				plaCard.add(l);
				plaCard.revalidate();
				plaCard.validate();
			}
		}else{
			if(type=="c"){
				
			}
		}
		System.out.println(num+" of "+suit+" of "+turn+" turn added");
		revalidate();
		validate();
	}
	
	//이미지 변환
	private Image imageChange(ImageIcon i,String aim){
		Image ori=i.getImage();
		Image nu = null;
		if(aim=="button"){
			nu=ori.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		}else if(aim=="card"){
			nu=ori.getScaledInstance(80, 114, Image.SCALE_SMOOTH);
		}
		return nu;
	}
	
	public void resetName(){
		pl.setPlayerName(null);
	}
	
	public String getCom(){
		return command;
	}
	
	public void resetCom(){
		command=null;
	}
	
	
}
