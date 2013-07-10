package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Saving {
	 FileReader datei = null;
	 BufferedReader dat;
	 public Saving() {
		 try {
			datei = new FileReader("src\\game\\savings\\save1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if (datei != null) {
		 dat = new BufferedReader(datei);
		 }
		
	 }
	 
	 public int[] load() throws IOException {
		 String line;
		 int wert;
		 int[] loadings = new int[16];
		 line = dat.readLine();
		 int a = 0;
			
		 while(line != null) {														//read a line
			wert = Integer.parseInt(line);
			loadings[a] = wert;														//save the loaded value in int-array to read out the saved game
			a++;
			line = dat.readLine();													// read a new line
		 }
		 dat.close();
		 return loadings;
	 }
	 
	 public void save(int[] save) throws IOException{
		 String line;
		 PrintWriter pWriter;
		 pWriter = new PrintWriter(new FileWriter("test.txt"));
		 
		 for (int i=0; i <12; i++) {
			 line = ""  + save[i];
			 pWriter.println("Hallo Welt!");
	         pWriter.flush(); 
		 }
	 }
}
