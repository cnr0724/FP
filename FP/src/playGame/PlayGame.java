package playGame;

import java.io.IOException;

import card.*;
import player.*;
import computer.*;
import inter.*;

public class PlayGame {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException{
		String filename="record.txt";
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
			String nam = null;
			int mon=0;
			int ke=0;
			
			while(play==true){
				ke++;
				Player p=new Player();
				PlayFrame pf=new PlayFrame(p);
				Computer c=new Computer();
				cards deck=new cards();
				card card=new card();
				int turn=1;
				String select = null;
				boolean tie=false;
				int stayNum=0;
				RecordReader checking=new RecordReader(filename);
				
				while(p.playerName==null){
					String playername=pf.getName();
					p.setPlayerName(playername);
				}
/*				if(ke>=1&(checking.text.contains(p.playerName))){
					int i=0;
					String[] words=checking.text.split("\n");
					for(;i<words.length;i++){
						if(words[i]==p.playerName){
							break;
						}
					}
					String mOney=words[i+1];
					p.setMon(Integer.parseInt(mOney));
					pf.pl.setMon(Integer.parseInt(mOney));
				}*/
				while(p.getBet()==0){
					int b=pf.pl.getBet();
					if(b>0&b<p.getMon()){
						p.setBet(b);
					}
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
					if(p.getSum()>=21|c.getSum()>=21){
						break;
					}
					if((p.getSum()==c.getSum())&p.getSum()>=17){
						tie=true;
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
			
				if(tie==true){
					p.setBet(0);
					System.out.println("It's a tie");
				}else if(p.getSum()>21&c.getSum()>21){
					int pla=p.getSum()-21;
					int com=c.getSum()-21;
					if(pla>com){
						System.out.println("--You lose");
						p.subBet(p.getBet());
					}else{
						System.out.println("--You won!");
						p.addBet(p.getBet());
					}
				}else if(p.getSum()>21|c.getSum()==21){
					System.out.println("--You lose");
					p.subBet(p.getBet());
				}else if(c.getSum()>21|p.getSum()==21){
					System.out.println("--You won!");
					p.addBet(p.getBet());
				}else{
					int pla=21-p.getSum();
					int com=21-c.getSum();
					if(pla>com){
						System.out.println("--You lose");
						p.subBet(p.getBet());
					}else{
						System.out.println("--You won!");
						p.addBet(p.getBet());
					}
				}
				
				System.out.println(p.playerName+"'s current money1 is "+p.getMon());
				pf.gameEnd();
				System.out.println(pf.checked);
				pf.askingAfterEnd();
				while(pf.savchecked==false){}
				boolean sav=pf.sav;
				if(sav){
					mon=p.getMon();
					nam=p.playerName;
					RecordReader rr=new RecordReader(filename);
					RecordWriter rw=new RecordWriter(filename,rr.text,nam,mon);
				}
				play=false;
				while(pf.checked==false){}
				play=pf.game;
				if(play==false){
					pf.dispose();
				}
			}
		}else if(key==2){
			//기록 조회
				SearchFrame sf1=new SearchFrame();
			
		}
	}

}
