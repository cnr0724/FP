package playGame;

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
			PlayFrame pf=new PlayFrame();
			Player p=new Player();
			Computer c=new Computer();
		}
	}

}
