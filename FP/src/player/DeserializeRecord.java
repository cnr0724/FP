package player;

import inter.SerializeRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeRecord {
	
	public static void main(String[] args){
		PlayerRecord pr=null;
		try{
			FileInputStream fileIn=new FileInputStream(SerializeRecord.class.getResource("").getPath()+"player.ser");
			ObjectInputStream in=new ObjectInputStream(fileIn);
			pr=(PlayerRecord)in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i){
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c){
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}

		System.out.println("Deserialized player");
		System.out.println("Name: "+pr.name);
		System.out.println("Left money: "+pr.mon);
		System.out.println("Number of game played: "+pr.gameNum);
		System.out.println("Number of game won: "+pr.winNum);
	}

}
