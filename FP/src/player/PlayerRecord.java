package player;

public class PlayerRecord implements java.io.Serializable{
	public String name;
	public int mon;
	public int gameNum;
	public int winNum;
	
	public PlayerRecord(String name,int mon,int gameNum,int winNum){
		this.name=name;
		this.mon=mon;
		this.gameNum=gameNum;
		this.winNum=winNum;
	}
	
	public void recordCheck(){
		System.out.println("The record check to "+name+" with "+mon+" dollars");
	}

}
