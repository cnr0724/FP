package computer;
import card.*;

public class Computer {
	public cards computersDeckpu=new cards();
	private cards computersDeckpr=new cards();
	private int comSum=0;
	private int comSumpi=0;
	
	public void addSumpu(int a){
		comSum+=a;
	}
	
	public void addSumpi(int a){
		comSumpi+=a;
	}
	
	public int getSum(){
		return comSum+comSumpi;
	}
	
	public void addPr(card c){
		computersDeckpr.cardAdd(c);
	}

}
