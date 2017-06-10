package inter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import player.*;

public class SerializeRecord {
	PlayerRecord pr;
	
	
	public SerializeRecord(PlayerRecord p){
		pr=p;
	}
	
	public void recordToServer(){
		try{
			FileOutputStream fileOut=new FileOutputStream(SerializeRecord.class.getResource("").getPath()+"player.ser");
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
			out.writeObject(pr);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data save in /tmp/player.ser");
		}catch(IOException i){
			i.printStackTrace();
		}
	}
}
