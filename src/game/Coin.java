package game;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Coin {
	
	private int x, y, width, height;
	private Image image;
	private boolean visible = true;
	
	public Coin(int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/Coins.png"));
		image = ii.getImage();
		width = image.getWidth(null);   //lädt Image, setzt Variablen
		height = image.getHeight(null);
		this.x = x;                     
		this.y = y;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean vis) {
		this.visible = vis;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public Image getImage() {   
		return image;
	}
	
	public Rectangle getBounds() {  //for Collision detect
		return new Rectangle(x, y, width, height);
	}
}
