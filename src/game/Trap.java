package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Trap {

	private int x, y, width, height, damage;
	private boolean visible = true;
	private Image image;
	
	public Trap(int x, int y){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/trap.png"));
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
