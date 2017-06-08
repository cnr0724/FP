package inter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class RecordWriter {
	public RecordWriter (String filename,String and,String name,int mon) throws IOException {
		Writer osw,bw = null;
		OutputStream fos;
		boolean suc=false;
		String data=null;
		try {
			fos = new FileOutputStream(RecordWriter.class.getResource("").getPath()+filename);
	        osw = new OutputStreamWriter (fos);
	        bw  = new BufferedWriter (osw);
	        if(and!=""){
	        	data=and+name+"\n"+mon+"\n";
	        }else{
	        	data=name+"\n"+mon+"\n";
	        }
			System.out.println(data);
			suc=true;
			System.out.println("Wrote");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data);
		bw.write(data);
		bw.close();
	}
}
