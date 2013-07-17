package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Xoss {


	private int x, dx, dy, y, damage, direction, width, height;
	private boolean visible = true;
	private int life = 500;
	private Image image;
	private boolean Arrow;
	private ArrayList<Arrow> arrows = new ArrayList<Arrow>();
	
	/**
	 * Erstelle zweiten Boss
	 * @param x
	 * @param y
	 * @param dir
	 */
	public Xoss(int x, int y, int dir){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/boss2.png"));
		image = ei.getImage();
		image = image.getScaledInstance(50, 50, -1);
		damage = 2;
		this.x = x;
		this.y = y;
		direction = 3;
		dx = 2;
		dy = 0;
		Arrow = false;
		width = image.getWidth(null); //holt breite/höhe vom Bild
		height = image.getHeight(null);
		
		
	}
	
	/**
	 * Gebe Schaden des zweiten Bosses aus
	 * @return damage
	 */
	public int getDmg() {
		return damage;
	}
	
	/**
	 * Gebe Leben des zweiten Bosses aus
	 * @return life
	 */
	public int getLife (){
		return life;
	}
	
	/**
	 * Berechne Leben nach Schaden des zweiten Bosses
	 */
	public void damage (int dmg){
		this.life -= dmg;
	}
	
	/**
	 * Gebe Sichtbarkeit des zweiten Bosses aus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setze Sichtbarkeit des zweiten Bosses
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Parameter des zweiten Bosses aus
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des zweiten Bosses aus
	 * @return y
	 */
	public int getY(){
		return y;
	}

	/**
	 * Gebe Bild des zweiten Bosses aus
	 * @return image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe des zweiten Bosses aus
	 * @return x+15, y, 35, 35
	 */
	public Rectangle getBounds(){
		return new Rectangle(x+15, y, 35, 35);
	}
	
	/**
	 * Gebe GrößeX des zweiten Bosses aus
	 * @return x+10, y, 40, 50
	 */
	public Rectangle getBoundsX() {
		return new Rectangle(x+10, y, 40, 50);
	}
	/**
	 * Gebe Arraylist des zweiten Bosses aus
	 * @return arrows
	 */
	public ArrayList<Arrow> getBossArrows() {
		return arrows;
	}
	
	/**
	 * Erstelle neue Arrayliste des zweiten Bosses
	 */
	public void resArrows() {
		arrows = new ArrayList<Arrow>();
	}
	
	/**
	 * Schieße Pfeile
	 */
	public void shoot() {
		if (direction==1) arrows.add(new Arrow(x  +width  , y-3+height/2, direction));
		else if (direction==2) arrows.add(new Arrow(x-2        , y-3+height/2, direction));
		else if (direction==3) arrows.add(new Arrow(x-3+width/2, y  +height  , direction));
		else if (direction==4) arrows.add(new Arrow(x-3+width/2, y           , direction));
	}

	/**
	 * Ändere Bewegungsrichtung des zweiten Bosses
	 * @param x
	 * @param y
	 */
	public void changedirection(int x, int y) {
		double h = Math.random();
		/*if (h < 0.25) direction = 1;
		else if (h > 0.25 && h < 0.5) direction = 2;
		else if (h > 0.5 && h < 0.75) direction = 3;
		else direction = 4;*/
		if (x> this.x && y> this.y) {
			if(h>0.5) direction = 3;
			else direction = 1;
		}
		else if (x< this.y && y< this.y){
			if(h>0.5) direction = 2;
			else direction = 4;
		}
		else if (x> this.x && y< this.y){
			if ( h>0.5) direction = 4;
			else direction = 1;
		}
		else if (h>0.5) direction = 3;
		else direction = 2;
	}
	
	/**
	 * Bewege zweiten Boss
	 */
	public void move() {
		x = x+dx;
		y = y+dy;
	}
	
	/**
	 * Ändere Bewegungsrichtung des zweiten Bosses
	 */
	public void movecollide() {
		if (dx>0) {
			direction = 2;
			x = x-3;
			dx = 0;
			dy = 2;
		}
		else {if(dy > 0){
			direction = 4;
			y = y-3;
			dx = -2;
			dy = 0;
			}
		else if(dx < 0) {
			direction = 1;
			x = x+3;
			dx = 0;
			dy = -2;
		}else {
			direction = 3;
			y = y+3;
			dx = 2;
			dy = 0;
		}
	}
	
}
}


