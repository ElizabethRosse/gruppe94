package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Zoss {

	private int x, dx, dy, y, damage;
	private boolean visible = true;
	private int life = 1000;
	private Image image;
	
	/**
	 * Erstelle dritten Boss
	 * @param x
	 * @param y
	 * @param dir
	 */
	public Zoss(int x, int y, int dir){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/endboss.png"));
		image = ei.getImage();
		image = image.getScaledInstance(75, 75, -1);
		damage = 2;
		this.x = x;
		this.y = y;
		dx = 2;
		dy = 0;
	}
	
	/**
	 * Gebe Schaden des dritten Bosses aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Leben des dritten Bosses aus
	 * @return life
	 */
	public int getLife (){
		return life;
	}
	
	/**
	 * Berechne Leben nach Schaden
	 */
	public void damage (int dmg){
		this.life -= dmg;
	}
	
	/**
	 * Gebe Sichtbarkeit des dritten Bosses aus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des dritten Bosses
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Parameter des dritten Bosses aus
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des dritten Bosses aus
	 * @return y
	 */
	public int getY(){
		return y;
	}

	/**
	 * Gebe Bild des dritten Bosses aus
	 * @return image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe des dritten Bosses aus
	 * @return x, y, 35, 35
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, 35, 35);
	}
	
	/**
	 * Gebe GrößeZ des dritten Bosses aus
	 * @return x, y, 75, 75
	 */
	public Rectangle getBoundsZ() {
		return new Rectangle(x, y, 75, 75);
	}
	
	/**
	 * Bewege den dritten Boss
	 */
	public void move() {
		x = x+dx;
		y = y+dy;
	
	}
	
	/**
	 * Ändere Bewegungsrichtung bei Kollision
	 */
	public void movecollide() {
		if (dx>0) {
			x = x-3;
			dx = 0;
			dy = 2;
		}
		else {if(dy > 0){
			y = y-3;
			dx = -2;
			dy = 0;
			}
		else if(dx < 0) {
			x = x+3;
			dx = 0;
			dy = -2;
		}else {
			y = y+3;
			dx = 2;
			dy = 0;
		}
		}
		
	}
}


