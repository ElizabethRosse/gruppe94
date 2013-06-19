package game;



import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import menu.menu;

public class game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	private Char cha;
	private ArrayList<Tree> trees;
	private ArrayList<Arrow> arrows;
	private ArrayList<Feuerball> fball;
	private ArrayList<Manapotion> manap;
	private ArrayList<Healthpotion> healthp;
	private ArrayList<Enemy> enemies;
	private ArrayList<Trap> traps;
	
	private ArrayList<Checkpoint> checkpoints;
	private ArrayList<Coin> coins;

	private goal goal;
	private int max = 110;
	private Image image, imagescaled;
	private boolean ingame;
	private boolean win;
	private boolean checkpointactivated = false;
	private int G_WIDTH, G_HEIGHT;

	private int[] pos1 = new int[max]; 	//sp�ter �ndern f�r verschiedene Maps
	private int[] pos2 = new int[max];
	private int[] posE1 = new int[max];
	private int[] posE2 = new int[max];
	private int[] posEDIR = new int[110];
	private int[] posT1 = new int[110];
	private int[] posT2 = new int[110];
	private int[] ManapotionX = new int[max];
	private int[] ManapotionY = new int[max];
	private int[] HealthpotionX = new int[max];
	private int[] HealthpotionY = new int[max];
	private int[] checkpointX = new int [max];
	private int[] checkpointY = new int [max];
	private int[] coinX = new int[max];
	private int[] coinY = new int[max];
	private int mapNumber = 110;

	int NumberofTrees = 1;
	int maxcoin = 0;
	int Spawnpoints = 0;
	int NumberofEnemies = 0;
	int NumberofTraps = 0;
	int bosses = 0;
	int goals = 0;
	int items = 0;
	int npcs = 0;
	int manapotions = 0;
	int healthpotions = 0;
	int NumberofCheckpoints = 0;
	
	public game() {
		
		addKeyListener(new KAdapter());
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		ingame = true;
		win = false;
		
		ImageIcon ii =                       //l�dt ein Grass image und skaliert es groesser damit es das Sichtfeld abdeckt
				new ImageIcon(this.getClass().getResource("images/grass.jpg"));
		image = ii.getImage();
		imagescaled = image.getScaledInstance(530, 530, UNDEFINED_CONDITION);
		
		
		setSize(500, 500);
		
		cha = new Char();
		initArrows();
		initfball();
		
		try {
			initMap(mapNumber, 51, 240);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		goal = new goal (300, 275);     // erstellt Ziel mit Koordinaten
	
		
		timer = new Timer(5, this);
		timer.start();
		repaint();
	}
	
	public void initArrows() {
		arrows = new ArrayList<Arrow>();
		arrows = cha.getArrows();
	}
	
	public void initfball() {
		fball = new ArrayList<Feuerball>();
		fball = cha.getFBall();
	}
	
	public void addNotify() {  //holt h�he und breite des Fensters um Game Over naricht mittig zu platzieren
		super.addNotify();
		G_WIDTH = getWidth();
		G_HEIGHT = getHeight();
	}
	
	public void initTrees() {
		trees = new ArrayList<Tree>();
		
		for (int i=0; i < NumberofTrees ; i++) {
			trees.add(new Tree(pos1[i], pos2[i]));
		}
	}
	
	public void initCoin() {
		coins = new ArrayList<Coin>();
		
		for (int i=0; i < maxcoin ; i++) {
			coins.add(new Coin(coinX[i], coinY[i]));
		}
	}
	
	public void initCheckpoints() {
		checkpoints = new ArrayList<Checkpoint>();
		
		for (int i=0; i < NumberofCheckpoints ; i++) {
			checkpoints.add(new Checkpoint(checkpointX[i], checkpointY[i]));
		}
	}
	
	public void initManap() {
		manap = new ArrayList<Manapotion>();
		
		for(int i = 0; i< manapotions ; i++) {
			manap.add(new Manapotion(ManapotionX[i], ManapotionY[i]));
		}
	}
	
	public void initHealthp() {
		healthp = new ArrayList<Healthpotion>();
		
		for(int i = 0; i< healthpotions; i++) {
			healthp.add(new Healthpotion(HealthpotionX[i], HealthpotionY[i]));
		}
	}
	
	public void initEnemies() {
		enemies = new ArrayList<Enemy>();
		
		for (int i=0; i < NumberofEnemies ; i++) {
			enemies.add(new Enemy(posE1[i] + 13, posE2[i] + 13, posEDIR[i]));
		}
	}

	public void initTraps() {
		traps = new ArrayList<Trap>();
		
		for (int i=0; i < NumberofTraps ; i++) {
			traps.add(new Trap(posT1[i] + 13, posT2[i] + 13));
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		if (ingame) { //zeichne Character, B�ume usw.  wenn ingame = true ist
			
			Graphics2D g2d = (Graphics2D)g;
			
			g2d.drawImage(imagescaled, 0, 0, this);   // l�dt das Hintergrundbild
			g2d.drawImage(cha.getImage(), cha.getX(), cha.getY(), this);
			
			for (int i = 0; i <trees.size(); i++) {
				Tree t = (Tree) trees.get(i);
				g2d.drawImage(t.getImage(), t.getX(), t.getY(), this);
			
			}
			
			for (int i = 0; i < manap.size(); i++) {
				Manapotion m = (Manapotion) manap.get(i);
				if(m.isVisible()) g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
			
			for (int i = 0; i < healthp.size(); i++) {
				Healthpotion h = (Healthpotion) healthp.get(i);
				if(h.isVisible()) g2d.drawImage(h.getImage(), h.getX(), h.getY(), this);
			}
			
			for (int i = 0; i < coins.size(); i++) {
				Coin c = (Coin) coins.get(i);
				if(c.isVisible()) g2d.drawImage(c.getImage(), c.getX(), c.getY(), this);
			}
			
			for (int i = 0; i< fball.size(); i++) {
				Feuerball f = (Feuerball) fball.get(i);
				if (f.isVisible())
					g2d.drawImage(f.getImage(), f.getX(), f.getY(),  this);
			}
			
			for (int i = 0; i < arrows.size(); i++) {
				Arrow a = (Arrow) arrows.get(i);
				if (a.isVisible())
					g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
			
			for (int i = 0; i <traps.size();i++) {
				Trap t = (Trap) traps.get(i);
				if (t.isVisible()) g2d.drawImage(t.getImage(), t.getX(), t.getY(), this);
			}
			
			for (int k = 0; k<enemies.size(); k++) {		// zeichne Gegner
				Enemy e = (Enemy) enemies.get(k);
				if (e.isVisible()) g2d.drawImage(e.getImage(), e.getX(), e.getY(), this);
					}

			if (checkpointactivated){
				for (int i = 0; i < checkpoints.size(); i++) {
					Checkpoint c = (Checkpoint) checkpoints.get(i);
					if(c.active()) g2d.drawImage(c.getImageac(), c.getX(), c.getY(), this);
					else g2d.drawImage(c.getImagein(), c.getX(), c.getY(), this);
				}
			}
			
			if(cha.getST()) {
				Sword sword = cha.getSword();
				g2d.drawImage(sword.getImage(), sword.getX(), sword.getY(), this);
			}
			
			g2d.setColor(Color.RED);
			g2d.setFont(new Font( "Arial", Font.BOLD, 16));

			g2d.drawString("Lifes left: " + (cha.gethealth()), 5, 17);
			g2d.setColor(Color.BLUE);
			g2d.drawString("Mana left: " + (cha.getmana()), 100, 17);	
			g2d.setColor(Color.YELLOW);
			//g2d.drawString("Coins: " + (cha.getGold()), 200, 17);	
			
		}
		
		else {
			if (win) { //Naricht bei Sieg
				String msg = "YOU WIN";
				Font small = new Font ("Gewonnen", Font.BOLD, 14);
				FontMetrics metr = this.getFontMetrics(small);
				
				g.setColor(Color.black);
				g.setFont(small);
				g.drawString(msg, (G_WIDTH - metr.stringWidth(msg))/2, G_HEIGHT /2);
				
			}
			else { // Naricht bei Niederlage (wenn ingame falsch ist, aber kein Sieg = false ist)
				String msg = "Game Over";
				Font small = new Font ("Ende", Font.BOLD, 14);
				FontMetrics metr = this.getFontMetrics(small);
			
				g.setColor(Color.black);
				g.setFont(small);
				g.drawString(msg, (G_WIDTH - metr.stringWidth(msg))/2, G_HEIGHT / 2);

			}
		}
		Toolkit.getDefaultToolkit().sync();
		g.dispose(); //wie final verhindert �nderung des JFrames
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
        initArrows();
		
		for(int i = 0; i < arrows.size(); i++) {
			Arrow a = (Arrow) arrows.get(i);
			if (a.isVisible())
				a.move();
			else arrows.remove(i);
		}
		
		initfball();
		
		for(int i = 0; i < fball.size(); i++) {
			Feuerball f = (Feuerball) fball.get(i);
			if (f.isVisible())
				f.move();
			else fball.remove(i);
		}
		
		if(cha.getST()) {
			Sword sword = (Sword) cha.getSword();
			sword.move();
		}

		if(cha.getX() > 490) {
			mapNumber++;
			try {
				initMap(mapNumber, 11, 240);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}//increasing mapNumber with starting positions
		else if(cha.getX() < 10 ) { 															//decrease mapNumber
			mapNumber--;
			try {
				initMap(mapNumber, 480, 240);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		} else if(cha.getY() < 35 ) { 															//decrease mapNumber
			mapNumber = mapNumber + 10;
			try {
				initMap(mapNumber, 225, 480);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		} else if(cha.getY() > 490 ) { 															//decrease mapNumber
			mapNumber = mapNumber - 10;
			try {
				initMap(mapNumber, 220, 36);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
		
		cha.move();
		moveEnemy();
		checkCollisions();
		repaint();
	}
	
	public void moveEnemy(){

		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = (Enemy) enemies.get(i);
			if(e.getLife()>0) {
				e.move();
			}
		}	
	}		
		
		
	

	public void checkCollisions() {
		
		Rectangle rChar = cha.getBounds();
	
		
		if(cha.getST()) {
			Sword sword = cha.getSword();
			Rectangle rSword = sword.getBounds();
			
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = (Enemy) enemies.get(i);
				if(e.getLife()>0) {
					Rectangle rEnemy = e.getBounds();
					if(rSword.intersects(rEnemy)) {
						e.damage(sword.getDmg());
					}
				}
				else enemies.remove(i);
			}
		}
		
		if(cha.getSmile()) {
			cha.manacost(1);
			Rectangle rSmile = cha.getBoundsSmile();
			
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = (Enemy) enemies.get(i);
				if(e.getLife()>0) {
					Rectangle rEnemy = e.getBounds();
					if(rSmile.intersects(rEnemy)) {
						e.damage(1);						
					}
				}
				else enemies.remove(i);
			}
		}
		if (checkpointactivated) {
			for (int i = 0; i < checkpoints.size(); i++) {
				Checkpoint c = (Checkpoint) checkpoints.get(i);
				Rectangle rCheckpoint = c.getBounds();
			
				if (rChar.intersects(rCheckpoint)){
				c.setActivated(true);								//setzt checkpoint bei beruehrung auf activated

					if (cha.getDX() == 1) {
						cha.addX(-1);
					}
				
					if (cha.getDX() == -1) {
						cha.addX(1);
					}
				
					if (cha.getDY() == 1) {
						cha.addY(-1);
					}
				
					if (cha.getDY() == -1) {
						cha.addY(1);
					}
				}
			}	
		}
		

		for (int i = 0; i < traps.size(); i++) {
			Trap t = (Trap) traps.get(i);
			Rectangle rTrap = t.getBounds();
			if (rChar.intersects(rTrap)){
				if (t.isVisible()){
					cha.dmg(t.getDmg());
					t.setVisible(false);
				}
			}	
		}
		
		for (int k = 0; k < enemies.size(); k++) {
			Enemy e = (Enemy) enemies.get(k);
			if(e.getLife()>0) {
			Rectangle rEnemy = e.getBounds();
			
			for (int j = 0; j < trees.size(); j++) {
				Tree t = (Tree) trees.get(j);
				Rectangle rTree = t.getBounds();
				if (rEnemy.intersects(rTree)){
					e.movecollide();
				}
			}
			
			if (rChar.intersects(rEnemy)){    //schaden bei Ber�hung mit Gegner
				if ((cha.gethealth() > 0)) {
					cha.dmg(e.getDmg());
					if (cha.getDX() == 1) {
						cha.addX(-10);
					}
					
					if (cha.getDX() == -1) {
						cha.addX(10);
					}
					
					if (cha.getDY() == 1) {
						cha.addY(-10);
					}
					
					if (cha.getDY() == -1) {
						cha.addY(10);
					}
				}
				else ingame = false;
			}
			for(int i = 0; i<arrows.size();i++) {
				Arrow a = (Arrow) arrows.get(i);
				if(a.getBounds().intersects(rEnemy)) {
					a.setVisible(false);
					e.damage(a.getDmg());
				}
			}
			for(int i = 0; i<fball.size(); i++) {
				Feuerball f = (Feuerball) fball.get(i);
				if(f.getBounds().intersects(rEnemy)) {
					f.setVisible(false);
					e.damage(f.getDmg());
				}
			}}
			
			else enemies.remove(k);
		}
		
		for (int i = 0; i<manap.size(); i++) {
			Manapotion m = (Manapotion) manap.get(i);
			if(m.isVisible()){
			Rectangle rMana = m.getBounds();
			
			if(rChar.intersects(rMana)) {
				cha.Manapotion();
				m.setVisible(false);
			}
			}
			else manap.remove(i);
		}
		
		for (int i = 0; i<healthp.size(); i++) {
			Healthpotion h = (Healthpotion) healthp.get(i);
			if(h.isVisible()){
			Rectangle rHealth = h.getBounds();
			
			if(rChar.intersects(rHealth)) {
				cha.Healthpotion();
				h.setVisible(false);
			}
			}
			else healthp.remove(i);
		}
		

		for (int i = 0; i<coins.size(); i++) {
			Coin c = (Coin) coins.get(i);
			if(c.isVisible()){
			Rectangle rCoin = c.getBounds();
			
			if(rChar.intersects(rCoin)) {
				cha.setGold();
				c.setVisible(false);
			}
			}
			else coins.remove(i);
		}
		
		Rectangle rGoal = goal.getBounds();

	
		for (int j = 0; j < trees.size(); j++) {
			Tree t = (Tree) trees.get(j);
			Rectangle rTree = t.getBounds();
			
			if (rChar.intersects(rTree)) { //stop at touching tree
				
				if (cha.getDX() == 1) {
					cha.addX(-1);
				}
				
				if (cha.getDX() == -1) {
					cha.addX(1);
				}
				
				if (cha.getDY() == 1) {
					cha.addY(-1);
				}
				
				if (cha.getDY() == -1) {
					cha.addY(1);
				}
				

			}
						
			for (int i = 0; i<arrows.size();i++) {
				Arrow a = (Arrow) arrows.get(i);
				if(a.getBounds().intersects(rTree)) a.setVisible(false);
				for(int p = 0; p<fball.size(); p++){
					Feuerball f = (Feuerball) fball.get(p);
					if(a.getBounds().intersects(f.getBounds())) a.setVisible(false);
				}
			}
			for (int i = 0; i<fball.size();i++) {
				Feuerball f = (Feuerball) fball.get(i);
				if(f.getBounds().intersects(rTree)) f.setVisible(false);
			}
		}
		
	}
	
	public void initMap(int m, int j ,int k) throws IOException {
		int i = 1;													//loop variables
		int x = 50;
		int y = 25;
		char[] prototypemap = new char[110];
		checkpointactivated = false;
		
		NumberofTraps = 0;
		NumberofTrees = 1;
		Spawnpoints = 0;
		NumberofEnemies = 0;
		bosses = 0;
		goals = 0;
		items = 0;
		npcs = 0;
		manapotions = 0;
		healthpotions = 0;
		maxcoin = 0;
		
		
		pos1[0] = 0;
		pos2[0] = 25;
		
		
		prototypemap = getMap(m);
		
		while(i < max) {												//maximum of fields on a map: 110
			
			if(i % 10 == 0){
				y = y + 50;
				x = 0;
			}
			
			switch(prototypemap[i]) {
			case '#' : {											// # : wall
				pos1[NumberofTrees] = x;
				pos2[NumberofTrees] = y;
				NumberofTrees++;
				break;
			}
			
			case 's' : {											// e : enemy moving horizontal
				posE1[NumberofEnemies] = x;
				posE2[NumberofEnemies] = y;
				posEDIR[NumberofEnemies] = 1;
				
				NumberofEnemies++;
				break;
			}
			
			case 'v' : {											// e2 : enemy moving vertical
				posE1[NumberofEnemies] = x;
				posE2[NumberofEnemies] = y;
				posEDIR[NumberofEnemies] = 2;
				
				NumberofEnemies++;
				break;
			}
			
			case 't': {												// t : traps
				posT1[NumberofTraps] = x;
				posT2[NumberofTraps] = y;
				
				NumberofTraps++;
				break;
			}
			
			case 'b' : {											// b : boss
				bosses++;
				break;
			}
			case 'g' : {											// g : coin/gold/money
				coinX[maxcoin] = x;
				coinY[maxcoin] = y;
				maxcoin++;
				break;
			}
			case 'm' : {											// m : manapotion
				ManapotionX[manapotions] = x;
				ManapotionY[manapotions] = y;
				manapotions++;
				break;
			}
			case 'h' : {                                            // h : healthpotion
				HealthpotionX[healthpotions] = x;
				HealthpotionY[healthpotions] = y;
				healthpotions++;
				break;
			}
			case 'n' : {											// n : npc
				npcs++;
				break;
			}
			case 'c' : {											// c : checkpoint
				checkpointX[NumberofCheckpoints] = x;
				checkpointY[NumberofCheckpoints] = y;
				NumberofCheckpoints++;
				checkpointactivated = true;
				initCheckpoints();
				break;
			}
			default : {
				break;
			}
			}
			
		x = x + 50;
		i++;
		}
		
		cha.setX(j);
		cha.setY(k);
		
		initTrees();											// important for repainting
		initEnemies();
		initTraps();
		initManap();
		initHealthp();
		initCoin();
	}
	
	
	public char[] getMap(int m) throws IOException {						//maps are starting buttom left, following principe: XYZ, X: Level, Y: High, Z: wide
		FileReader datei;
		BufferedReader dat; 
		char[] prototypemap = new char[110];
		switch(m) {	
		case 110 : {
				datei = new FileReader("src\\game\\maps\\map1");
				dat = new BufferedReader(datei);																				//map1
			break;
		}
		case 111 : {
				datei = new FileReader("src\\game\\maps\\map2");
				dat = new BufferedReader(datei);																				//map2		
			break;
		}
		case 112 : {			
			datei = new FileReader("src\\game\\maps\\map3");
			dat = new BufferedReader(datei);																					//map3				
		break;
		}
		case 113 : {
				datei = new FileReader("src\\game\\maps\\map4");
				dat = new BufferedReader(datei);																				//map4			
			break;
		}
		case 123 : {
				datei = new FileReader("src\\game\\maps\\map5");
				dat = new BufferedReader(datei);																				//map5			
			break;
		}
		case 122 : {
				datei = new FileReader("src\\game\\maps\\map6");
				dat = new BufferedReader(datei);																				//map6				
			break;
		}
		case 121 : {
				datei = new FileReader("src\\game\\maps\\map7");
				dat = new BufferedReader(datei);																				//map7			
			break;
		}
		case 120 : {
				datei = new FileReader("src\\game\\maps\\map8");
				dat = new BufferedReader(datei);																				//map8			
			break;
		}
		case 100 : {
				datei = new FileReader("src\\game\\maps\\map9");
				dat = new BufferedReader(datei);																				//map9			
			break;
		}
		case 101 : {
			datei = new FileReader("src\\game\\maps\\map10");
			dat = new BufferedReader(datei);																				//map10
		break;
		}
		case 102 : {
			datei = new FileReader("src\\game\\maps\\map11");
			dat = new BufferedReader(datei);																				//map11
		break;
	}
		case 103 : {
			datei = new FileReader("src\\game\\maps\\map12");
			dat = new BufferedReader(datei);																				//map12
		break;
	}
		case 220 : {
			datei = new FileReader("src\\game\\maps\\map13");
			dat = new BufferedReader(datei);																				//map13
		break;
	}
		case 221 : {
			datei = new FileReader("src\\game\\maps\\map14");
			dat = new BufferedReader(datei);																				//map14
		break;
	}
		case 222 : {
			datei = new FileReader("src\\game\\maps\\map15");
			dat = new BufferedReader(datei);																				//map15
		break;
	}
		case 223 : {
			datei = new FileReader("src\\game\\maps\\map16");
			dat = new BufferedReader(datei);																				//map16
		break;
	}
		case 210 : {
			datei = new FileReader("src\\game\\maps\\map17");
			dat = new BufferedReader(datei);																				//map17
		break;
	}
		case 211 : {
			datei = new FileReader("src\\game\\maps\\map18");
			dat = new BufferedReader(datei);																				//map18
		break;
	}
		case 212 : {
			datei = new FileReader("src\\game\\maps\\map19");
			dat = new BufferedReader(datei);																				//map19
		break;
	}
		case 213 : {
			datei = new FileReader("src\\game\\maps\\map20");
			dat = new BufferedReader(datei);																				//map20
		break;
	}
		case 200 : {
			datei = new FileReader("src\\game\\maps\\map21");
			dat = new BufferedReader(datei);																				//map21
		break;
	}
		case 201 : {
			datei = new FileReader("src\\game\\maps\\map22");
			dat = new BufferedReader(datei);																				//map22
		break;
	}
		case 202 : {
			datei = new FileReader("src\\game\\maps\\map23");
			dat = new BufferedReader(datei);																				//map23
		break;
	}
		case 203 : {
			datei = new FileReader("src\\game\\maps\\map24");
			dat = new BufferedReader(datei);																				//map24
		break;
	}
		case 310 : {
			datei = new FileReader("src\\game\\maps\\map25");
			dat = new BufferedReader(datei);																				//map25
		break;
	}
		case 311 : {
			datei = new FileReader("src\\game\\maps\\map26");
			dat = new BufferedReader(datei);																				//map26
		break;
	}
		case 301 : {
			datei = new FileReader("src\\game\\maps\\map27");
			dat = new BufferedReader(datei);																				//map27
		break;
	}
		case 300 : {
			datei = new FileReader("src\\game\\maps\\map28");
			dat = new BufferedReader(datei);																				//map28
		break;
	}
		default : {
				datei = new FileReader("src\\game\\maps\\map1");
				dat = new BufferedReader(datei);																				//map1
		}
		}
		
		String line;
		line = dat.readLine();
		int a = 0;
		
		while(line != null) {														//read a line
			for (int i = 0; i<line.length(); i++) {									//read a symbol
				prototypemap[a] = line.charAt(i); 									// save the symbol in an array
				a++;
			}
			line = dat.readLine();													// read a new line
		}
		dat.close();
		return prototypemap;
	}
	
	
	
	public class KAdapter extends KeyAdapter { 
		
		public void keyPressed(KeyEvent e) {
			if (ingame == false)
			{
				add(new menu());
			}
			else
			{
			cha.keyPressed(e);
			}
		}
		public void keyReleased(KeyEvent e) {
			cha.keyReleased(e);
		}
		
	}
}
