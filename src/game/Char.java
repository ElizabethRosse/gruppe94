package game;



import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Char {
	
	private int x, y, dx, dy, width, height;
	private Image image;
	private boolean visible;
	
	public Char() {
		
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/char.gif")); // holt ein Bild fuer den Charakter
		image = ii.getImage();
		width = image.getWidth(null); //holt breite/höhe vom Bild
		height = image.getHeight(null);
		visible = true;
		x = 100;  //standart Startpunkt (linke Seite mittig)
		y = 225;
	}
	
	public void move() { //bewegung mithilfe der Bewegungsvariablen
		
		x += dx;
		y += dy;
		
		if (x < 1) { //verhindert verlassen des Sichtbereichs nach links
			x = 1;
		}
		if (x >= 500) {  //verhindert verlassen des Sichtbereichs nach rechts
			x = 500;
		}
		if (y < 1) {  //verhindert verlassen des Sichtbereichs nach oben
			y = 1;
		}
		if (y >= 500) {  //verhindert verlassen des Sichtbereichs nach unten
			y = 500;
		}
	}
	
	public int getX() {
		 return x;
	}
	 
	public void addX(int x) {
		 this.x = this.x + x;
	}
	 
	public void addY(int y) {
		 this.y = this.y + y;
	}
	
	public int getY() {
		 return y;
	}
	
	public void setX(int x) { //char-positionierung (x,y) bei mapwechsel
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	 
	public int getDX() {
		 return dx;
	}
	 
	public int getDY() {
		 return dy;
	}
	 
	public void setDX(int x) { //zum stoppen der Bewegung bei berührung mit einem Baum
		 this.dx = x;
	}
	 
	public void setDY(int y) {
		 this.dy = y;
	}
	 
	public Image getImage() {
		 return image;
	}
	 
	public void setVisible(boolean visible) {
		 this.visible = visible;  //verändern der Sichtbarkeit zB. bei Tot
	}
	 
	public boolean isVisible() {
		 return visible;
	}
	 
	public Rectangle getBounds() {
		 return new Rectangle(x, y, width, height);
	}
	 
	public void keyPressed(KeyEvent e) { //veränderung der Bewegungsvariablen
		 int key = e.getKeyCode();
		 
		 if (key == KeyEvent.VK_UP) {
			 dy = -1;
		 }
		 
		 if (key == KeyEvent.VK_DOWN) {
			 dy = 1;
		 }
		 
		 if (key == KeyEvent.VK_LEFT) {
			 dx = -1;
		 }
		 
		 if (key == KeyEvent.VK_RIGHT) {
			 dx = 1;
		 }
	}
	 
	public void keyReleased(KeyEvent e) { //zurücksetzten der Bewegungsvariablen
		 int key = e.getKeyCode();
		 
		 if (key == KeyEvent.VK_UP) {
			 dy = 0;
		 }
		 
		 if (key == KeyEvent.VK_DOWN) {
			 dy = 0;
		 }
		 
		 if (key == KeyEvent.VK_LEFT) {
			 dx = 0;
		 }
		 
		 if (key == KeyEvent.VK_RIGHT) {
			 dx = 0;
		 }
	}
}
