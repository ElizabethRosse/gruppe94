package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
			FileInputStream dat = new FileInputStream(saveDialog.getSelectedFile());					//user loads the file
			BufferedInputStream buf = new BufferedInputStream(dat);
			ObjectInputStream read = new ObjectInputStream(buf);
			
			int[] [] map = (int[] []) read.readObject();
			String name = (String) read.readObject();
			String dataname = (String) read.readObject();
			
			window.actualMap = new Map(map,name,dataname);												//read at first the map, than the name of the map and the tileset-name
			read.close();
			window.surface.changeMap();
			window.palette.repaint();
			window.surface.repaint();
			window.surface.scroll.repaint();
			window.repaint();																			//repaint everything to show the changes
			
		} catch(IOException e) {
			e.getStackTrace();
		} catch(ClassNotFoundException e) {
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
