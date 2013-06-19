package game;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class npc {
	
	private int x, y, width, height;
	private Image image;
	private boolean visible = true;
	
	public npc(int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/npc.png"));
		image = ii.getImage();
		width = image.getWidth(null);   //lädt Image, setzt Variablen
		height = image.getHeight(null);
		this.x = x;                     
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public boolean isVisible () {
		return visible;
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

