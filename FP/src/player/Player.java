package player;
import card.*;

public class Player {
	public String playerName=null;
	public cards playersDeckpu=new cards();
	int money=100;
	int bet=0;
	int sum=0;
	
	public void setPlayerName(String name){
		playerName=name;
		System.out.println(playerName);
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public void setBet(int b){
		if(b>100){
			System.out.println("Enter smaller value");
		}else{
			bet=b;
			System.out.println(bet);
		}
	}
	
	public void addBet(int b){
		money+=b*2;
		bet=0;
	}
	
	public void addSum(int a){
		sum+=a;
	}
	
	public int getBet(){
		return bet;
	}
	
	public int getMon(){
		return money;
	}
	
	public int getSum(){
		return sum;
	}
	

}
