package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class dog {


	private int x, y, width, height;
	private boolean visible = true;
	private Image image;

	
	/**
	 * Erstelle Hund
	 * @param x
	 * @param y
	 */
	public dog(int x, int y){
		ImageIcon ei = new ImageIcon(this.getClass().getResource("images/dog.png"));
		image = ei.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gebe Sichtbarkeit des Hundes saus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Setze SIhtbarkeit des Hundes
	 * @param visible
	 */
	public void setVisible (boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Gebe X-Paramter des Hundes aus
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gebe Y-Paramter des Hundes aus
	 * @return y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gebe Bild des Hundes aus
	 * @return image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Gebe Größe des Hunde aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
}


