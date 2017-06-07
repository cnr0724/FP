package card;

public class card {
	private String suit;
	private int num;
	private boolean used=false;
	
	void setCard(String s,int i){
		this.suit=s;
		this.num=i;
	}
	
	void cardSelected(){
		this.used=true;
	}
	
	public boolean getCardUsed(){
		return this.used;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public String getSuit(){
		return suit;
	}
	
	

}
