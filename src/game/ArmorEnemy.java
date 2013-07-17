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
	 * Erstelle einen Rüstungsgegner
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
	 * Gebe Schaden des Rüstungsgegners aus
	 * @return Damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Leben des Rüstungsgegner aus
	 * @return Life
	 */
	public int getLife (){
		return life;
	}
	
	/**
	 * Ziehe Schaden vom Leben des Rüstungsgegners
	 * @param dmg Schaden
	 */
	public void damage (int dmg){
		this.life -= dmg;
	}
	
	/**
	 * Gebe Sichtbarkeit des Rüstungsgegner aus
	 * @return Visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setzte Sichtbarkeit des Rüstungsgegners
	 * @param visible
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Parameter des Rüstungsgegners aus
	 * @return X-Parameter
	 */
	public int getX(){
		return x;
	}
	
	/**
	 *  Gebe Y-Parameter des Rüstungsgegners aus
	 * @return Y-Paramter
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gebe Bild des Rüstungsgegners aus
	 * @return Image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe des Rüstungsgegners aus
	 * @return X- und Y-Paramter, Breite und Höhe
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Bewege den Rüstungsgegner horizontal oder vertikal
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
	 * Ändere die Richtung bei Kollision mit einem Objekt
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