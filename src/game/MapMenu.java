package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MapMenu extends JMenuBar {
	
	MapEditor window;
	JMenu file = new JMenu("File");
	JMenuItem load = new JMenuItem("Load Map");
	JMenuItem save = new JMenuItem("Save Map");
	
	public MapMenu(MapEditor Window) {
		
		window = Window;
		
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				load();
			}
			
		});
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				save();
			}
			
		});
		
		
		file.add(load);
		file.add(save);
		add(file);
	}
	
	public void load() {
		
		try {
			JFileChooser saveDialog = new JFileChooser();
			saveDialog.showOpenDialog(window);
			FileReader dat = new FileReader(saveDialog.getSelectedFile());					//user loads the file
			BufferedReader buf = new BufferedReader(dat);
			
			String line = "";
			
			char[] Map = new char[300];
			
			String substring = buf.readLine();
			
			while(substring != null) {
				line = line + substring;
				substring = buf.readLine();
			}
			
			Map = line.toCharArray();
			
			int[] [] map = convertload(Map);
			//String name = (String) read.readObject();
			//String dataname = (String) read.readObject();
			
			window.actualMap = new Map(map,"src\\game\\images\\mapedit.png","");												//read at first the map, than the name of the map and the tileset-name
			dat.close();
			window.surface.changeMap();
			window.palette.repaint();
			window.surface.repaint();
			window.surface.scroll.repaint();
			window.repaint();																			//repaint everything to show the changes
			
		} catch(IOException e) {
			e.getStackTrace();
		}
		
	}
	
	public void save() {																		//saves the map
		
		char[] Map = new char[300];
		
		try {
			JFileChooser saveDialog = new JFileChooser();
			saveDialog.showSaveDialog(window);
			PrintWriter dat = new PrintWriter(saveDialog.getSelectedFile());			//user says where the data is saved
			
			Map = convertsave(window.actualMap.map);
			int a = 0;
			
			for(int i = 0; i < 10; i++) {
				
				int j = 0;
				String line = "";
				
				while (j < 10) {
					line = line + Character.toString(Map[a]);
					a++;
					j++;
				}
				
				dat.println(line);
				dat.flush();
			}
			dat.close();
			
		} catch(IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public int[] [] convertload(char[] map) {
		
		int[] [] Map = new int[10] [10];
		
		for(int i = 0; i < 10; i++) {											//going through char-array and read it out, convert into integer and the right position in integer [] []
			
			for(int j = 0; j < 10; j++) {
				
				switch (map[(i*10)+j]) {										//i*10 to kompensate the reset of j
				
				case 't' : {
					Map [j] [i] = 3;
					break;
				}
				case '#' : {
					Map [j] [i] = 4;
					break;
				}
				case 'c' : {
					Map [j] [i] = 5;
					break;
				}
				case 'v' : {
					Map [j] [i] = 6;
					break;
				}
				case 's' : {
					Map [j] [i] = 7;
					break;
				}
				case 'b' : {
					Map [j] [i] = 8;
					break;
				}
				case 'q' : {
					Map [j] [i] = 10;
					break;
				}
				case 'h' : {
					Map [j] [i] = 11;
					break;
				}
				case 'd' : {
					Map [j] [i] = 12;
					break;
				}
				case 'f' : {
					Map [j] [i] = 13;
					break;
				}
				case 'x' : {
					Map [j] [i] = 14;
					break;
				}
				case 'ü' : {
					Map [j] [i] = 16;
					break;
				}
				case 'm' : {
					Map [j] [i] = 17;
					break;
				}
				case 'k' : {
					Map [j] [i] = 18;
					break;
				}
				case 'l' : {
					Map [j] [i] = 19;
					break;
				}
				case 'z' : {
					Map [j] [i] = 20;
					break;
				}
				case 'p' : {
					Map [j] [i] = 22;
					break;
				}
				case 'g' : {
					Map [j] [i] = 23;
					break;
				}
				case ' ' : {
					Map[j] [i] = 0;
				}
				default : {
					Map [j] [i] = 0;
					break;
				}
				
				}											//end switch
			}												//end for (j)
		}													//end for (i)
		return Map;
	}
	
	public char[] convertsave(int[] [] map) {
		
		char[] Map = new char[300];
		int a = 0;
		
		for(int i= 0; i < window.actualMap.map.length; i++) {
			
			for(int j = 0; j < window.actualMap.map[i].length; j++) {
				
				switch (window.actualMap.map[j] [i]) {
				case 0 : {
					Map[a] = ' ';
					a++;
					break;
				}
				case 1 : {
					Map[a] = ' ';
					a++;
					break;
				}
				case 2 : {
					Map[a] = ' ';
					a++;
					break;
				}
				case 3 : {
					Map[a] = 't';
					a++;
					break;
				}
				case 4 : {
					Map[a] = '#';
					a++;
					break;
				}
				case 5 : {
					Map[a] = 'c';
					a++;
					break;
				}
				case 6 : {
					Map[a] = 'v';
					a++;
					break;
				}
				case 7 : {
					Map[a] = 's';
					a++;
					break;
				}
				case 8 : {
					Map[a] = 'b';
					a++;
					break;
				}
				case 9 : {
					Map[a] = 'b';
					a++;
					break;
				}
				case 10 : {
					Map[a] = 'q';
					a++;
					break;
				}
				case 11 : {
					Map[a] = 'h';
					a++;
					break;
				}
				case 12 : {
					Map[a] = 'd';
					a++;
					break;
				}
				case 13 : {
					Map[a] = 'f';
					a++;
					break;
				}
				case 14 : {
					Map[a] = 'x';
					a++;
					break;
				}
				case 15 : {
					Map[a] = 'x';
					a++;
					break;
				}
				case 16 : {
					Map[a] = ' ';
					a++;
					break;
				}
				case 17 : {
					Map[a] = 'm';
					a++;
					break;
				}
				case 18 : {
					Map[a] = 'k';
					a++;
					break;
				}
				case 19 : {
					Map[a] = 'l';
					a++;
					break;
				}
				case 20 : {
					Map[a] = 'z';
					a++;
					break;
				}
				case 21 : {
					Map[a] = 'z';
					a++;
					break;
				}
				case 22 : {
					Map[a] = 'p';
					a++;
					break;
				}
				case 23 : {
					Map[a] = 'g';
					a++;
					break;
				}
				default : {
					Map[a] = ' ';
					a++;
					break;
				}
				}
			}
		}
	return Map;	
	}

}
