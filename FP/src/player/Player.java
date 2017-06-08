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
		if(name!=null){
			System.out.println(playerName);
		}
	}
	
	public void setBet(int b){
		if(b>this.money){
			System.out.println("Enter smaller value");
		}else if(b<=0){
			System.out.println("Enter bigger value");
		}else{
			bet=b;
			if(bet!=0){
				System.out.println(bet);
			}
		}
	}
	
	public void addBet(int b){
		money+=b*2;
		this.bet=0;
	}
	
	public void subBet(int b){
		money-=b;
		this.bet=0;
	}
	
	public void addSum(int a){
		sum+=a;
	}
	
	public int getBet(){
		return bet;
	}
	
	public void setMon(int a){
		this.money=a;
	}
	
	public int getMon(){
		return money;
	}
	
	public int getSum(){
		return sum;
	}
	

}
