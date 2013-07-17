package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Boss {

	private int x, dx, dy, y, damage;
	private boolean visible = true;
	private int life = 500;
	private Image image;
	
	/**
	 * Erstelle den ersten Boss
	 * @param x
	 * @param y
	 * @param direction
	 */
	public Boss(int x, int y, int dir){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/boss1.png"));
		image = ei.getImage();
		image = image.getScaledInstance(35, 35, -1);
		damage = 2;
		this.x = x;
		this.y = y;
		dx = 2;
		dy = 0;
	}
	
	/**
	 * Gebe Schaden des ersten Bosses aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Leben des ersten Bosses aus
	 * @return life
	 */
	public int getLife (){
		return life;
	}
	
	/**
	 * Ziehe Schaden vom Leben des ersten Bosses ab
	 * @param dmg
	 */
	public void damage (int dmg){
		this.life -= dmg;
	}
	
	/**
	 * Gebe Sichtbarkeit des ersten Bosses aus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des ersten Bosses
	 * @param visible
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Paramter des ersten Bosses aus
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 *  Gebe Y-Paramter des ersten Bosses aus
	 * @return y
	 */
	public int getY(){
		return y;
	}

	/**
	 * Gebe Bild des ersten Bosses aus
	 * @return image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe des ersten Bosses aus
	 * @return x, y
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, 35, 35);
	}
	
	/**
	 * Bewege den ersten Boss
	 */
	public void move() {
		x = x+dx;
		y = y+dy;
	}
	
	/**
	 * Ändere Richtung bei Kollision mit einem Objekt
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
