package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class BlueEnemy {
	private int x, y, dx, dy, width, height, damage, direction;
	private boolean visible = true;
	private boolean change = false;
	private int life = 100;
	private Image image;

	
	/**
	 * Erstelle einen Geistergegner
	 * @param x
	 * @param y
	 * @param direction
	 */
	public BlueEnemy(int x, int y, int dir){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/ghost.png"));
		image = ei.getImage();
		damage = 1;
		width = image.getWidth(null);
		height = image.getHeight(null);
		dx = 1;
		dy = 1;
		this.x = x;
		this.y = y;
		direction = dir;
	}
	
	/**
	 * Gebe Schaden des Geistergegners aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Leben des Geistergegners aus
	 * @return life
	 */
	public int getLife (){
		return life;
	}
	
	/**
	 * Ziehe Schaden vom Leben des Geistergegners ab
	 * @param dmg
	 */
	public void damage (int dmg){
		this.life -= dmg;
	}
	
	/**
	 * Gebe Sichtbarkeit des Geistergegners aus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des Geistergegners
	 * @param visible
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Paramter des Geistergegners aus
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gebe Y-Paramter des Geistergegners aus 
	 * @return y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gebe Bild des Geistergegners aus
	 * @return image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe des Geistergegners aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Bewege den Geistergegner vertikal oder horizontal
	 */
	public void move() {
		switch(direction){
		case 1 : {
			if(change) x -= dx;
			else x += dx;
			break;
		}
		case 2 : {
			if(change) y -= dy;
			else y += dy;
			break;
		}
		default : break;
		}
	
	}
	
	/**
	 * Ändere die Richtung des Geistergegners bei Kollision mit einem Objekt
	 */
	public void movecollide() {
		if (direction == 1) {
			if(change) {
				change = false;
				x +=3;
			}
			else {
				change = true;
				x -= 3;
			}
		}
		if (direction == 2) {
			if (change) {
				change =false;
				y +=3;
			}
			else {
				change = true;
				y -=3;
			}
	}
	

}}
