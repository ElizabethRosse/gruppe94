package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Xoss {


	private int x, dx, dy, y, width, height, damage, direction;
	private boolean visible = true;
	private int life = 500;
	private Image image;
	private boolean change =false;
	
	public Xoss(int x, int y, int dir){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/boss2.png"));
		image = ei.getImage();
		damage = 2;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
		direction = dir;
		dx = 2;
		dy = 0;
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

	public void setChange(){
		change = true;
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


