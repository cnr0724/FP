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
	
	public int getNum(int index){
		if(index<=2){
			return this.num;
		}else{
			return 0;
		}
	}
	
	public String getSuit(){
		return suit;
	}
	
	

}
