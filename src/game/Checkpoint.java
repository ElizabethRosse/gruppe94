package game;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Checkpoint {
	private int x, y, width, height, map;
	private Image imageac, imagein;
	private boolean activated = false;
	
	/**
	 * Erstelle Checkpoint
	 * @param x
	 * @param y
	 */
	public Checkpoint(int x, int y){
		ImageIcon ei;									//different images for activated and deactivated
			ei = new ImageIcon(this.getClass().getResource("images/checkpoint_activated.gif"));
		imageac = ei.getImage();
			ei = new ImageIcon(this.getClass().getResource("images/checkpoint_deactivated.gif"));
		imagein = ei.getImage();
		

		width = imageac.getWidth(null);
		height = imageac.getHeight(null);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Setze Aktivierung des Checkpoints
	 * @param vis
	 */
	public void setActivated(boolean vis) {						//changes state of checkpoint
		this.activated = vis;
	}
	
	/**
	 * Gebe X-Parameter des Checkpoints aus
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gebe Y-Parameter des Checkpoint aus
	 * @return
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gebe Map zurück
	 * @return map
	 */
	public int getMap(){
		return map;
	}
	
	/**
	 * Setze Map fest
	 * @param i
	 */
	public void setMap(int i){
		map = i;
		
	}
	
	/**
	 * Gebe Bild des aktiven Checkpoints aus
	 * @return imageac
	 */
	public Image getImageac(){
		return imageac;
	}
	
	/**
	 * Gebe Bild des inaktiven Checkpoints aus
	 * @return imagein
	 */
	public Image getImagein() {
		return imagein;
	}
	
	/**
	 * Gebe Größe des Checkpoint aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Gebe aus ob aktiviert
	 * @return activated
	 */
	public boolean active() {								//gives back activated if activated and deactivated if deactivated
		return activated;
	}
}
