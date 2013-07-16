package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TilePalette extends JPanel{
	
	MapEditor window;
	int actualTile = 0;
	
	public TilePalette(MapEditor Window) {
		window = Window;
		setPreferredSize(new Dimension(4*50, (window.actualMap.tileset.size() / 4 ) * 50));				//4 rows and (tileset.size() / 4) * 50 columns
		setDoubleBuffered(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				setTileID(e.getX(), e.getY());
			}
		});
	}
	
	public void paintComponent(Graphics g) {							//painting the TilePalette
		
		int TileNumber = window.actualMap.tileset.size();
		int row = 0;
		int column = 0;
		
		for(int i = 0; i < TileNumber; i++) {
			
			BufferedImage tile =  window.actualMap.tileset.get(i);
			g.drawImage(tile, column * 50 , row * 50, this);
			
			if(i % 4 == 3) {
				row++;
				column = 0;
			} else column++;
		}
		
	}
	
	public void setTileID(int x, int y) {								//used to remember the clicked tile
		
		int column = x / 50;
		int row = y / 50;
		int tileID = row * 4 + column;
		
		if(tileID < window.actualMap.tileset.size()) {
			actualTile = tileID;
		}
	}

}
