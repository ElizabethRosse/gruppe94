package game;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Checkpoint {
	private int x, y, width, height;
	private Image imageac, imagein;
	private boolean activated = false;
	
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
	public void setActivated(boolean vis) {						//changes state of checkpoint
		this.activated = vis;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImageac(){
		return imageac;
	}
	
	public Image getImagein() {
		return imagein;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	public boolean active() {								//gives back activated if activated and deactivated if deactivated
		return activated;
	}
}
