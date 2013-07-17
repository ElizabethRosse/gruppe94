package game;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tree {
	
	private int x, y, width, height;
	private Image image;
	
	/**
	 * Erstelle Baum
	 * @param x
	 * @param y
	 */
	public Tree(int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/tree.gif"));
		image = ii.getImage();
		width = image.getWidth(null);   //lädt Image, setzt Variablen
		height = image.getHeight(null);
		this.x = x;                     
		this.y = y;
	}
	
	/**
	 * Gebe X-Parameter des Baums aus
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des Baums aus
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Bild des Baums aus
	 * @return image
	 */
	public Image getImage() {   
		return image;
	}
	
	/**
	 * Gebe Größe des Baums aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {  //for Collision detect
		return new Rectangle(x, y, width, height);
	}
}
