package inter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RecordReader {
	public String text="";
	int nBuffer;
	public RecordReader(String filename) throws IOException{
		File file=new File(RecordReader.class.getResource("").getPath()+filename);
		if(file.exists()){
			BufferedReader br=new BufferedReader(new FileReader(file));
			while((nBuffer=br.read())!=-1){
				text+=(char)nBuffer;
			}
			br.close();
		}
	}
	
	public String retStr(){
		return text;
	}

}
