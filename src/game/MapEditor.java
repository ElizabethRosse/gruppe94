package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MapEditor extends JFrame {									//creates the map-editor
	
	public Map actualMap;
	TilePalette palette;												//creates all tiles which can be used
	MapSurface surface;													//shows the actual version of the map
	MapMenu mapmenu;													//utilities for loading and saving maps (bar layout)
	
	public MapEditor() {
		
		this.requestFocus();
		
		int[] [] map = new int[10] [10];								//contains the map as integer
		
		String name = "first map";
		String tilesetDataname = "src\\game\\images\\mapedit.png";		//data in which are all objectives as picture
		
		actualMap = new Map(map,tilesetDataname,name);
		palette = new TilePalette(this);
		surface = new MapSurface(this);
		mapmenu = new MapMenu(this);
		
		setLayout(new BorderLayout());
		
		add(mapmenu, BorderLayout.NORTH);
		add(palette, BorderLayout.WEST);
		add(surface.scroll, BorderLayout.CENTER);
		
		setSize(750, 600);
		setTitle("MapEditor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

}
