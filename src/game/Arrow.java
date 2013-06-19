package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Arrow {
	
	private int x,y;
	private Image image;
	boolean visible;
	private int width, height, direction, damage;
	
	private int Arrow_Speed = 2;
	
	public Arrow(int x, int y, int dir) {
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("images/shuri.png"));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;
		damage = 20;
		this.direction = dir;
		this.x = x;
		this.y = y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDmg() {
		return damage;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void move() {
		if (direction == 1) x += Arrow_Speed;
		if (direction == 2) x -= Arrow_Speed;
		if (direction == 3) y += Arrow_Speed;
		if (direction == 4) y -= Arrow_Speed;
		if ((x<0)||(x>500)) visible = false;
		if ((y<0)||(y>500)) visible = false;
	}

}

