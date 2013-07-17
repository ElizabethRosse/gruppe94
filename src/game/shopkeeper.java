package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class shopkeeper {
	private int x, y, width, height;
	private Image image;
	private boolean visible = true;
	
	/**
	 * Erstelle Shopkeeper
	 * @param x
	 * @param y
	 */
	public shopkeeper(int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/shoptyp.png"));
		image = ii.getImage();
		width = image.getWidth(null);   //lädt Image, setzt Variablen
		height = image.getHeight(null);
		this.x = x;                     
		this.y = y;
	}
	
	/**
	 * Gebe X-Parameter des Shopkeepers aus
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Sichtbarkeit des Shopkeepers aus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Gebe Y-Paramter des Shopkeepers aus
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Bild des Shopkeepers aus
	 * @return image
	 */
	public Image getImage() {   
		return image;
	}
	
	/**
	 * Gebe Größe des Shopkeepers aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {  //for Collision detect
		return new Rectangle(x, y, width, height);
	}
}


