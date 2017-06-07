package playGame;

import card.*;
import player.*;
import computer.*;
import inter.*;

public class PlayGame {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
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
			
			while(play==true){
				Player p=new Player();
				PlayFrame pf=new PlayFrame(p);
				Computer c=new Computer();
				cards deck=new cards();
				card card=new card();
				int turn=1;
				String select = null;
				boolean tie=false;
				int stayNum=0;
				
				while(p.getPlayerName()==null){
					String playername=pf.getName();
					p.setPlayerName(playername);
				}
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
					System.out.println("The player's sum is "+p.getSum());
					System.out.println("The computer's sum is "+c.getSum());				
				}
			
				if(tie==true){
					p.setBet(0);
					System.out.println("It's a tie");
				}else if(p.getSum()>21&c.getSum()>21){
					int pla=p.getSum()-21;
					int com=c.getSum()-21;
					if(pla>com){
						System.out.println("--You lose");
					}else{
						System.out.println("--You won!");
					}
				}else if(p.getSum()>21|c.getSum()==21){
					System.out.println("--You lose");
				}else if(c.getSum()>21|p.getSum()==21){
					System.out.println("--You won!");
				}else{
					int pla=21-p.getSum();
					int com=21-c.getSum();
					if(pla>com){
						System.out.println("--You lose");
					}else{
						System.out.println("--You won!");
					}
				}
			
				System.out.println("The player's sum is "+p.getSum());
				System.out.println("The computer's sum is "+c.getSum());
				int k=0;
				pf.gameEnd();
				pf.askingAfterEnd();
				
							
			}
		}else if(key==2){
			//기록 조회
			SearchFrame sf1=new SearchFrame();
			
			
		}
	}

}
