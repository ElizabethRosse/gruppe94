package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RepaintManager;

public class MapSurface extends JPanel {
	
	MapEditor window;
	JScrollPane scroll = new JScrollPane();
	RepaintManager m;
	
	public MapSurface(MapEditor Window) {
		
		window = Window;
		setDoubleBuffered(true);
		changeMap();
		m = RepaintManager.currentManager(window);
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				paintTile(e.getX(), e.getY());							//painting the current tile at mouse position
			}
		});
		
		addMouseMotionListener( new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e) {					//painting the current tile at mouse position (moving)
				paintTile(e.getX(), e.getY());
			}
		});
	}
	
	public void paintComponent (Graphics g) {
		
		Rectangle r = g.getClipBounds();
		int startx = r.x;
		int starty = r.y;
		int endx = startx + r.width;
		int endy = starty + r.height;
		
		startx = startx / 50;
		starty = starty / 50;
		endx = endx / 50;
		endy = endy / 50;
		
		if(endx < window.actualMap.map.length) {											//used to paint an "extra" placeholder
			endx++;
		}
		
		if(endy < window.actualMap.map[0].length) {
			endy++;
		}
		
		for(int x = startx; x < endx; x++) {												//paints the current tile
			
			for(int y = starty; y < endy; y++) {
				BufferedImage tile = window.actualMap.getTileImage(x,y);
				g.drawImage(tile, x*50, y*50, this);
			}
		}
		
	}
	
	public void changeMap() {
		
		int dx =  window.actualMap.map.length;												//used to fast change the map, but only repaint the current visible area
		int dy =  window.actualMap.map[0].length;
		
		setPreferredSize(new Dimension(dx * 50, dy * 50));
		scroll.setViewportView(this);
	}
	
	public void paintTile(int x, int y) {
		
		x = x / 50;																			// divide through 50 to know in which array object the user has clicked
		y = y / 50;
		window.actualMap.map[x] [y] = window.palette.actualTile;							//loads the current (clicked) tile in the array
		Rectangle r = scroll.getViewport().getViewRect();
		int dx=this.scroll.getLocation().x + window.getInsets().left - r.x;					//finding the x position of the visible left side
		int dy=this.scroll.getLocation().y + window.getInsets().top - r.y;					//finding the y position of the visible top side
		m.addDirtyRegion(window ,dx + x*50,dy + y*50, 50, 50);											//repaint on clicked position
	}
}
