package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Feuerball {
	
	private int x,y, manacost;
	private Image image;
	boolean visible;
	private int width, height, direction, damage;
	
	private int FBall_Speed = 1;
	
	/**
	 * Erstelle Feuerball
	 * @param x
	 * @param y
	 * @param dir
	 */
	public Feuerball(int x, int y, int dir) {
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("images/fball.png"));
		image = ii.getImage();
		damage = 50;
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.direction = dir;
		manacost = 20;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gebe Bild des Feuerballs aus
	 * @return image
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Gebe Manakosten des Feuerballs aus
	 * @return manacost
	 */
	public int getCost() {
		return manacost;
	}
	
	/**
	 * Gebe X-Paramter des Feuerballs aus
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Y-Paramter des Feuerballs aus
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Schaden des Feuerballs aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Sichtbarkeit des Feuerballs aus
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des Feuerballs
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Gebe Größe des Feuerballs aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Bewege den Feuerball
	 */
	public void move() {
		if (direction == 1) x += FBall_Speed;
		if (direction == 2) x -= FBall_Speed;
		if (direction == 3) y += FBall_Speed;
		if (direction == 4) y -= FBall_Speed;
		if ((x<0)||(x>500)) visible = false;
		if ((y<0)||(y>500)) visible = false;
	}

}

