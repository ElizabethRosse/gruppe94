package game;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Manapotion {
	
	private int x, y, width, height;
	private Image image;
	private boolean visible = true;
	
	/**
	 * Erstelle Manatrank
	 * @param x
	 * @param y
	 */
	public Manapotion(int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/Manapotion.png"));
		image = ii.getImage();
		width = image.getWidth(null);   //lädt Image, setzt Variablen
		height = image.getHeight(null);
		this.x = x;                     
		this.y = y;
	}
	
	/**
	 * Gebe Sichtbarkeit des MP aus
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des MP
	 * @param vis
	 */
	public void setVisible(boolean vis) {
		this.visible = vis;
	}
	
	/**
	 * Gebe X-Parameter des MP aus
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des MP aus
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Bild des MP aus
	 * @return image
	 */
	public Image getImage() {   
		return image;
	}
	
	/**
	 * Gebe Größe des MP aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {  //for Collision detect
		return new Rectangle(x, y, width, height);
	}
}
