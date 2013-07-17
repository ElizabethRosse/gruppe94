package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;


public class Sword implements Serializable {

	private static final long serialVersionUID = 1L;
	private int x, y, chax, chay;
	private Image image, imageD, imageR, imageL;
	boolean visible, forward;
	private int width, height,width2 ,height2, direction, damage;
	
	/**
	 * Erstelle Schwert
	 * @param x
	 * @param y
	 * @param dir
	 */
	public Sword(int x, int y, int dir) {
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("images/Sword.png"));
		image = ii.getImage();
				  ii = new ImageIcon(this.getClass().getResource("images/SwordD.png"));
		imageD = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		
				  ii = new ImageIcon(this.getClass().getResource("images/SwordR.png"));
		imageR = ii.getImage();
				  ii = new ImageIcon(this.getClass().getResource("images/SwordL.png"));
		imageL = ii.getImage();
		width2 = imageR.getWidth(null);
		height2 = imageR.getHeight(null);
		
		this.x = x;
		this.y = y;
		chax = x;
		chay = y;
		forward = true;
		damage = 5;
		direction = dir;
		visible = true;
	}
	
	/**
	 * Gebe Bild je nach Richtung aus
	 * @return image
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
	 * Gebe X-Parameter des Schwerts aus
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des Schwerts aus
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Schaden des Schwerts aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Sichtbarkeit des Schwerts aus
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des Schwerts
	 * @return x
	 */
	public void setVisible(boolean vis) {
		this.visible = vis;
	}
	
	/**
	 * Gebe Größe des Schwerts aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {
		switch(direction){
		case 1 : return new Rectangle(x, y, width2, height2); 
		case 2 : return new Rectangle(x, y, width2, height2);
		case 3 : return new Rectangle(x, y, width, height);
		case 4 : return new Rectangle(x, y, width, height);
		default: return new Rectangle(x, y, width2, height2);
		}
		
	}
	
	/**
	 * Bewegung des Schwerts
	 * @return x
	 */
	public void move() {
		switch(direction){
		case 1 : {
			if((x<(chax+7))&&forward) x += 1;
			else {
				forward = false;
				if (x != chax) x -= 1;
				else forward = true;
			}
			break;
		}
		case 2 : {
			if((x>(chax-7))&&forward) x -= 1;
			else {
				forward = false;
				if (x != chax) x += 1;
				else forward = true;
			}
			break;
		}
		case 3 : {
			if((y<(chay+7))&&forward) y += 1;
			else {
				forward = false;
				if (y != chay) y -= 1;
				else forward = true;
			}
			break;
		}
		case 4 : {
			if((y>(chay-7))&&forward) y -= 1;
			else {
				forward = false;
				if (y != chay) y += 1;
				else forward = true;
			}
			break;
		}
		default: {
			if((x<(chax+7))&&forward) x += 1;
			else {
				forward = false;
				if (x != chax) x -= 1;
				else forward = true;
			}
			break;
		}
		}
	}
}
