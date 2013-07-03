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

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import menu.menu;

public class game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	private Char cha;
	private ArrayList<Tree> trees;
	private ArrayList<Tree> falsetrees;
	private ArrayList<Arrow> arrows;
	private ArrayList<Feuerball> fball;
	private ArrayList<Manapotion> manap;
	private ArrayList<Healthpotion> healthp;
	private ArrayList<Enemy> enemies;
	private ArrayList<Trap> traps;
	private ArrayList<Xoss> xosses; // boss2
	private ArrayList<Zoss> zosses; // boss3
	private ArrayList<Boss> bosses;
	private ArrayList<npc> npc;
	private ArrayList<shopkeeper> shop;
	
	private ArrayList<Checkpoint> checkpoints;
	private ArrayList<Coin> coins;

	private int max = 110;
	private Image image, imagescaled, health, halfhealth, nohealth;
	private boolean ingame;
	private boolean win;
	private boolean checkpointactivated = false;
	private boolean levelup = false, levelup2 = true, levelup3 = true;
	private int G_WIDTH, G_HEIGHT;

	private int[] pos1 = new int[max]; 	//Arrays for object set
	private int[] pos2 = new int[max];
	private int[] posE1 = new int[max]; 		//Gegner X Wert
	private int[] posE2 = new int[max];			//Gegner Y Wert
	private int[] posEDIR = new int[110];		//Gegener Vertikal oder Horizontal
	private int[] posT1 = new int[110];			//Trap X Wert
	private int[] posT2 = new int[110];			//Trap Y Wert
	private int[] posB1 = new int[110];			//Boss1 X Wert
	private int[] posB2 = new int[110];			//Boss1 Y Wert
	private int[] posX1 = new int[110];			//Boss2 X Wert
	private int[] posX2 = new int[110];
	private int[] posZ1 = new int[110];			//Boss3 X Wert
	private int[] posZ2 = new int[110];
	private int[] npcX = new int[max];
	private int[] npcY = new int[max];
	private int[] npcD = new int[max];
	private int[] shopX = new int[max];
	private int[] shopY = new int[max];
	private int[] falsetreeX = new int[max];
	private int[] falsetreeY = new int[max];
	private int[] ManapotionX = new int[max];
	private int[] ManapotionY = new int[max];
	private int[] HealthpotionX = new int[max];
	private int[] HealthpotionY = new int[max];
	private int[] checkpointX = new int [max];
	private int[] checkpointY = new int [max];
	private int[] coinX = new int[max];
	private int[] coinY = new int[max];
	private int mapNumber = 110;
	
	int NumberofXosses = 0;
	int NumberofZosses = 0;
	int NumberofBosses = 0;

	private int reset = 110;
	private Coin coinpic = new Coin(1000,1000);
	private Manapotion manapic = new Manapotion(1000,1000);
	private Healthpotion healthpic = new Healthpotion(1000,1000);

	int NumberofTrees = 1;		//absolut number of objects of this type
	int NumberofFalsetrees = 0;
	int maxcoin = 0;
	int Spawnpoints = 0;
	int NumberofEnemies = 0;
	int NumberofTraps = 0;
	int goals = 0;
	int items = 0;
	int npcs = 0;
	int manapotions = 0;
	int healthpotions = 0;
	int NumberofCheckpoints = 0;
	int maxnpc = 0;
	int maxshops = 0;
	
	public game() {
		
		addKeyListener(new KAdapter());
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		ingame = true;
		win = false;
		
		ImageIcon ii =                       //laedt ein Grass image und skaliert es groesser damit es das Sichtfeld abdeckt
				new ImageIcon(this.getClass().getResource("images/grass.jpg"));
		image = ii.getImage();
		imagescaled = image.getScaledInstance(530, 530, UNDEFINED_CONDITION);
		
		ii = new ImageIcon(this.getClass().getResource("images/leben.png")); // 40x40 bilder
		health = ii.getImage();
		ii = new ImageIcon(this.getClass().getResource("images/half.png"));
		halfhealth = ii.getImage();
		ii = new ImageIcon(this.getClass().getResource("images/dead.png"));
		nohealth = ii.getImage();
		
		
		setSize(500, 500);
		
		cha = new Char();
		initArrows();
		initfball();
		
		try {
			initMap(mapNumber, 51, 240);									//loading first map
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		timer = new Timer(5, this);
		timer.start();
		repaint();
		dialog();
		Sounds.play(2);
	}
	
	public void initArrows() {								//create the arraylist of objects
		arrows = new ArrayList<Arrow>();
		arrows = cha.getArrows();
	}
	
	public void initfalsetrees() {
		falsetrees = new ArrayList<Tree>();
		
		for (int i = 0; i < NumberofFalsetrees ; i++) {
			falsetrees.add(new Tree(falsetreeX[i], falsetreeY[i]));
		}
	}
	
	public void initfball() {
		fball = new ArrayList<Feuerball>();
		fball = cha.getFBall();
	}
	
	public void addNotify() {  
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
	
	public void initnpc() {
		npc = new ArrayList<npc>();
		
		for (int i=0; i < maxnpc ; i++) {
			npc.add(new npc(npcX[i], npcY[i], npcD[i]));
		}
	}
	
	public void initshop() {
		shop = new ArrayList<shopkeeper>();
		
		for (int i=0; i < maxshops ; i++) {
			shop.add(new shopkeeper(shopX[i], shopY[i]));
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
	
	public void initBoss(){														//initializiere Bosse
		bosses = new ArrayList<Boss>();
		for (int i =0; i<NumberofBosses; i++)									// Boss ArrayList mit X und Y Werten aus Textdatei
			bosses.add(new Boss(posB1[i] +13, posB2[i] + 13, 1));
	}
	
	public void initXoss(){														//initializiere Bosse
		xosses = new ArrayList<Xoss>();
		for (int i =0; i<NumberofXosses; i++)									// Boss ArrayList mit X und Y Werten aus Textdatei
			xosses.add(new Xoss(posX1[i] +13, posX2[i] + 13, 2));
	}
	
	public void initZoss(){														//initializiere Bosse
		zosses = new ArrayList<Zoss>();
		for (int i =0; i<NumberofZosses; i++)									// Boss ArrayList mit X und Y Werten aus Textdatei
			zosses.add(new Zoss(posZ1[i] +13, posZ2[i] + 13, 1));
	}
	
	
	public void initEnemies() {													//initializiere Enemies
		enemies = new ArrayList<Enemy>();
		for (int i=0; i < NumberofEnemies ; i++) {								//Enemy ArrayList mit X, Y und Direction aus Textdatei
			enemies.add(new Enemy(posE1[i] + 13, posE2[i] + 13, posEDIR[i]));
		}
	}

	public void initTraps() {													//initializiere Traps
		traps = new ArrayList<Trap>();
		for (int i=0; i < NumberofTraps ; i++) {								//Trap ArrayListe mit X und Y Werten aus Textdatei
			traps.add(new Trap(posT1[i] + 13, posT2[i] + 13));
		}
	}
	
	public void initXP() {
		if(levelup){
			if ((cha.getXP() >= 1)&&levelup2){
				dialogLVL2();
				cha.addFDMG(50);
				cha.setLVL(2);
				levelup2 = false;
			}
			else if ((cha.getXP() >= 30)&&levelup3) {
				dialogLVL3();
				cha.addSDMG(5);
				cha.setLVL(3);
				levelup3 = false;
			}
		}
	}
	
	public void paint(Graphics g) {												//painting the background
		super.paint(g);
		
		if (ingame) { //zeichne Character, Baeume usw.  wenn ingame = true ist
			
			Graphics2D g2d = (Graphics2D)g;
			
			g2d.drawImage(imagescaled, 0, 0, this);   // laedt das Hintergrundbild
			g2d.drawImage(cha.getImage(), cha.getX(), cha.getY(), this);
			
			for (int i = 0; i <trees.size(); i++) {
				Tree t = (Tree) trees.get(i);
				g2d.drawImage(t.getImage(), t.getX(), t.getY(), this);
			
			}
			
			if (cha.haveSword()==false) {
				for (int i = 0; i <falsetrees.size(); i++) {
				Tree t = (Tree) falsetrees.get(i);
				g2d.drawImage(t.getImage(), t.getX(), t.getY(), this);
				}
			}
			
			for (int i = 0; i < manap.size(); i++) {							//painting a manapotion for every entry in the array manap
				Manapotion m = (Manapotion) manap.get(i);
				if(m.isVisible()) g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
			

			for (int i = 0; i < npc.size(); i++) {
				npc n = (npc) npc.get(i);
				if(n.isVisible()) g2d.drawImage(n.getImage(), n.getX(), n.getY(), this);
			}
			
			for (int i = 0; i < shop.size(); i++) {
				shopkeeper s = (shopkeeper) shop.get(i);
				if(s.isVisible()) g2d.drawImage(s.getImage(), s.getX(), s.getY(), this);
			}
			
			for (int i = 0; i < healthp.size(); i++) {
				Healthpotion h = (Healthpotion) healthp.get(i);
				if(h.isVisible()) g2d.drawImage(h.getImage(), h.getX(), h.getY(), this);
			}
			
			for (int i = 0; i < coins.size(); i++) {							//zeichne Coins
				Coin c = (Coin) coins.get(i);
				if(c.isVisible()) g2d.drawImage(c.getImage(), c.getX(), c.getY(), this);
			}
			
			for (int i = 0; i< fball.size(); i++) {								//zeichne Fireballs
				Feuerball f = (Feuerball) fball.get(i);
				if (f.isVisible())
					g2d.drawImage(f.getImage(), f.getX(), f.getY(),  this);
			}
			
			for (int i = 0; i < arrows.size(); i++) {							//zeichne Arrows
				Arrow a = (Arrow) arrows.get(i);
				if (a.isVisible())
					g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
			
			for (int i = 0; i <traps.size();i++) {								//zeichne Traps
				Trap t = (Trap) traps.get(i);
				if (t.isVisible()) g2d.drawImage(t.getImage(), t.getX(), t.getY(), this);
			}
			
			for (int k = 0; k<enemies.size(); k++) {							// zeichne Gegner
				Enemy e = (Enemy) enemies.get(k);
				if (e.isVisible()) g2d.drawImage(e.getImage(), e.getX(), e.getY(), this);
					}
			
			for (int i = 0; i<bosses.size();i++){								//zeichne Bosse
				Boss b = (Boss) bosses.get(i);
			 	if (b.isVisible()) g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}

			for (int i = 0; i<xosses.size();i++){								//zeichne Bosse
				Xoss x = (Xoss) xosses.get(i);
			 	if (x.isVisible()) g2d.drawImage(x.getImage(), x.getX(), x.getY(), this);
			}
			
			for (int i = 0; i<zosses.size();i++){								//zeichne Bosse
				Zoss z = (Zoss) zosses.get(i);
			 	if (z.isVisible()) g2d.drawImage(z.getImage(), z.getX(), z.getY(), this);
			}
			
			if (checkpointactivated){											//zeichne Checkpoints
				for (int i = 0; i < checkpoints.size(); i++) {
					Checkpoint c = (Checkpoint) checkpoints.get(i);
					if(c.active()) g2d.drawImage(c.getImageac(), c.getX(), c.getY(), this);
					else g2d.drawImage(c.getImagein(), c.getX(), c.getY(), this);
				}
			}
			
			if(cha.getST()) {													//zeichne Sword
				Sword sword = cha.getSword();
				g2d.drawImage(sword.getImage(), sword.getX(), sword.getY(), this);
			}
			
     		//zeichne Infoleiste

			switch(cha.getMaxhealth()) {									//initilize healthbar
			case 6 : {
				switch(cha.gethealth()){
				case 6 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					break;
				}
				case 5 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(halfhealth, 85, -5, this);
					break;
				}
				case 4 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					break;
				}
				case 3 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(halfhealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					break;
				}
				case 2 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					break;
				}
				case 1 : {
					g2d.drawImage(halfhealth, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					break;
				}
				case 0 : {
					g2d.drawImage(nohealth, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					break;
				}
				} break;	
			}
			case 8 : {
				switch(cha.gethealth()){
				case 8 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					g2d.drawImage(health, 125, -5, this);
					break;
				}
				case 7 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					g2d.drawImage(halfhealth, 125, -5, this);
					break;
				}
				case 6 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					break;
				}
				case 5 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(halfhealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					break;
				}
				case 4 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					break;
				}
				case 3 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(halfhealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					break;
				}
				case 2 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					break;
				}
				case 1 : {
					g2d.drawImage(halfhealth, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					break;
				}
				case 0 : {
					g2d.drawImage(nohealth, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					break;
				}
				}break;
			}
			case 10 : {
				switch(cha.gethealth()) {
				case 10 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					g2d.drawImage(health, 125, -5, this);
					g2d.drawImage(health, 165, -5, this);
					break;
				}
				case 9 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					g2d.drawImage(health, 125, -5, this);
					g2d.drawImage(halfhealth, 165, -5, this);
					break;
				}
				case 8 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					g2d.drawImage(health, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				case 7 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					g2d.drawImage(halfhealth, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				case 6 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(health, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				case 5 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(halfhealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				case 4 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(health, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				case 3 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(halfhealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				case 2 : {
					g2d.drawImage(health, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				case 1 : {
					g2d.drawImage(halfhealth, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				case 0 : {
					g2d.drawImage(nohealth, 5, -5, this);
					g2d.drawImage(nohealth, 45, -5, this);
					g2d.drawImage(nohealth, 85, -5, this);
					g2d.drawImage(nohealth, 125, -5, this);
					g2d.drawImage(nohealth, 165, -5, this);
					break;
				}
				}
			}
			}

			g2d.setColor(Color.BLUE);										// Mana Anzeige
				g2d.drawString("Mana left: " + (cha.getmana()), 210, 17);
			
			
			g2d.setColor(Color.BLACK);										// Coin Anzeige
				g2d.drawImage(coinpic.getImage(), 300, 0, this);
				g2d.drawString(" " + (cha.getGold()), 340, 17);
				
				g2d.drawString("Lv : " + (cha.getLVL()), 370, 17);
				
			g2d.drawImage(manapic.getImage(), 400, -2, this);
			g2d.drawString(" " + (cha.getManapotion()), 430, 17);

			g2d.drawImage(healthpic.getImage(), 450, -2, this);
			g2d.drawString(" " + (cha.getHealthpotion()), 480, 17);
			
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
	
	public void dialog() {
		JDialog startupJDialog = new JDialog();
		startupJDialog.setTitle("How to!");
		startupJDialog.setLocationRelativeTo(null);
		if(cha.haveSword()){
			if(cha.haveArrow()){
				startupJDialog.setSize(620,75);
				startupJDialog.add(new JLabel("Move: Pfeiltasten | Feuerball: f | Manapotion: m | Healthpotion: n | Sprint: Shift | Help: h" +
						" | Sword: g | Arrow: Space"));
			}
			else {
				startupJDialog.setSize(560,75);
				startupJDialog.add(new JLabel("Move: Pfeiltasten | Feuerball: f | Manapotion: m | Healthpotion: n | Sprint: Shift | Help: h | Sword: g"));
			}
		}
		else{
			startupJDialog.setSize(500,75);
			startupJDialog.add(new JLabel("Move: Pfeiltasten | Feuerball: f | Manapotion: m | Healthpotion: n | Sprint: Shift | Help: h"));
		}
		startupJDialog.setModal(true);
		startupJDialog.setVisible(true);
	}
	
	public void dialog1() {            //dialog fuer den ersten npc, der die story erzaehlt
		JDialog ersterJDialog = new JDialog();
		ersterJDialog.setTitle ("Mr Moustache");
		ersterJDialog.setSize(270,75);
		ersterJDialog.setLocationRelativeTo(null);
		ersterJDialog.add(new JLabel ("Yo Nerd! Die Smileys brauchen deine Hilfe!"));
		ersterJDialog.setModal(true);
		ersterJDialog.setVisible(true);
	}
	
	public void dialog2() {
		JDialog zweiterJDialog = new JDialog();
		zweiterJDialog.setTitle("It's dagerous out there!");
		zweiterJDialog.setSize(400,75);
		zweiterJDialog.setLocationRelativeTo(null);
		zweiterJDialog.add(new JLabel ("Take this it's dangerous out there!(You got a Sword(use with 'g'))"));
		zweiterJDialog.setModal(true);
		zweiterJDialog.setVisible(true);
		cha.makeSword();
	}
	
	public void dialog3() {
		JDialog dritterJDialog = new JDialog();
		dritterJDialog.setTitle("Smile!");
		dritterJDialog.setSize(400,75);
		dritterJDialog.setLocationRelativeTo(null);
		dritterJDialog.add(new JLabel ("You got your Smile back!(You can Smile with 'd')"));
		dritterJDialog.setModal(true);
		dritterJDialog.setVisible(true);
		cha.makeSmile();
	}
	
	public void dialog4() {
		JDialog vierterJDialog = new JDialog();
		vierterJDialog.setTitle("Arrows!");
		vierterJDialog.setSize(400,75);
		vierterJDialog.setLocationRelativeTo(null);
		vierterJDialog.add(new JLabel ("You got some Arrows!(Shoot them with 'LEER')"));
		vierterJDialog.setModal(true);
		vierterJDialog.setVisible(true);
		cha.makeArrow();
	}
	
	public void dialogLVL2() {
		JDialog LVL2JDialog = new JDialog();
		LVL2JDialog.setTitle("Level Up!");
		LVL2JDialog.setSize(400,75);
		LVL2JDialog.setLocationRelativeTo(null);
		LVL2JDialog.add(new JLabel ("You reached Level 2! Your fireball's damage doubled!"));
		LVL2JDialog.setModal(true);
		LVL2JDialog.setVisible(true);
	}
	
	public void dialogLVL3() {
		JDialog LVL3JDialog = new JDialog();
		LVL3JDialog.setTitle("Level Up!");
		LVL3JDialog.setSize(400,75);
		LVL3JDialog.setLocationRelativeTo(null);
		LVL3JDialog.add(new JLabel ("You reached Level 3! Your sword's damage doubled!"));
		LVL3JDialog.setModal(true);
		LVL3JDialog.setVisible(true);
	}
	
	public void shop() {
		//JOptionPane.showOptionDialog(null, "Wollen Sie ein zusaetzliches Leben kaufen?","Shop",
                //JOptionPane.YES_NO_OPTION,
                //JOptionPane.PLAIN_MESSAGE, null, 
                //new String[]{"BUY", "BYE"}, "BUY");
				int h = JOptionPane.showConfirmDialog(null, "Wollen Sie ein zusaetzliches Leben kaufen fuer 3 Gold?");
		if(h==0){
			if(cha.getGold()>=3){
				if(cha.getMaxhealth()<10){
					cha.buyHealth();
					cha.setMaxhealth(cha.getMaxhealth()+2);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {						//checking performed actions
		
		if(cha.gethealth() <= 0) ingame = false; 
		
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

		if(cha.getX() > 490) {										//mapchanges
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
		moveBoss();
		moveXoss();
		moveZoss();
		checkCollisions();
		checkAlive();
		initXP();
		repaint();
	}
	
	public void checkAlive() {													//check, if you have tries left and reset health, else you loose
		if((cha.getContinues() > 0) && (cha.gethealth() <= 0)) {
			cha.Continue();
			if(cha.getContinues() == 0) ingame = false;
			cha.Healthpotion();
			mapNumber = reset;
			if(mapNumber%10 == 0) {
				try {
					initMap(mapNumber, 51, 240);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
			try {
				initMap(mapNumber, checkpointX[0] + 25, checkpointY[0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
	
	public void moveBoss(){
		for (int i = 0; i < bosses.size(); i++){
			Boss b = (Boss) bosses.get(i);
			if (b.getLife()>0){
				b.move();
			}
		}
	}
	
	public void moveXoss(){
		for (int i = 0; i < xosses.size(); i++){
			Xoss x = (Xoss) xosses.get(i);
			if (x.getLife()>0){
				x.move();
			}
		}
	}
	
	public void moveZoss(){
		for (int i = 0; i < zosses.size(); i++){
			Zoss z = (Zoss) zosses.get(i);
			if (z.getLife()>0){
				z.move();
			} else zosses.remove(i);
		}
	}
	
	public void moveEnemy(){

		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = (Enemy) enemies.get(i);
			if(e.getLife()>0) {
				e.move();
			}
		}	
	}		
		
		
	

	public void checkCollisions() {								//checking collisions of objects with another object
		
		Rectangle rChar = cha.getBounds();
	
		
		if(cha.getST()) {
			Sword sword = cha.getSword();
			Rectangle rSword = sword.getBounds();
			
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = (Enemy) enemies.get(i);
				if(e.getLife()>0) {
					Rectangle rEnemy = e.getBounds();
					if(rSword.intersects(rEnemy)) {
						e.damage(cha.getSDMG());
					}
				}
				else {
					enemies.remove(i);
					cha.addXP(1);
					levelup = true;
				}
			}
			/*for(int i = 0; i<traps.size();i++) {
				Trap t = (Trap) traps.get(i);
				Rectangle rTrap = t.getBounds();
				if(rTrap.intersects(rSword)){
					
				}
			}*/
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
						e.movecollide();
					}
				}
				else {
					enemies.remove(i);
					cha.addXP(1);
					levelup = true;
				}

			}
		}
		if (checkpointactivated) {
			for (int i = 0; i < checkpoints.size(); i++) {
				Checkpoint c = (Checkpoint) checkpoints.get(i);
				Rectangle rCheckpoint = c.getBounds();
			
				if (rChar.intersects(rCheckpoint)){
				c.setActivated(true);								//setzt checkpoint bei beruehrung auf activated
				reset = mapNumber;

					if (cha.getDX()>0) {
						cha.addX(-3);
					}
				
					if (cha.getDX()<0) {
						cha.addX(3);
					}
				
					if (cha.getDY()>0) {
						cha.addY(-3);
					}
				
					if (cha.getDY()<0) {
						cha.addY(3);
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
					Sounds.play(1);
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
					e.movecollide();
					/*if (cha.getDX()>0) {
						cha.addX(-10);
					}
					
					if (cha.getDX()<0) {
						cha.addX(10);
					}
					
					if (cha.getDY()>0) {
						cha.addY(-10);
					}
					
					if (cha.getDY()<0) {
						cha.addY(10);
					}*/
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
					e.damage(cha.getFDMG());
				}
			}}
			else {
				enemies.remove(k);
				cha.addXP(1);
				levelup = true;
			}
		}
		
		for (int i = 0; i<manap.size(); i++) {
			Manapotion m = (Manapotion) manap.get(i);
			if(m.isVisible()){
			Rectangle rMana = m.getBounds();
			
				if(rChar.intersects(rMana)) {
					cha.setManapotion();
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
				cha.setHealthpotion();
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
		
		for (int i= 0; i < bosses.size(); i++) {
			Boss b = (Boss) bosses.get(i);
			if(b.getLife()>0) {
			Rectangle rBoss = b.getBounds();			
			for (int j = 0; j < trees.size(); j++) {
				Tree t = (Tree) trees.get(j);
				Rectangle rTree = t.getBounds();
				if (rBoss.intersects(rTree)){
					b.movecollide();
				}	
			}
			if (rChar.intersects(rBoss)) {
				cha.dmg(b.getDmg());
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
			
			else if(cha.getContinues() == 0) ingame = false;

		for(int k = 0; k<arrows.size();k++) {
			Arrow a = (Arrow) arrows.get(k);
			if(a.getBounds().intersects(rBoss)) {
				a.setVisible(false);
				b.damage(a.getDmg());
			}
		}
		if(cha.getSmile()){
			Rectangle rSmile = cha.getBoundsSmile();
			if(rSmile.intersects(rBoss)) {
				b.damage(1);
				b.movecollide();
			}
		}
		if(cha.getST()){
			Sword sword = (Sword) cha.getSword();
			if(sword.getBounds().intersects(rBoss)){
				b.damage(cha.getSDMG());
				b.movecollide();
			}
		}
		for(int j = 0; j<fball.size(); j++) {
			Feuerball f = (Feuerball) fball.get(j);
			if(f.getBounds().intersects(rBoss)) {
				f.setVisible(false);
				b.damage(cha.getFDMG());
			}
		}
		}
			else {
				cha.addXP(10);
				levelup = true;
				dialog3();
				mapNumber = 220;
				reset = 220;
				NumberofCheckpoints = 0;
				try {
					initMap(mapNumber, 51, 240);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		for (int i= 0; i < xosses.size(); i++) {
			Xoss x= (Xoss) xosses.get(i);
			if(x.getLife()>0) {
			Rectangle rBoss = x.getBounds();
			Rectangle rBoss2 = x.getBoundsX();
			for (int j = 0; j < trees.size(); j++) {
				Tree t = (Tree) trees.get(j);
				Rectangle rTree = t.getBounds();
				if (rBoss.intersects(rTree)){
					x.movecollide();
				}	
			}
			if (rChar.intersects(rBoss2)) {
				cha.dmg(x.getDmg());
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
			else if(cha.getContinues() == 0) ingame = false;
			
		for(int k = 0; k<arrows.size();k++) {
			Arrow a = (Arrow) arrows.get(k);
			if(a.getBounds().intersects(rBoss2)) {
				a.setVisible(false);
				x.damage(a.getDmg());
			}
		}
		
		for(int j = 0; j<fball.size(); j++) {
			Feuerball f = (Feuerball) fball.get(j);
			if(f.getBounds().intersects(rBoss2)) {
				f.setVisible(false);
				x.damage(cha.getFDMG());
			}
		}
		if(cha.getSmile()){
			Rectangle rSmile = cha.getBoundsSmile();
			if(rSmile.intersects(rBoss2)) {
				x.damage(1);
				x.movecollide();
			}
		}
		if(cha.getST()){
			Sword sword = (Sword) cha.getSword();
			if(sword.getBounds().intersects(rBoss2)){
				x.damage(cha.getSDMG());
			}
		}
		}
			else {
			cha.addXP(20);
			levelup = true;
			dialog4();
			mapNumber = 310;
			reset = 310;
			NumberofCheckpoints = 0;
			try {
				initMap(mapNumber, 51, 240);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
		if(cha.haveSword()==false){
		for (int j = 0; j < falsetrees.size(); j++) {
			Tree t = (Tree) falsetrees.get(j);
			Rectangle rTree = t.getBounds();
			
			if (rChar.intersects(rTree)) { //stop at touching tree
				
				if (cha.getDX()>0) {
					if(cha.getDX()==1) cha.addX(-1);
					else cha.addX(-3);
				}
				
				if (cha.getDX()<0) {
					if(cha.getDX()==1) cha.addX(1);
					else cha.addX(3);
				}
				
				if (cha.getDY()>0) {
					if(cha.getDY() == 1) cha.addY(-1);
					else cha.addY(-3);
				}
				
				if (cha.getDY()<0) {
					if(cha.getDY() == -1) cha.addY(1);
					else cha.addY(3);
				}
				

			}
		}}
		
		for (int j = 0; j < trees.size(); j++) {
			Tree t = (Tree) trees.get(j);
			Rectangle rTree = t.getBounds();
			
			if (rChar.intersects(rTree)) { //stop at touching tree
				
				if (cha.getDX()>0) {
					if(cha.getDX()==1) cha.addX(-1);
					else cha.addX(-3);
				}
				
				if (cha.getDX()<0) {
					if(cha.getDX()==1) cha.addX(1);
					else cha.addX(3);
				}
				
				if (cha.getDY()>0) {
					if(cha.getDY() == 1) cha.addY(-1);
					else cha.addY(-3);
				}
				
				if (cha.getDY()<0) {
					if(cha.getDY() == -1) cha.addY(1);
					else cha.addY(3);
				}
				

			}
		for (int z = 0; z < npc.size(); z++) {
			npc n = (npc) npc.get(z);
			Rectangle rnpc = n.getBounds();
					
			if (rChar.intersects(rnpc)) { //stop at touching npc
				switch (n.getDialog()){		
				case 1 : {	if (cha.getDX()>0) {
								cha.setDX(0);
								cha.addX(-5);
								dialog1();
							}
						
							if (cha.getDX()<0) {
								cha.setDX(0);
								cha.addX(5);
								dialog1();
							}
						
							if (cha.getDY()>0) {
								cha.setDY(0);
								cha.addY(-5);
								dialog1();
							}
						
							if (cha.getDY()<0) {
								cha.setDY(0);
								cha.addY(5);
								dialog1();
							}
				}
				case 2 : {
					if (cha.getDX()>0) {
						cha.setDX(0);
						cha.addX(-5);
						dialog2();
					}
				
					if (cha.getDX()<0) {
						cha.setDX(0);
						cha.addX(5);
						dialog2();
					}
				
					if (cha.getDY()>0) {
						cha.setDY(0);
						cha.addY(-5);
						dialog2();
					}
				
					if (cha.getDY()<0) {
						cha.setDY(0);
						cha.addY(5);
						dialog2();
					}
				}
			}
		}
		for (int h = 0; h < shop.size(); h++) {
			shopkeeper s = (shopkeeper) shop.get(h);
			Rectangle rshop = s.getBounds();
				
			if (rChar.intersects(rshop)) { //stop at touching shopkeeper and dialog
				
				if (cha.getDX()>0) {
					cha.setDX(0);
					cha.addX(-5);
					shop();
				}
					
				if (cha.getDX()<0) {
					cha.setDX(0);
					cha.addX(5);
					shop();
				}
					
				if (cha.getDY()>0) {
					cha.setDY(0);
					cha.addY(-5);
					shop();
				}
					
				if (cha.getDY()<0) {
					cha.setDY(0);
					cha.addY(5);
					shop();
				}
				
					

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
		
	
	
	for (int i= 0; i < zosses.size(); i++) {
		Zoss z = (Zoss) zosses.get(i);
		if(z.getLife()>0) {
		Rectangle rBoss = z.getBounds();
		Rectangle rBoss2 = z.getBoundsZ();
		for (int p = 0; p < trees.size(); p++) {
			Tree o = (Tree) trees.get(p);
			Rectangle rTreeX = o.getBounds();
			if (rBoss.intersects(rTreeX)){
				z.movecollide();
			}	
		}
		if ((rChar.intersects(rBoss2))) {
			cha.dmg(z.getDmg());
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
		else if(cha.getContinues() == 0) ingame = false;
		
	for(int k = 0; k<arrows.size();k++) {
		Arrow a = (Arrow) arrows.get(k);
		if(a.getBounds().intersects(rBoss2)) {
			a.setVisible(false);
			z.damage(a.getDmg());
		}
	}
	for(int h = 0; h<fball.size(); h++) {
		Feuerball f = (Feuerball) fball.get(h);
		if(f.getBounds().intersects(rBoss2)) {
			f.setVisible(false);
			z.damage(cha.getFDMG());
		}
	}if(cha.getSmile()){
		Rectangle rSmile = cha.getBoundsSmile();
		if(rSmile.intersects(rBoss2)) {
			z.damage(1);
			z.movecollide();
		}
	}
	if(cha.getST()){
		Sword sword = (Sword) cha.getSword();
		if(sword.getBounds().intersects(rBoss2)){
			z.damage(cha.getSDMG());
		}
	}
	}
		else {
			win = true;
			ingame = false;
		}
	}}
	}
	
	public void initMap(int m, int j ,int k) throws IOException {
		int i = 1;													//loop variables
		int x = 50;
		int y = 25;
		char[] prototypemap = new char[110];
		checkpointactivated = false;
		
		NumberofXosses = 0;
		NumberofZosses = 0;
		NumberofBosses = 0;
		NumberofTraps = 0;
		NumberofTrees = 1;
		Spawnpoints = 0;
		NumberofEnemies = 0;
		NumberofFalsetrees = 0;
		goals = 0;
		items = 0;
		maxnpc = 0;
		manapotions = 0;
		healthpotions = 0;
		maxcoin = 0;
		maxshops = 0;
		
		
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
			
			case 'v' : {											// e : enemy moving horizontal
				posE1[NumberofEnemies] = x;
				posE2[NumberofEnemies] = y;
				posEDIR[NumberofEnemies] = 1;
				
				NumberofEnemies++;
				break;
			}
			
			case 's' : {											// e2 : enemy moving vertical
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
				posB1[NumberofBosses] = x;
				posB2[NumberofBosses] = y;
				
				NumberofBosses++;
				break;
			}
			
			case 'x' : {											// x : boss
				posX1[NumberofXosses] = x;
				posX2[NumberofXosses] = y;
				
				NumberofXosses++;
				break;
			}
			
			case 'z' : {											// z : boss
				posZ1[NumberofZosses] = x;
				posZ2[NumberofZosses] = y;
				
				NumberofZosses++;
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
				npcX[maxnpc] = x;
				npcY[maxnpc] = y;
				npcD[maxnpc] = 1;
				maxnpc++;
				break;
			}
			case 'q' : {                                            // q : npc f�r schwert
				npcX[maxnpc] = x;
				npcY[maxnpc] = y;
				npcD[maxnpc] = 2;
				maxnpc++;
				break;
			}
			case 'p' : {											// p : shopkeeper
				shopX[maxshops] = x;
				shopY[maxshops] = y;
				maxshops++;
				initshop();
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
			case '�' : {
				falsetreeX[NumberofFalsetrees] = x;
				falsetreeY[NumberofFalsetrees] = y;
				NumberofFalsetrees++;
				initfalsetrees();
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
		initBoss();
		initnpc();
		initshop();
		initXoss();
		initZoss();
		initfalsetrees();
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
			if (ingame)
			{
				cha.keyPressed(e);
			}
			else
			{
				add(new menu());
			}
		}
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_H) dialog();
			else cha.keyReleased(e);
		}
		
	}
}
