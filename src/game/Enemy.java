package game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy {

	private int x, y, dx, dy, width, height, damage, direction;
	private boolean visible = true;
	private boolean change = false;
	private int life = 100;
	private Image image;

	/**
	 * Erstelle Teufelgegner
	 * @param x
	 * @param y
	 * @param dir
	 */
	public Enemy(int x, int y, int dir){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/enemy.gif"));
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
	 * Gebe Schaden des Teufelgegners aus
	 * @return
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Leben des Teufelgegners aus
	 * @return
	 */
	public int getLife (){
		return life;
	}
	
	/**
	 * Berechne Leben nach Schaden
	 * @param dmg
	 */
	public void damage (int dmg){
		this.life -= dmg;
	}
	
	/**
	 * Gebe Sichtbarkeit des Teufelgegners aus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des Teufelgegners
	 * @param visible
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Paramter des Teufelgegners aus
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gebe Y-Paramter des Teufelgegners aus
	 * @return y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gebe Bild des Teufelgegners aus
	 * @return image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe des Teufelgegners aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Bewege Teufelgegner
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
	 * Änderung Bewegungsrichtung bei Kollision mit Objekt
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

