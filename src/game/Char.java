package game;



import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Char {
	
	private int x, y, dx, dy, width, height, life, mana, maxmana, armor, direction;
	private Image image;
	private ArrayList<Arrow> arrows;
	private ArrayList<Feuerball> fball;
	
	public Char() {
		
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/char.gif")); // holt ein Bild fuer den Charakter
		image = ii.getImage();
		width = image.getWidth(null); //holt breite/höhe vom Bild
		height = image.getHeight(null);
		direction = 1;
		arrows = new ArrayList<Arrow>();
		fball = new ArrayList<Feuerball>();
		armor = 1;
		life = 6;
		mana = 100;
		maxmana = 100;
		x = 100;
		y = 220;
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
	
	public int getDirection() {
		return direction;
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
	 
	public ArrayList<Arrow> getArrows() {
		return arrows;
	}
	
	public ArrayList<Feuerball> getFBall() {
		return fball;
	}
	
	public void shoot() {
		if((direction>0)&&(direction<5)) {
			if (direction==1) arrows.add(new Arrow(x  +width  , y-3+height/2, direction));
			else if (direction==2) arrows.add(new Arrow(x-2        , y-3+height/2, direction));
			else if (direction==3) arrows.add(new Arrow(x-3+width/2, y  +height  , direction));
			else if (direction==4) arrows.add(new Arrow(x-3+width/2, y           , direction));
		}
	}
	
	public void cast() {
		if((direction>0)&&(direction<5)) {
			if (direction==1) fball.add(new Feuerball(x  +width  , y-3+height/2, direction));
			else if (direction==2) fball.add(new Feuerball(x-2        , y-3+height/2, direction));
			else if (direction==3) fball.add(new Feuerball(x-3+width/2, y  +height  , direction));
			else if (direction==4) fball.add(new Feuerball(x-3+width/2, y           , direction));
		}
	}
	 
	public void dmg (int dmg){
		switch(dmg%armor){
		case 0 : {
			this.life -= dmg/armor;
			break;
		}
		case 1 : {
			if((armor-1)==0) this.life -= dmg;
			else this.life -= dmg/(armor-1);
			break;
		}
		case 2 : {
			if((armor-2)==0) this.life -= dmg;
			else this.life -= dmg/(armor-2);
			break;
		}
		case 3 : {
			if((armor-3)==0) this.life -= dmg;
			else this.life -= dmg/(armor-3);
			break;
		}
		default : {
			this.life -= dmg;
			break;
		}
		}
	}
	
	public void manacost (int mana) {
		this.mana -= mana;
	}
	
	public int Mana() {
		return mana;
	}
	
	public void Manapotion() {
		this.mana = maxmana;
	}
	
	public void setMaxmana(int mana) {
		this.maxmana = mana;
		this.mana = maxmana;
	}
	
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	public int getArmor() {
		return armor;
	}
	
	public int getLife (){
		return life;
	}
	 
	public Rectangle getBounds() {
		 return new Rectangle(x, y, width, height);
	}
	 
	public void keyPressed(KeyEvent e) { //veränderung der Bewegungsvariablen
		 int key = e.getKeyCode();
		 
		 if (key == KeyEvent.VK_F){
			 if(mana>0) cast();
			 mana -= 20;
		 }
		 
		 if (key == KeyEvent.VK_SPACE) {
			 shoot();
		 }
		 
		 if (key == KeyEvent.VK_UP) {
			 dy = -1;
			 direction = 4;
		 }
		 
		 if (key == KeyEvent.VK_DOWN) {
			 dy = 1;
			 direction = 3;
		 }
		 
		 if (key == KeyEvent.VK_LEFT) {
			 dx = -1;
			 direction = 2;
		 }
		 
		 if (key == KeyEvent.VK_RIGHT) {
			 dx = 1;
			 direction = 1;
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
