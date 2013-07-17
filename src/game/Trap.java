package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Trap {

	private int x, y, width, height, damage;
	private boolean visible = true;
	private Image image;
	
	/**
	 * Erstelle Falle
	 * @param x
	 * @param y
	 */
	public Trap(int x, int y){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/trap.png"));
		image = ei.getImage();
		damage = 2;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gebe Schaden der Falle aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	
	/**
	 * Gebe Sichtbarkeit der Falle aus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit der Falle
	 * @return visible
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Parameter der Falle aus
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gebe Y-Parameter der Falle aus
	 * @return y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gebe Bild der Falle aus
	 * @return image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe der Falle aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
}
