package game;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class npc {
	
	private int x, y, width, height, dialog;
	private Image image;
	private boolean visible = true;
	
	/**
	 * Erstelle NPC
	 * @param x
	 * @param y
	 * @param dialog
	 */
	public npc(int x, int y, int dialog) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/npc.png"));
		image = ii.getImage();
		width = image.getWidth(null);   //lädt Image, setzt Variablen
		height = image.getHeight(null);
		this.dialog = dialog;
		this.x = x;                     
		this.y = y;
	}
	
	/**
	 * Gebe Dialog des NPCs zurück
	 * @return
	 */
	public int getDialog() {
		return dialog;
	}
	
	/**
	 * Gebe X-Paramter des NPCs aus
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gebe Sichtbarkeit des NPCs aus
	 * @return visible
	 */
	public boolean isVisible () {
		return visible;
	}
	
	/**
	 * Gebe Y-Paramter des NPCs aus
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gebe Bild des NPCs aus
	 * @return image
	 */
	public Image getImage() {   
		return image;
	}
	
	/**
	 * Gebe Größe des NPCs aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {  //for Collision detect
		return new Rectangle(x, y, width, height);
	}
}

