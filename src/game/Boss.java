package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Boss {

	private int x, y, width, height, damage;
	private boolean visible = true;
	private int life = 500;
	private Image image;
	
	public Boss(int x, int y){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/enemy.gif"));
		image = ei.getImage();
		damage = 2;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
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
}
