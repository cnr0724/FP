package inter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	JPanel WinLose;
	public Player pl;
	public boolean game=false;
	boolean end=false;
	JButton hit;
	JButton stay;
	public boolean checked=false;
	public boolean sav=false;
	public boolean savchecked=false;
	
	public PlayFrame(Player p){
		pl=p;
		total=new JPanel();
		panel=new JPanel();
		cardPanel=new JPanel();
		comCard=new JPanel();
		plaCard=new JPanel();
		ButCard=new JPanel();
		WinLose=new JPanel();
		String address="back-undo-return-button-png-5.png";
		ImageIcon ori=new ImageIcon(address);
		Image newI=imageChange(ori,"button");
		ori=new ImageIcon(newI);
		
		setTitle("Blackjack with the computer - Play Game");
		
		total.setLayout(new BorderLayout());
		panel.setLayout(new FlowLayout());
		cardPanel.setLayout(new BorderLayout());
		comCard.setLayout(new FlowLayout());
		comCard.setBackground(Color.BLACK);
		plaCard.setLayout(new FlowLayout());
		plaCard.setBackground(Color.GRAY);
		ButCard.setLayout(new GridLayout(1,2));
		
		//Getting new card
		JButton hit=new JButton("Hit");
		hit.setBackground(Color.WHITE);
		hit.setActionCommand("Hit");
		hit.addActionListener(new ButtonClickListener());
		
		//Staying
		JButton stay=new JButton("Stay");
		stay.setBackground(Color.WHITE);
		stay.setActionCommand("Stay");
		stay.addActionListener(new ButtonClickListener());
		
		//Name and bet
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
		cardPanel.add(WinLose,"East");
		cardPanel.add(plaCard,"South");
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
	
	//버튼 제어
	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			command=e.getActionCommand();
			if(command.equals("return")){
				dispose();
			}else if(command.equals("Hit")){
			}else if(command.equals("Stop playing")&end==true){
				checked=true;
				game=false;
				System.out.println("Stop playing");
			}else if(command.equals("Play again")&end==true){
				checked=true;
				game=true;
				end=false;
				System.out.println("Again");
			}else if(command.equals("Save yes")&end==true){
				savchecked=true;
				sav=true;
				System.out.println("Save");
			}
		}
	}

	
	//화면 상에 추가된 카드들 보이기
	public void addCard(String type,card c,int turn){
		String suit=c.getSuit();
		int num=c.getNum();
		int pad=num-1;
		ImageIcon card = null;
		
		if(type=="c"&(turn==1)){
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
		

		if(type=="c"){
			l.setBounds(100*turn+pad*20, 50, 100, 114);
			comCard.add(l);
			comCard.revalidate();
			comCard.validate();
		}else if(type=="p"){
			l.setBounds(100*turn+pad*20, 436, 100, 114);
			plaCard.add(l);
			plaCard.revalidate();
			plaCard.validate();
		}
		revalidate();
		validate();
	}
	
	//다시 할 건지+저장할 건지 묻기
	public void askingAfterEnd(){
		JPanel q=new JPanel();
		JPanel a=new JPanel();
		a.setLayout(new GridLayout(1,2));
		JPanel savq=new JPanel();
		JPanel sava=new JPanel();
		sava.setLayout(new GridLayout(1,2));
		WinLose.setLayout(new GridLayout(4,1));
		JLabel question=new JLabel("Do you want to play again?");
		JButton playAgain=new JButton("Yes");
		playAgain.setBackground(Color.lightGray);
		playAgain.setActionCommand("Play again");
		playAgain.addActionListener(new ButtonClickListener());
		JButton stopPlaying=new JButton("No");
		stopPlaying.setBackground(Color.lightGray);
		stopPlaying.setActionCommand("Stop playing");
		stopPlaying.addActionListener(new ButtonClickListener());
		
		JLabel savquestion=new JLabel("Do you want to save your record?");
		JButton savYes=new JButton("Yes");
		savYes.setBackground(Color.LIGHT_GRAY);
		savYes.setActionCommand("Save Yes");
		savYes.addActionListener(new ButtonClickListener());
		JButton savNo=new JButton("No");
		savNo.setBackground(Color.LIGHT_GRAY);
		
		savq.add(savquestion);
		sava.add(savYes);
		sava.add(savNo);
		
		q.add(question);
		a.add(playAgain);
		a.add(stopPlaying);
		WinLose.add(q);
		WinLose.add(a);
		WinLose.add(savq);
		WinLose.add(sava);
		WinLose.revalidate();
		WinLose.validate();
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
	
	public void gameEnd(){
		end=true;
	}
	
	public boolean again(){
		return game;
	}
}
