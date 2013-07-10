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
	
	public int getDmg() {
		return damage;
	}
	
	public int getLife (){
		return life;
	}
	
	public void damage (int dmg){
		this.life -= dmg;
	}
	
	public boolean isVisible () {
		return visible;
	}
	
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImage(){
		return image;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
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