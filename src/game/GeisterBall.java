package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GeisterBall {
	
	private int x,y, manacost;
	private Image image, imageD, imageR, imageL;
	boolean visible;
	private int width, height, direction, damage;
	
	private int GBall_Speed = 1;
	
	public GeisterBall(int x, int y, int dir) {
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("images/spirit.png"));
		image = ii.getImage();
				  ii = new ImageIcon(this.getClass().getResource("images/spiritD.png"));
		imageD = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		
				  ii = new ImageIcon(this.getClass().getResource("images/spiritR.png"));
		imageR = ii.getImage();
				  ii = new ImageIcon(this.getClass().getResource("images/spiritL.png"));
		imageL = ii.getImage();
		
		damage = 50;
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.direction = dir;
		manacost = 20;
		this.x = x;
		this.y = y;
	}
	
	public Image getImage() {
		switch(direction){
		case 1 : return imageR; 
		case 2 : return imageL;
		case 3 : return imageD;
		case 4 : return image;
		default: return imageR;
		}
	}
	
	public int getCost() {
		return manacost;
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
		if (direction == 1) x += GBall_Speed;
		if (direction == 2) x -= GBall_Speed;
		if (direction == 3) y += GBall_Speed;
		if (direction == 4) y -= GBall_Speed;
		if ((x<0)||(x>500)) visible = false;
		if ((y<0)||(y>500)) visible = false;
	}

}
