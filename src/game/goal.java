package game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class goal {

	private int x, y, width, height;
	private Image image;
	
	/**
	 * Erstelle Ziel
	 * @param x
	 * @param y
	 */
	public goal(int x, int y){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/goal.gif"));
		image = ei.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gebe X-Parameter des Ziels aus
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des Ziel aus
	 * @return
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gebe Bild des Ziels aus
	 * @return image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe des Ziels aus
	 * @return x, y , breite, höhe
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public void moveforwards (int dx){
		x = x - dx;
	}
	
	public void movebackwards (int dx){
		x = x + dx;
	}
	
	public void moveupwards (int dy){
		y = y - dy;
	}
	
	public void movedownwards (int dy){
		y = y + dy;
	}
	
}