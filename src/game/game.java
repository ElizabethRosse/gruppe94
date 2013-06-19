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
	private ArrayList<Checkpoint> checkpoints;

	private goal goal;

	private Image image, imagescaled;
	private boolean ingame;
	private boolean win;
	private int G_WIDTH, G_HEIGHT;
	private int[] pos1 = new int[110]; 	//später ändern für verschiedene Maps
	private int[] pos2 = new int[110];
	private int[] posE1 = new int[110];
	private int[] posE2 = new int[110];
	private int[] ManapotionX = new int[110];
	private int[] ManapotionY = new int[110];
	private int[] HealthpotionX = new int[110];
	private int[] HealthpotionY = new int[110];
	private int[] checkpointX = new int [110];
	private int[] checkpointY = new int [110];
	private int mapNumber = 1;
	int NumberofTrees = 1;
	int Spawnpoints = 0;
	int NumberofEnemies = 0;
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
		
		ImageIcon ii =                       //lädt ein Grass image und skaliert es groesser damit es das Sichtfeld abdeckt
				new ImageIcon(this.getClass().getResource("images/grass.jpg"));
		image = ii.getImage();
		imagescaled = image.getScaledInstance(530, 530, UNDEFINED_CONDITION);
		
		
		setSize(500, 500);
		
		cha = new Char();
		initArrows();
		initfball();
		
		try {
			initMap(mapNumber, 51, 220);
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
	
	public void addNotify() {  //holt höhe und breite des Fensters um Game Over naricht mittig zu platzieren
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
			enemies.add(new Enemy(posE1[i] + 13, posE2[i] + 13));
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		if (ingame) { //zeichne Character, Bäume usw.  wenn ingame = true ist
			
			Graphics2D g2d = (Graphics2D)g;
			
			g2d.drawImage(imagescaled, 0, 0, this);   // lädt das Hintergrundbild
			
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
			
			for (int k = 0; k<enemies.size(); k++) {		// zeichne Gegner
				Enemy e = (Enemy) enemies.get(k);
				if (e.isVisible()) g2d.drawImage(e.getImage(), e.getX(), e.getY(), this);
			}
			for (int i = 0; i < checkpoints.size(); i++) {
				Checkpoint c = (Checkpoint) checkpoints.get(i);
				if(c.active()) g2d.drawImage(c.getImageac(), c.getX(), c.getY(), this);
				else g2d.drawImage(c.getImagein(), c.getX(), c.getY(), this);
			}

			if (mapNumber == 3) g2d.drawImage(goal.getImage(), goal.getX(), goal.getY(),this);              //  zeichne Ziel auf karte 3
			
			g2d.setColor(Color.BLACK);
			g2d.drawString("Targets left: 1", 5, 15);
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
		g.dispose(); //wie final verhindert Änderung des JFrames
		
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
		
		if(cha.getX()>490 && mapNumber < 9) {
			mapNumber++;
			try {
				initMap(mapNumber, 51, 220);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 																								//increasing mapNumber with starting positions

		}
		else if(cha.getX()<10 && mapNumber >= 1) { 															//decrease mapNumber
			mapNumber--;
			try {
				initMap(mapNumber, 480, 225);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
		cha.move();
		checkCollisions();
		repaint();
	}
	
	public void checkCollisions() {
		
		Rectangle rChar = cha.getBounds();
		
		if(cha.getSmile()) {
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
		
		for (int k = 0; k < enemies.size(); k++) {
			Enemy e = (Enemy) enemies.get(k);
			if(e.getLife()>0) {
			Rectangle rEnemy = e.getBounds();
			
			if (rChar.intersects(rEnemy)){    //schaden bei Berühung mit Gegner
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
		
		Rectangle rGoal = goal.getBounds();
		
		if (mapNumber == 3){
		if (rChar.intersects(rGoal)){
			ingame = false;
			win = true;
		}}
		
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
		int y = 0;
		char[] prototypemap = new char[110];
		
		
		NumberofTrees = 1;
		Spawnpoints = 0;
		NumberofEnemies = 0;
		bosses = 0;
		goals = 0;
		items = 0;
		npcs = 0;
		manapotions = 0;
		healthpotions = 0;
		
		
		pos1[0] = 0;
		pos2[0] = 0;
		
		
		prototypemap = getMap(m);
		
		while(i < 110) {												//maximum of fields on a map: 110
			
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
			case 's' : {											// s : spawn
				Spawnpoints++;
				break;
			}
			case 'e' : {											// e : enemy
				posE1[NumberofEnemies] = x;
				posE2[NumberofEnemies] = y;
				NumberofEnemies++;
				break;
			}
			case 'b' : {											// b : boss
				bosses++;
				break;
			}
			case 'g' : {											// g : goal
				goals++;
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
		initManap();
		initHealthp();
		initCheckpoints();
	}
	
	
	public char[] getMap(int m) throws IOException {
		FileReader datei;
		BufferedReader dat; 
		char[] prototypemap = new char[110];
		switch(m) {	
		case 1 : {
				datei = new FileReader("src\\game\\maps\\map1");
				dat = new BufferedReader(datei);																				//map1
			break;
		}
		case 2 : {
				datei = new FileReader("src\\game\\maps\\map2");
				dat = new BufferedReader(datei);																				//map2		
			break;
		}
		case 3 : {			
			datei = new FileReader("src\\game\\maps\\map3");
			dat = new BufferedReader(datei);																					//map3				
		break;
		}
		case 4 : {
				datei = new FileReader("src\\game\\maps\\map4");
				dat = new BufferedReader(datei);																				//map4			
			break;
		}
		case 5 : {
				datei = new FileReader("src\\game\\maps\\map5");
				dat = new BufferedReader(datei);																				//map5			
			break;
		}
		case 6 : {
				datei = new FileReader("src\\game\\maps\\map6");
				dat = new BufferedReader(datei);																				//map6				
			break;
		}
		case 7 : {
				datei = new FileReader("src\\game\\maps\\map7");
				dat = new BufferedReader(datei);																				//map7			
			break;
		}
		case 8 : {
				datei = new FileReader("src\\game\\maps\\map8");
				dat = new BufferedReader(datei);																				//map8			
			break;
		}
		case 9 : {
				datei = new FileReader("src\\game\\maps\\map9");
				dat = new BufferedReader(datei);																				//map9			
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
