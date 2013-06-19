package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Sword {

	private int x, y, chax, chay;
	private Image image, imageD, imageR, imageL;
	boolean visible, forward;
	private int width, height,width2 ,height2, direction, damage;
	
	public Sword(int x, int y, int dir) {
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("images/Sword.png"));
		image = ii.getImage();
				  ii = new ImageIcon(this.getClass().getResource("images/SwordD.png"));
		imageD = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		
				  ii = new ImageIcon(this.getClass().getResource("images/SwordR.png"));
		imageR = ii.getImage();
				  ii = new ImageIcon(this.getClass().getResource("images/SwordL.png"));
		imageL = ii.getImage();
		width2 = imageR.getWidth(null);
		height2 = imageR.getHeight(null);
		
		this.x = x;
		this.y = y;
		chax = x;
		chay = y;
		forward = true;
		damage = 5;
		direction = dir;
		visible = true;
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
	
	public void setVisible(boolean vis) {
		this.visible = vis;
	}
	
	public Rectangle getBounds() {
		switch(direction){
		case 1 : return new Rectangle(x, y, width2, height2); 
		case 2 : return new Rectangle(x, y, width2, height2);
		case 3 : return new Rectangle(x, y, width, height);
		case 4 : return new Rectangle(x, y, width, height);
		default: return new Rectangle(x, y, width2, height2);
		}
		
	}
	
	public void move() {
		switch(direction){
		case 1 : {
			if((x<(chax+7))&&forward) x += 1;
			else {
				forward = false;
				if (x != chax) x -= 1;
				else forward = true;
			}
			break;
		}
		case 2 : {
			if((x>(chax-7))&&forward) x -= 1;
			else {
				forward = false;
				if (x != chax) x += 1;
				else forward = true;
			}
			break;
		}
		case 3 : {
			if((y<(chay+7))&&forward) y += 1;
			else {
				forward = false;
				if (y != chay) y -= 1;
				else forward = true;
			}
			break;
		}
		case 4 : {
			if((y>(chay-7))&&forward) y -= 1;
			else {
				forward = false;
				if (y != chay) y += 1;
				else forward = true;
			}
			break;
		}
		default: {
			if((x<(chax+7))&&forward) x += 1;
			else {
				forward = false;
				if (x != chax) x -= 1;
				else forward = true;
			}
			break;
		}
		}
	}
}
