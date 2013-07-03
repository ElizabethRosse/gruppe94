package game;



import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Char {
	

	private int xp, lvl, x, y, dx, dy, width, height, life, health, maxhealth, mana, maxmana, armor, direction,gold;
	private boolean change, move, sword;
	private boolean Smile, Sword, Arrow;
	private Image image, smile;
	private ArrayList<Arrow> arrows;
	private ArrayList<Feuerball> fball;
	private Sword Mastersword = new Sword(x,y,direction);
	private int fdmg = 50;
	private int sdmg = 5;
	
	public Char() {
		
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/smile.gif")); // holt ein Bild fuer den Charakter
		image = ii.getImage();
		ii = new ImageIcon(this.getClass().getResource("images/Char.png"));
		smile = ii.getImage();
		change = false;
		move = true;
		sword = false;
		width = image.getWidth(null); //holt breite/höhe vom Bild
		height = image.getHeight(null);
		direction = 1;
		arrows = new ArrayList<Arrow>();
		fball = new ArrayList<Feuerball>();
		gold = 0;
		armor = 1;
		life = 3;
		health = 6;
		maxhealth = 6;
		mana = 100;
		maxmana = 100;
		Sword = false;
		Arrow = false;
		Smile = false;
		x = 100;
		y = 220;
		xp = 0;
		lvl = 1;
	}
	
	public void setLVL(int lvl){
		this.lvl = lvl;
	}
	
	public int getLVL(){
		return lvl;
	}
	
	public int getFDMG(){
		return fdmg;
	}
	
	public void addFDMG (int fdmg){
		this.fdmg = this.fdmg + fdmg; 
	}
	
	public int getSDMG(){
		return sdmg;
	}
	
	public void addSDMG (int sdmg){
		this.sdmg = this.sdmg + sdmg;
	}
	
	public void makeSmile() {
		Smile = true;
	}
	
	public void makeSword() {
		Sword = true;
	}
	
	public void makeArrow() {
		Arrow = true;
	}
	
	public void move() { //bewegung mithilfe der Bewegungsvariablen
		if(move) {
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
	
	public void addXP (int xp) {
		this.xp = this.xp + xp;
	}
	
	public int getXP() {
		return xp;
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
	
	public void Continue(){
		this.life -= 1;
	}
	
	public int getContinues() {
		return life;
	}
	 
	public Image getImage() {
		if (change&&(mana>0)) return smile;
		else {
			if(change) change = false;
			return image;
		}
	}
	
	public boolean getSmile() {
		return change;
	}
	 
	public ArrayList<Arrow> getArrows() {
		return arrows;
	}
	
	public ArrayList<Feuerball> getFBall() {
		return fball;
	}
	
	public void shoot() {
			if (direction==1) arrows.add(new Arrow(x  +width  , y-3+height/2, direction));
			else if (direction==2) arrows.add(new Arrow(x-2        , y-3+height/2, direction));
			else if (direction==3) arrows.add(new Arrow(x-3+width/2, y  +height  , direction));
			else if (direction==4) arrows.add(new Arrow(x-3+width/2, y           , direction));
	}
	
	public void cast() {
			if (direction==1) fball.add(new Feuerball(x  +width  , y-3+height/2, direction));
			else if (direction==2) fball.add(new Feuerball(x-2        , y-3+height/2, direction));
			else if (direction==3) fball.add(new Feuerball(x-3+width/2, y  +height  , direction));
			else if (direction==4) fball.add(new Feuerball(x-3+width/2, y           , direction));
	}
	 
	public void dmg (int dmg){
		switch(dmg%armor){
		case 0 : {
			this.health -= dmg/armor;
			break;
		}
		case 1 : {
			if((armor-1)==0) this.health -= dmg;
			else this.health -= dmg/(armor-1);
			break;
		}
		case 2 : {
			if((armor-2)==0) this.health -= dmg;
			else this.health -= dmg/(armor-2);
			break;
		}
		case 3 : {
			if((armor-3)==0) this.health -= dmg;
			else this.health -= dmg/(armor-3);
			break;
		}
		default : {
			this.life -= dmg;
			break;
		}
		}
	}
	
	public boolean getST() {
		return sword;
	}
	
	public void sword() {
		if(direction==1) Mastersword = new Sword(x-2+width, y-1+height/2, 1);
		if(direction==2) Mastersword = new Sword(x-13, y-1+height/2, 2);
		if(direction==3) Mastersword = new Sword(x+1, y-1+height, 3);
		if(direction==4) Mastersword = new Sword(x-1+width/2, y-13, 4);
	}
	
	public Sword getSword() {
		return Mastersword;
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
	
	public void setMaxhealth(int health) {
		this.maxhealth = health;
		this.health = maxhealth;
	}
	
	public int getMaxhealth() {
		return maxhealth;
	}
	
	public void Healthpotion() {
		this.health = maxhealth;
	}
	
	public void buyHealth() {
		gold -= 3;
	}
	
	public void setGold() {
		 gold = gold + 1;
	}
	
	public void setGoldtest(int i)   {
		gold = i;
	}
	
	public int getGold() {
		 return gold;
	}
	
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	public int getArmor() {
		return armor;
	}
	
	public int gethealth (){
		return health;
	}
	public int getmana (){
		return mana;
	}
	 
	public Rectangle getBounds() {
		 return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBoundsSmile() {
		return new Rectangle(x-15, y-15, width+30, height+30);
	}
	 
	public void keyPressed(KeyEvent e) { //veränderung der Bewegungsvariablen
		 int key = e.getKeyCode();
		 
		 if (key == KeyEvent.VK_D){
			 if(Smile){
				 if(mana>0) {change = true;
				 			move = false;
				 }
			 }
		 }
		 
		 if (key == KeyEvent.VK_G){
			 if(Sword) {sword = true;
			 move = false;
			 sword();}
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
		 
		 if (key == KeyEvent.VK_D){
			 change = false;
			 move = true;
		 }
		 
		 if (key == KeyEvent.VK_G){
			 sword = false;
			 move = true;
		 }
		 
		 if (key == KeyEvent.VK_F){
			 if(mana>19) {cast();
			 mana -= 20;}
		 }
		 
		 if (key == KeyEvent.VK_SPACE) {
			 if(Arrow) shoot();
		 }
		 
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
