package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		
		try {
			JFileChooser saveDialog = new JFileChooser();
			saveDialog.showSaveDialog(window);
			FileOutputStream dat = new FileOutputStream(saveDialog.getSelectedFile());			//user says where the data is saved
			BufferedOutputStream buf = new BufferedOutputStream(dat);
			ObjectOutputStream write = new ObjectOutputStream(buf);
			
			write.writeObject(window.actualMap.map);											//data contains: map, mapname, tileset-name; in this row
			write.writeObject(window.actualMap.mapName);										
			write.writeObject(window.actualMap.tilesetDataname);
			write.close();
			
		} catch(IOException e) {
		
			e.printStackTrace();
		}
	}

}
