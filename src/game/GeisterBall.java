package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GeisterBall {
	
	private int x,y, manacost;
	private Image image, imageD, imageR, imageL;
	boolean visible;
	private int width, height, direction, damage;
	
	private int GBall_Speed = 1;
	
	/**
	 * Erstelle Geisterbälle
	 * @param x
	 * @param y
	 * @param dir
	 */
	public GeisterBall(int x, int y, int dir) {
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("images/spirit.png"));
		image = ii.getImage();
				  ii = new ImageIcon(this.getClass().getResource("images/spiritD.png"));
		imageD = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		
				  ii = new ImageIcon(this.getClass().getResource("images/spiritR.png"));
		imageR = ii.getImage();
				  ii = new ImageIcon(this.getClass().getResource("images/spiritL.png"));
		imageL = ii.getImage();
		
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
	 * Gebe Bild zurück je nach Richtung
	 * @return
	 */
	public Image getImage() {
		switch(direction){
		case 1 : return imageR; 
		case 2 : return imageL;
		case 3 : return imageD;
		case 4 : return image;
		default: return imageR;
		}
	}
	
	/**
	 * Gebe Mankosten aus
	 * @return
	 */
	public int getCost() {
		return manacost;
	}
	
	/**
	 * Gebe X-Parameter des Geisterballs aus
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des Geisterballs aus
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Schaden des Geisterballs aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Sichtbarkeit des Geisterballs aus
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des Geisterballs
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Gebe Größe des Geisterballs aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Bewege Geisterball
	 */
	public void move() {
		if (direction == 1) x += GBall_Speed;
		if (direction == 2) x -= GBall_Speed;
		if (direction == 3) y += GBall_Speed;
		if (direction == 4) y -= GBall_Speed;
		if ((x<0)||(x>500)) visible = false;
		if ((y<0)||(y>500)) visible = false;
	}

}
