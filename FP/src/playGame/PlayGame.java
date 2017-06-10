package playGame;

import java.io.IOException;
import java.util.ArrayList;

import card.*;
import player.*;
import computer.*;
import inter.*;

public class PlayGame {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException{
		String filename="record.txt";
		RecordReader checking=new RecordReader(filename);
		ArrayList<PlayerRecord> pra=new ArrayList<PlayerRecord>();
		String[] words=checking.text.split("\n");
		for(int i=0;i<(words.length/4);i++){
			PlayerRecord pr=new PlayerRecord(words[i],Integer.parseInt(words[i+1]),Integer.parseInt(words[i+2]),Integer.parseInt(words[i+3]));
			pra.add(pr);
		}
		StartFrame sf=new StartFrame();
		int key=0;
		for(;;){
			key=sf.getSig();
			if(key!=0){
				break;
			}
		}
		if(key==1){
			//게임 플레이
			boolean play=true;
			
			while(play){
				Player p=new Player();
				PlayFrame pf=new PlayFrame(p);
				Computer c=new Computer();
				cards deck=new cards();
				card card=new card();
				
				int turn=1;
				String select = null;
				boolean tie=false;
				int stayNum=0;
				
				System.out.println("Checkingtext:\n"+checking.text);
				
				while(p.playerName==null){}
				int numGame=Integer.parseInt(pf.getName().substring(5, pf.getName().length()))+1;
				p.setPlayerName(pf.pl.playerName);
				System.out.println("The "+numGame+"th game's player name is "+p.playerName);
				
				if(checking.text.contains(p.playerName)){
					int i=0;
					int playing = 0;
					for(;i<words.length;i++){
						if(words[i]==p.playerName){
							playing=i;
						}
					}
					p.setMon(Integer.parseInt(words[playing+1]));
					pf.pl.setMon(Integer.parseInt(words[playing+1]));
					System.out.println("Player's current money: "+p.getMon());
					
				}
				
				while(p.getBet()==0){
						p.setBet(pf.pl.getBet());
				}
				pf.panel.hide();
			
				//덱 뽑음
				deck.newDeck();
			
				//player와 computer에게 두 장씩 배분(두 장은 공개)
				for(;turn<=2;turn++){
					card=deck.cardSelect();
					p.playersDeckpu.cardAdd(card);
					p.addSum(card.getNum());
					pf.addCard("p", card, turn);
					
					card=deck.cardSelect();
					c.computersDeckpu.cardAdd(card);
					c.addSumpu(card.getNum());
					pf.addCard("c", card, turn);
				}
			
				turn++;
			
				for(;;turn++){
					select=null;
					if((p.getSum()==c.getSum())&(p.getSum()>=17)){
						tie=true;
					}
					if(p.getSum()>=21|c.getSum()>=21){
						break;
					}
				
					while(select==null){
						select=pf.getCom();
					}
				
					if(select=="Hit"){
						card=deck.cardSelect();
						p.playersDeckpu.cardAdd(card);
						p.addSum(card.getNum());
						pf.addCard("p", card, turn);
						if(c.getSum()<17){
							card=deck.cardSelect();
							c.addPr(card);
							c.addSumpi(card.getNum());
							pf.addCard("c", card, turn);
						}
					}else{
						stayNum++;
						if(c.getSum()<17){
							card=deck.cardSelect();
							c.addPr(card);
							c.addSumpi(card.getNum());
							pf.addCard("c", card, turn);
						}
						if(stayNum>=3){
							break;
						}
					}
				
					if(p.getSum()>=21|c.getSum()>=21){
						break;
					}
					pf.resetCom();
				}
			
				//tie
				if(tie==true){
					p.setBet(0);
					System.out.println("It's a tie");
					p.lose();
				}
				//both number bigger than 21
				else if(p.getSum()>21&c.getSum()>21){
					int pla=p.getSum()-21;
					int com=c.getSum()-21;
					if(pla>com){
						System.out.println("--You lose");
						p.subBet(p.getBet());
						p.lose();
					}else{
						System.out.println("--You won!");
						p.addBet(p.getBet());
						p.win();
					}
				}
				//if player number>21 or computer=blackjack
				else if(p.getSum()>21|c.getSum()==21){
					System.out.println("--You lose");
					p.subBet(p.getBet());
					p.lose();
				}
				//if computer number>21 or player=blackjack
				else if(c.getSum()>21|p.getSum()==21){
					System.out.println("--You won!");
					p.addBet(p.getBet());
					p.win();
				}
				//both smaller than 21
				else{
					int pla=21-p.getSum();
					int com=21-c.getSum();
					if(pla>com){
						System.out.println("--You lose");
						p.subBet(p.getBet());
						p.lose();
					}else{
						System.out.println("--You won!");
						p.addBet(p.getBet());
						p.win();
					}
				}
				
				System.out.println("The sum of the computer's deck was "+c.getSum());
				System.out.println(p.playerName+"'s current money is "+p.getMon());
				pf.gameEnd();
				System.out.println(pf.checked);
				pf.askingAfterEnd();
				while(pf.savchecked==false){}
				boolean sav=pf.sav;
				
				//if player choose to save the record
				if(sav){
					PlayerRecord pr=new PlayerRecord(p.playerName,p.getMon(),p.getGN(),p.getWN());
					SerializeRecord sr=new SerializeRecord(pr);
					sr.recordToServer();
				}

				while(pf.checked==false){}
				play=pf.game;
				if(play==false){
					pf.dispose();
				}
			}
			RecordWriter rw=new RecordWriter(filename,pra);
		}else if(key==2){
			//기록 조회
				SearchFrame sf1=new SearchFrame();
			
		}
	}

}
