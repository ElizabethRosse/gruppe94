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

	private ArrayList<Enemy> enemies;

	private Enemy enemy1;
	private goal goal;

	private Image image, imagescaled;
	private boolean ingame;
	private boolean win;
	private int G_WIDTH, G_HEIGHT;
	private int[] pos1 = new int[800]; 	//später ändern für verschiedene Maps
	private int[] pos2 = new int[800];
	private int[] posE1 = new int[800];
	private int[] posE2 = new int[800];
	private int mapNumber = 1;
	
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
		
		try {
			initMap(mapNumber, 40, 220);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initTrees();
		initEnemies();
		
		enemy1 = new Enemy (400, 400);	// erstelle Enemy Objekt mit Koordinaten
		goal = new goal (300, 275);     // erstellt Ziel mit Koordinaten
		
		timer = new Timer(5, this);
		timer.start();
		repaint();
	}
	
	public void addNotify() {  //holt höhe und breite des Fensters um Game Over naricht mittig zu platzieren
		super.addNotify();
		G_WIDTH = getWidth();
		G_HEIGHT = getHeight();
	}
	
	public void initTrees() {
		trees = new ArrayList<Tree>();
		
		for (int i=0; i < pos1.length ; i++) {
			trees.add(new Tree(pos1[i], pos2[i]));
		}
	}
	
	public void initEnemies() {
		enemies = new ArrayList<Enemy>();
		
		for (int i=0; i < posE1.length ; i++) {
			enemies.add(new Enemy(posE1[i], posE2[i]));
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		if (ingame) { //zeichne Character, Bäume usw.  wenn ingame = true ist
			
			Graphics2D g2d = (Graphics2D)g;
			
			g2d.drawImage(imagescaled, 0, 0, this);   // lädt das Hintergrundbild
			
			if (cha.isVisible())
				g2d.drawImage(cha.getImage(), cha.getX(), cha.getY(), this);
			
			for (int i = 0; i <trees.size(); i++) {
				Tree t = (Tree) trees.get(i);
				g2d.drawImage(t.getImage(), t.getX(), t.getY(), this);
			
			}
			for (int k = 0; k<enemies.size(); k++) {		// zeichne Gegner
				Enemy e = (Enemy) enemies.get(k);
				g2d.drawImage(e.getImage(), e.getX(), e.getY(), this);
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
		
		/**if (cha.isVisible() == false) {
			ingame = false; //für berührung mit Gegner
		}*/
		
		if(cha.getX()>490 && mapNumber==1){ 						//Mapwechsel von 1 zu 2
			mapNumber++;
			try {
				initMap(mapNumber, 40, 220);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 									//ruft changeMap mit neuer Map-Nummer und x y (startposition 100,225) fuer char auf
																	//erhoeht die Map-Nummer fuer die if-abfrage
		}
		else if(cha.getY()>470 && mapNumber==2){
			mapNumber++;
			try {
				initMap(mapNumber, 40 ,200);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		else if(cha.getX()<10 && mapNumber==2){ //Ausgaenge 2 zu 1 und 3 zu 2
			mapNumber--;
			try {
				initMap(mapNumber, 480, 225);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		else if(cha.getX()<10 && mapNumber==3){
			mapNumber--;
			try {
				initMap(mapNumber, 450 , 460);
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
		
		for (int k = 0; k < enemies.size(); k++) {
			Enemy e = (Enemy) enemies.get(k);
			Rectangle rEnemy = e.getBounds();
			
			if (rChar.intersects(rEnemy)) { 		//Game over at touching enemy
				ingame = false;		
			}  
		}
		
		Rectangle rGoal = goal.getBounds();
		Rectangle rEnemy = enemy1.getBounds();
		
		if (mapNumber == 2){
		if (rChar.intersects(rEnemy)){			//Game Over bat touching enemy
			ingame = false;
		}}
		
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
		}
	}
	
	public void initMap(int m, int j ,int k) throws IOException {
		int i = 0;
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		int x = 0;
		int y = 0;
		char prototypemap[] = getMap(m);
		while(i < 50) {												//max wert noch andern
			
			switch(prototypemap[i]) {
			case '#' : {											// # : wall
				pos1[a] = x;
				pos2[a] = y;
				a++;
				break;
			}
			case 's' : {											// s : spawn
				b++;
				break;
			}
			case 'e' : {											// e : enemy
				posE1[c] = x;
				posE2[c] = y;
				c++;
				break;
			}
			case 'b' : {											// b : boss
				d++;
				break;
			}
			case 'g' : {											// g : goal
				e++;
				break;
			}
			case 'i' : {											// i : item
				f++;
				break;
			}
			case 'n' : {											// n : npc
				g++;
				break;
			}
			default : {
				break;
			}
			}
			
		x = x + 50;
		if(i % 10 == 0) {
			y = y + 50;
		}
		i++;
		}
		
		cha.setX(j);
		cha.setY(k);
		
		initTrees();
		initEnemies();
	}
	
	
	public char[] getMap(int m) throws IOException {
		FileReader datei;
		BufferedReader dat; 
		char[] prototypemap = new char[800];
		switch(m) {																		//dateipfad noch festlegen
		case 1 : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
				dat = new BufferedReader(datei);							//map1				
			break;
		}
		case 2 : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
				dat = new BufferedReader(datei);							//map2				
			break;
		}
		case 3 : {			
			datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
			dat = new BufferedReader(datei);							//map3				
		break;
		}
		case 4 : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
				dat = new BufferedReader(datei);							//map4			
			break;
		}
		case 5 : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
				dat = new BufferedReader(datei);							//map5			
			break;
		}
		case 6 : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
				dat = new BufferedReader(datei);							//map6				
			break;
		}
		case 7 : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
				dat = new BufferedReader(datei);							//map7			
			break;
		}
		case 8 : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1.txt");
				dat = new BufferedReader(datei);							//map8			
			break;
		}
		case 9 : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
				dat = new BufferedReader(datei);							//map9			
			break;
		}
		default : {
				datei = new FileReader("C:\\Users\\Oliver Heldt\\workspace\\gruppe94\\src\\game\\maps\\map1");
				dat = new BufferedReader(datei);							//map1
		}
		}
		
		String line;
		line = dat.readLine();
		
		while(line != null) {														//read a line
			for (int i=0; i<line.length(); i++) {									//read a symbol
				prototypemap[i] = line.charAt(i); 									// save the symbol in an array
			}
			line = dat.readLine();													// read a new line
			dat.close();
		}
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
