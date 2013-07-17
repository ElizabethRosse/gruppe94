package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class ArmorEnemy {
	private int x, y, dx, dy, width, height, damage, direction;
	private boolean visible = true;
	private boolean change = false;
	private int life = 100;
	private Image image;

	
	/**
	 * Erstelle einen R�stungsgegner
	 * @param x X-Parameter des Gegners
	 * @param y Y-Parameter des Gegners
	 * @param dir Richtung in die sich der Gegner bewegt
	 */
	public ArmorEnemy(int x, int y, int dir){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/armor.png"));
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
	 * Gebe Schaden des R�stungsgegners aus
	 * @return Damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Leben des R�stungsgegner aus
	 * @return Life
	 */
	public int getLife (){
		return life;
	}
	
	/**
	 * Ziehe Schaden vom Leben des R�stungsgegners
	 * @param dmg Schaden
	 */
	public void damage (int dmg){
		this.life -= dmg;
	}
	
	/**
	 * Gebe Sichtbarkeit des R�stungsgegner aus
	 * @return Visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setzte Sichtbarkeit des R�stungsgegners
	 * @param visible
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Parameter des R�stungsgegners aus
	 * @return X-Parameter
	 */
	public int getX(){
		return x;
	}
	
	/**
	 *  Gebe Y-Parameter des R�stungsgegners aus
	 * @return Y-Paramter
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gebe Bild des R�stungsgegners aus
	 * @return Image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Gr��e des R�stungsgegners aus
	 * @return X- und Y-Paramter, Breite und H�he
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Bewege den R�stungsgegner horizontal oder vertikal
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
	 * �ndere die Richtung bei Kollision mit einem Objekt
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
	

}
}