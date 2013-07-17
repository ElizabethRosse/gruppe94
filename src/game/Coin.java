package game;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Coin {
	
	private int x, y, width, height;
	private Image image;
	private boolean visible = true;
	
	/**
	 * Erstelle M�nzen
	 * @param x
	 * @param y
	 */
	public Coin(int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/Coins.png"));
		image = ii.getImage();
		width = image.getWidth(null);   //l�dt Image, setzt Variablen
		height = image.getHeight(null);
		this.x = x;                     
		this.y = y;
	}
	
	/**
	 * Gebe Sichtbarkeit aus
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit
	 * @param vis
	 */
	public void setVisible(boolean vis) {
		this.visible = vis;
	}
	
	/**
	 * Gebe X-Paramter der M�nze aus
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Y-Paramter der M�nze aus
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Bild der M�nze aus
	 * @return image
	 */
	public Image getImage() {   
		return image;
	}
	
	/**
	 * Gebe Gr��e der M�nze aus
	 * @return x, y, breite, h�he
	 */
	public Rectangle getBounds() {  //for Collision detect
		return new Rectangle(x, y, width, height);
	}
}
