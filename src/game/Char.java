package game;



import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Char {
	

	private int xp, lvl, x, y, dx, dy, width, height, life, health, maxhealth, mana, maxmana, armor, direction,gold, manapotion, healthpotion;
	private boolean change, move, sword;
	private boolean Smile, Sword, Arrow;
	private Image image, smile;
	private ArrayList<Arrow> arrows;
	private ArrayList<Feuerball> fball;
	private ArrayList<GeisterBall> gball;
	private Sword Mastersword = new Sword(x,y,direction);
	private int fdmg, gdmg = 50;
	private int sdmg = 5;
	
	/**
	 * Erstelle den Character
	 */
	public Char()  {
		
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("images/smile.gif")); // holt ein Bild fuer den Charakter
		image = ii.getImage();
		ii = new ImageIcon(this.getClass().getResource("images/Char.png"));
		smile = ii.getImage();
		change = false;
		move = true;
		sword = false;
		manapotion = 0;
		healthpotion = 0;
		width = image.getWidth(null); //holt breite/höhe vom Bild
		height = image.getHeight(null);
		direction = 1;
		arrows = new ArrayList<Arrow>();
		fball = new ArrayList<Feuerball>();
		gball = new ArrayList<GeisterBall>();
		gold = 0;
		armor = 1;
		life = 3;
		health = 6;
		maxhealth = 6;
		mana = 100;
		maxmana = 100;
		fdmg = 50;
		Sword = false;
		Arrow = false;
		Smile = false;
		x = 100;
		y = 220;
		xp = 0;
		lvl = 1;
	}
	
	/**
	 * Setze Manatränke des Characters
	 */
	public void setManapotion() {
		manapotion += 1;
	}
	
	/**
	 * Gebe aus ob der Character Smile besitzt
	 * @return smile
	 */
	public boolean haveSmile() {
		return Smile;
	}
	
	/**
	 * Gebe aus wieviele Manatränke der Character besitzt
	 * @return
	 */
	public int getManapotion() {
		return manapotion;
	}
	
	/**
	 * Setze die Lebenstränke des Characters
	 */
	public void setHealthpotion() {
		healthpotion += 1;
	}
	
	/**
	 * Gebe die Lebenstränke des Characters aus
	 * @return healthpotion
	 */
	public int getHealthpotion() {
		return healthpotion;
	}
	
	/**
	 * Setze das Level des Characters
	 * @param lvl
	 */
	public void setLVL(int lvl){
		this.lvl = lvl;
	}
	
	/**
	 * Gebe das Level des Characters aus
	 * @return
	 */
	public int getLVL(){
		return lvl;
	}
	
	/**
	 * Gebe den Schaden des Geisterballs aus
	 * @return gdmg
	 */
	public int getGDMG() {
		return gdmg;
	}
	
	/**
	 * Addiere den Schaden des Geisterballs
	 * @param gdmg
	 */
	public void addGDMG (int gdmg){
		this.gdmg = this.gdmg + gdmg;
	}
	
	/**
	 * Gebe den Schaden des Feuerballs aus
	 * @return fdmg
	 */
	public int getFDMG(){
		return fdmg;
	}
	
	/**
	 * Addiere den Schaden des Feuerballs
	 * @param fdmg
	 */
	public void addFDMG (int fdmg){
		this.fdmg = this.fdmg + fdmg; 
	}
	
	/**
	 * Gebe den Schaden des Schwerts aus
	 * @return sdmg
	 */
	public int getSDMG(){
		return sdmg;
	}
	
	/**
	 * Addiere den Schaden des Schwerts
	 * @param sdmg
	 */
	public void addSDMG (int sdmg){
		this.sdmg = this.sdmg + sdmg;
	}
	
	/**
	 * Erstelle den Smile des Characters
	 */
	public void makeSmile() {
		Smile = true;
	}
	
	/**
	 * Erstelle das Schwert des Characters
	 */
	public void makeSword() {
		Sword = true;
	}
	
	/**
	 * Gebe aus ob der Character das Schwert besitzt
	 * @return Sword
	 */
	public boolean haveSword() {
		return Sword;
	}
	
	/**
	 * Erstelle die Pfeile des Characters
	 */
	public void makeArrow() {
		Arrow = true;
	}
	
	/**
	 * Gebe aus ob der Character Pfeile besitzt
	 * @return Arrow
	 */
	public boolean haveArrow() {
		return Arrow;
	}
	
	/**
	 * Bewegung des Characters
	 */
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
	
	/**
	 * Gebe X-Parameter des Characters aus
	 * @return x
	 */
	public int getX() {
		 return x;
	}
	
	/**
	 * Gebe Bewegungsrichtung des Characters aus
	 * @return
	 */
	public int getDirection() {
		return direction;
	}
	 
	/**
	 * Addiere X-Paramter
	 * @param x
	 */
	public void addX(int x) {
		 this.x = this.x + x;
	}
	
	/**
	 * Addiere Y-Paramter
	 * @param y
	 */
	public void addY(int y) {
		 this.y = this.y + y;
	}
	
	/**
	 * Gebe Y-Paramter aus
	 * @return y
	 */
	public int getY() {
		 return y;
	}
	
	/**
	 * Addiere XP
	 * @param xp
	 */
	public void addXP (int xp) {
		this.xp = this.xp + xp;
	}
	
	/**
	 * Gebe XP des Characters aus
	 * @return xp
	 */
	public int getXP() {
		return xp;
	}
	
	/**
	 * Setze den X-Paramter des Characters
	 * @param x
	 */
	public void setX(int x) { //char-positionierung (x,y) bei mapwechsel
		this.x = x;
	}
	
	/**
	 * Setze den Y-Paramter des Characters
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	 
	/**
	 * Gebe Bewegungsgeschwindigkeit auf der X-Achse aus
	 * @return dx
	 */
	public int getDX() {
		 return dx;
	}
	 
	/**
	 * Gebe Bewegungsgeschwindigkeit auf der Y-Achse aus
	 * @return dy
	 */
	public int getDY() {
		 return dy;
	}
	
	/**
	 * Setze Bewegungsgeschwindigkeit auf der X-Achse
	 * @param x
	 */
	public void setDX(int x) { //zum stoppen der Bewegung bei berührung mit einem Baum
		 this.dx = x;
	}
	 
	/**
	 * Setze Bewegungsgeschwindigkeit auf der Y-Achse
	 * @param y
	 */
	public void setDY(int y) {
		 this.dy = y;
	}
	
	public void Continue(){
		this.life -= 1;
	}
	
	public int getContinues() {
		return life;
	}
	 
	/**
	 * Gebe Smile Bild aus
	 * @return image
	 */
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
	 
	/**
	 * Gebe Pfeile des Character aus
	 * @return arrows
	 */
	public ArrayList<Arrow> getArrows() {
		return arrows;
	}
	
	/**
	 * Setze Pfeile des Characters
	 * @param arrows
	 */
	public void setArrows(ArrayList<Arrow> arrows) {
		this.arrows = arrows;
	}
	
	/**
	 * Erstelle neue Pfeil Arrayliste
	 */
	public void resArrows() {
		arrows = new ArrayList<Arrow>();
	}
	
	/**
	 * Gebe Arrayliste der Feuerbälle aus
	 * @return
	 */
	public ArrayList<Feuerball> getFBall() {
		return fball;
	}
	
	/**
	 * Setze Arrayliste der Feuerbälle
	 * @param fball
	 */
	public void setFBall(ArrayList<Feuerball> fball) {
		this.fball = fball;
	}
	
	/**
	 * Erstelle neue Arrayliste der Feuerbälle
	 */
	public void resFball() {
		fball = new ArrayList<Feuerball>();
	}
	
	/**
	 * Gebe Arrayliste der Geisterbälle aus
	 * @return gball
	 */
	public ArrayList<GeisterBall> getGBall() {
		return gball;
	}
	
	/**
	 * Erstelle neue Arrayliste der Geisterbälle
	 */
	public void resGball() {
		gball = new ArrayList<GeisterBall>();
	}

	/**
	 * Schieße Pfeile
	 */
	public void shoot() {
			if      (direction==1) arrows.add(new Arrow(x  +width  , y-3+height/2, direction));
			else if (direction==2) arrows.add(new Arrow(x-2        , y-3+height/2, direction));
			else if (direction==3) arrows.add(new Arrow(x-3+width/2, y  +height  , direction));
			else if (direction==4) arrows.add(new Arrow(x-3+width/2, y           , direction));
	}
	
	/**
	 * Schieße Feuerbälle
	 */
	public void cast() {
			if (direction==1) {
				fball.add(new Feuerball(x  +width  , y-3+height/2, direction));
				Sounds.play(4);
			}
			else if (direction==2) {
				fball.add(new Feuerball(x-2        , y-3+height/2, direction));
				Sounds.play(4);
			}
			else if (direction==3) {
				fball.add(new Feuerball(x-3+width/2, y  +height  , direction));
				Sounds.play(4);
			}
			else if (direction==4) {
				fball.add(new Feuerball(x-3+width/2, y           , direction));
				Sounds.play(4);
			}
	}
	
	/**
	 * Schieße Geisterbälle
	 */
	public void ghost() {
		if (direction==1) {
			gball.add(new GeisterBall(x  +width  , y-3+height/2, direction));
			Sounds.play(9);
		}
		else if (direction==2) {
			gball.add(new GeisterBall(x-2        , y-3+height/2, direction));
			Sounds.play(9);
		}
		else if (direction==3) {
			gball.add(new GeisterBall(x-3+width/2, y  +height  , direction));
			Sounds.play(9);
		}
		else if (direction==4) {
			gball.add(new GeisterBall(x-3+width/2, y           , direction));
			Sounds.play(9);
		}
}
	/**
	 * Berechne Schaden
	 * @param dmg
	 */
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
	
	/**
	 * Gebe Schwert aus
	 * @return sword
	 */
	public boolean getST() {
		return sword;
	}
	
	/**
	 * Setze Schwert
	 * @param sword
	 */
	public void setST(boolean sword) {
		this.sword = sword;
	}
	
	public void resST() {
		sword = false;
		move = true;
	}
	
	/**
	 * Schlage mit Schwert
	 */
	public void sword() {
		if (direction==1) {
			Mastersword = new Sword(x-2+width, y-1+height/2, 1);
			//Sounds.play(5);
		}
		if(direction==2) {
			Mastersword = new Sword(x-13, y-1+height/2, 2);
			//Sounds.play(5);
		}
		if(direction==3) {
			Mastersword = new Sword(x+1, y-1+height, 3);
			//Sounds.play(5);
		}
		if(direction==4) {
			Mastersword = new Sword(x-1+width/2, y-13, 4);
			//Sounds.play(5);
		}
	}
	
	/**
	 * Gebe MasterSchwert des Characters aus
	 * @return Mastersword
	 */
	public Sword getSword() {
		return Mastersword;
	}
	
	/**
	 * Setze MasterSchwert
	 * @param sword
	 */
	public void setMSword(Sword sword) {
		Mastersword = sword;
	}
	
	/**
	 * Berechne Manakosten
	 * @param mana
	 */
	public void manacost (int mana) {
		this.mana -= mana;
	}
	
	/**
	 * Gebe Mana des Characters aus
	 * @return
	 */
	public int Mana() {
		return mana;
	}
	
	/**
	 * Trinke Manatrank
	 */
	public void Manapotion() {
		if((manapotion>0)&&(mana<maxmana)){
			Sounds.play(8);
			this.mana = maxmana;
			manapotion -= 1;
		}
	}
	
	/**
	 * Setze maximales Mana
	 * @param mana
	 */
	public void setMaxmana(int mana) {
		this.maxmana = mana;
		this.mana = maxmana;
	}
	
	/**
	 * Setze maximales Leben
	 * @param health
	 */
	public void setMaxhealth(int health) {
		this.maxhealth = health;
		this.health = maxhealth;
	}
	
	/**
	 * Gebe maximales Leben aus
	 * @return
	 */
	public int getMaxhealth() {
		return maxhealth;
	}
	
	/**
	 * Trinke Lebenstrank
	 */
	public void Healthpotion() {
		if((healthpotion>0)&&(health<maxhealth)){
			Sounds.play(8);
			this.health = maxhealth;
			healthpotion -= 1;
		}
	}
	
	public void resContinue() {
		this.health = maxhealth;
		this.mana = maxmana;
		this.gold = 0;
	}
	
	/**
	 * Kaufe Leben
	 */
	public void buyHealth() {
		gold -= 3;
	}
	
	/**
	 * Setze Gold
	 */
	public void setGold() {
		 gold = gold + 1;
	}
	
	public void setContinues(int i) {
		 life = i;
	}
	
	public void setHealth(int i) {
		 health = i;
	}
	
	public void setMana(int i) {
		 mana = i;
	}
	
	public void setGoldtest(int i)   {
		gold = i;
	}
	
	/**
	 * Gebe Gold aus
	 * @return gold
	 */
	public int getGold() {
		 return gold;
	}
	
	/**
	 * Setze Rüstung
	 * @param armor
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	/**
	 * Gebe Rüstung aus
	 * @return armor
	 */
	public int getArmor() {
		return armor;
	}
	
	/**
	 * Gebe Leben aus
	 * @return health
	 */
	public int gethealth (){
		return health;
		
	/**
	 * Gebe Mana aus
	 * @return mana	
	 */
	}
	public int getmana (){
		return mana;
	}
	
	/**
	 * Gebe Größe des Characters aus
	 * @return x, y, breite, höhe
	 */
	public Rectangle getBounds() {
		 return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Gebe Größe des Characters beim Smile aus
	 * @return x, y, höhe, breite
	 */
	public Rectangle getBoundsSmile() {
		return new Rectangle(x-15, y-15, width+30, height+30);
	}
	 
	/**
	 * Drücken der Tasten
	 * @param e
	 */
	public void keyPressed(KeyEvent e) { //veränderung der Bewegungsvariablen
		 int key = e.getKeyCode();
		 
		 if (key == KeyEvent.VK_D){
			 if(Smile){
				 if(mana>0) {change = true;
				 			move = false;
				 }
			 }
		 }
		 
		 if ( key == KeyEvent.VK_SHIFT) {
			 if(dx<0) dx = -3;
			 else if(dx>0) dx =  3;
			 if(dy<0) dy = -3;
			 else if(dy>0) dy =  3;
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
	 
	/**
	 * Loslassen der Tasten
	 * @param e
	 */
	public void keyReleased(KeyEvent e) { //zurücksetzten der Bewegungsvariablen
		 int key = e.getKeyCode();
		 
		 if (key == KeyEvent.VK_D){
			 change = false;
			 move = true;
		 }
		 
		 if (key == KeyEvent.VK_M){
			 Manapotion();
		 }
		 
		 if (key == KeyEvent.VK_N){
			 Healthpotion();
		 }
		 
		 if ( key == KeyEvent.VK_SHIFT) {
			 if(dx<0) dx = -1;
			 else if (dx>0) dx =  1;
			 if(dy<0) dy = -1;
			 else if(dy>0) dy = 1;
		 }
		 
		 if (key == KeyEvent.VK_G){
			 if (Sword) Sounds.play(5);
			 sword = false;
			 move = true;
		 }
		 
		 if (key == KeyEvent.VK_F){
			 if(mana>19) {cast();
			 mana -= 20;}
		 }
		 
		 if (key == KeyEvent.VK_V){
			 if(mana>9) {
				 ghost();
				 mana -= 10;}
		 }
		 
		 if (key == KeyEvent.VK_SPACE) {
			 if(Arrow) {
				 Sounds.play(10);
				 shoot();
			 }
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
