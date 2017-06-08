package inter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchFrame extends JFrame{
	JPanel p;
	JTextField nameTextField;
	String s;
	BufferedReader br;
	String record;
	
	public SearchFrame() throws IOException{
		setTitle("Blackjack with the computer - Search Record");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(300,200,900,600);
		setBackground(Color.WHITE);
		p=new JPanel();
		JLabel label=new JLabel();
		RecordReader checking=new RecordReader("record.txt");
		int i=0;
		String[] words=checking.text.split("\n");
		
		
		JLabel name=new JLabel("Enter your name: ");
		nameTextField=new JTextField(20);
		nameTextField.requestFocus();
		nameTextField.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent evt){
				s=nameTextField.getText();
				name.hide();
				nameTextField.disable();
		}});
		for(;i<words.length;i++){
			if(words[i]==s){
				break;
			}
		}
		String mOney=words[i+1];
		
		p.add(nameTextField,"North");
		
		
		setVisible(true);
	}
}
