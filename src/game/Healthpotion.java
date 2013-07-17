package game;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Healthpotion {
	
	private int x, y, width, height;
	private Image image;
	private boolean visible = true;
	
	/**
	 * Erstelle Lebenstrank
	 * @param x
	 * @param y
	 */
	public Healthpotion(int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/Healthpotion.png"));
		image = ii.getImage();
		width = image.getWidth(null);   //lädt Image, setzt Variablen
		height = image.getHeight(null);
		this.x = x;                     
		this.y = y;
	}
	
	/**
	 * Gebe Sichtbarkeit des HP aus
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des HP
	 * @param vis
	 */
	public void setVisible(boolean vis) {
		this.visible = vis;
	}
	
	/**
	 * Gebe X-Parameter des HP
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des HP
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Bild des HP aus
	 * @return image
	 */
	public Image getImage() {   
		return image;
	}
	
	/**
	 * Gebe Größe des HP aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {  //for Collision detect
		return new Rectangle(x, y, width, height);
	}
}
