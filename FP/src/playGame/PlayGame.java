package playGame;

import card.*;
import player.*;
import computer.*;
import inter.*;

public class PlayGame {
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
			Player p=new Player();
			PlayFrame pf=new PlayFrame(p);
			Computer c=new Computer();
			cards deck=new cards();
			card card=new card();
			int turn=1;
			String select = null;
			int key1=0;
			
			while(p.getPlayerName()==null){
				String playername=pf.getName();
				p.setPlayerName(playername);
			}
			while(p.getBet()==0){
				int b=pf.pl.getBet();
				p.setBet(b);
			}
			pf.panel.hide();
			
			//덱 뽑음
			deck.newDeck();
			
			//player와 computer에게 두 장씩 배분(두 장은 공개)
			for(;turn<=2;turn++){
				System.out.println("card");
				card=deck.cardSelect();
				p.playersDeckpu.cardAdd(card);
				p.addSum(card.getNum());
				pf.addCard("p", card, turn);
				card=deck.cardSelect();
				c.computersDeckpu.cardAdd(card);
				c.addSumpu(card.getNum());
				pf.addCard("c", card, turn);
			}
			
			if(p.getSum()==21){
				key1=1;
			}else if(c.getSum()==21){
				key1=2;
			}
			
			turn++;
			
			for(;;turn++){
				if(key1==1|key1==2){
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
					if(c.getSum()<17){
						card=deck.cardSelect();
						c.addPr(card);
						c.addSumpi(card.getNum());
						pf.addCard("c", card, turn);
					}
				}
				
				if(p.getSum()>21|c.getSum()>21){
					break;
				}
				
				
			}
			
			
			
			System.out.println("The bet is "+p.getBet());
			System.out.println("The player's sum is "+p.getSum());

			
		}else if(key==2){
			//기록 조회
			
		}
	}

}
