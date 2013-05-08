package hhu.ProPra_SoSe13.Gruppe94;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tree {
	
	private int x, y, width, height;
	private Image image;
	
	public Tree(int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/tree.gif"));
		image = ii.getImage();
		width = image.getWidth(null);   //lädt Image, setzt Variablen
		height = image.getHeight(null);
		this.x = x;                     
		this.y = y;
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
