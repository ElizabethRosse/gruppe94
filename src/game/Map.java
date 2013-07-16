package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {
	
	String mapName;
	String tilesetDataname;
	
	int[] [] map;
	ArrayList<BufferedImage> tileset = new ArrayList<BufferedImage>();
	
	public Map(int[] [] map, String dataname, String name) {
		this.mapName = name;
		this.map = map;
		this.tilesetDataname = dataname;
		
		try {
			BufferedImage bildTileset = ImageIO.read(new File(dataname));
			int width = bildTileset.getWidth()/32;										//contains the width of the coded map (in array)
			int height = bildTileset.getHeight()/32;									//contains the height of the coded map (in array)
			
			for(int x = 0; x < width; x++) {											//double to go through array and load the tiles
				
				for(int y = 0; y < height; y++) {
				
					BufferedImage tile = bildTileset.getSubimage(x*32, y*32, 32, 32);	//resize the tile 
					this.tileset.add(tile);												//add the tile to the new array, which contains the pictures
				}
			}
		} catch (IOException fault) {
			System.err.println("Tileset " + dataname + "not found");
			fault.printStackTrace();
		}
	}
	
	public BufferedImage getTileImage(int x, int y) {
		
		int tile = map[x] [y];
		
		return tileset.get(tile);
	}
	
	public void setTile (int x, int y, int tileID) {							//saves the actual tile at the current position
		map [x] [y] = tileID;
	}

}
