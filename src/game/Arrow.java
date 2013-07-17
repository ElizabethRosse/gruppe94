package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Erstelle ein Pfeil-Objekt
 *
 */
public class Arrow {
	
	private int x,y;
	private Image image;
	boolean visible;
	private int direction, damage;
	
	private int Arrow_Speed = 2;
	
	public Arrow(int x, int y, int dir) {
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("images/shuri.png"));
		image = ii.getImage();
		visible = true;
		damage = 20;
		this.direction = dir;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gebe Bild des Pfeils aus
	 * @return image
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Gebe X-Paramter des Pfeils aus
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Y-Paramter des Pfeils aus
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Schaden des Pfeils aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Sichtbarkeit des Pfeils aus
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des Pfeils fest 
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Gebe Größe des Pfeils aus
	 * @return x, y
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 6, 6);
	}
	
	/**
	 * Bewege den Pfeil
	 */
	public void move() {
		if (direction == 1) x += Arrow_Speed;
		if (direction == 2) x -= Arrow_Speed;
		if (direction == 3) y += Arrow_Speed;
		if (direction == 4) y -= Arrow_Speed;
		if ((x<0)||(x>500)) visible = false;
		if ((y<0)||(y>500)) visible = false;
	}

}

