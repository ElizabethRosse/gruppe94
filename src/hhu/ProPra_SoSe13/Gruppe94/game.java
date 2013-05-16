package hhu.ProPra_SoSe13.Gruppe94;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	private Char cha;
	private ArrayList<Tree> trees;
	private boolean ingame;
	private int G_WIDTH, G_HEIGHT;
	private int[] pos1 = {225}; 	//später ändern für verschiedene Maps
	private int[] pos2 = {225};
	//private Maps map;   			//zum holen von pos1 und pos2 für die Bäume der verschiedenen Maps
	
	public game() {
		
		addKeyListener(new KAdapter());
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		ingame = true;
		
		setSize(500, 500);
		
		cha = new Char();
		//map = new Maps;
		//pos1 = map.getPos1();
		//pos2 = map.getPos2();
		
		initTrees();
		
		timer = new Timer(5, this);
		timer.start();
		//repaint();
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
	
	public void paint(Graphics g) {
		super.paint(g);
		
		if (ingame) { //zeichne Character, Bäume usw.  wenn ingame = true ist
			
			Graphics2D g2d = (Graphics2D)g;
			
			if (cha.isVisible())
				g2d.drawImage(cha.getImage(), cha.getX(), cha.getY(), this);
			
			for (int i = 0; i <trees.size(); i++) {
				Tree t = (Tree) trees.get(i);
				g2d.drawImage(t.getImage(), t.getX(), t.getY(), this);
			}
			
			g2d.setColor(Color.BLACK);
			g2d.drawString("Targets left: 1", 5, 15);
		}
		
		else {
			String msg = "Game Over";
			Font small = new Font ("Ende", Font.BOLD, 14);
			FontMetrics metr = this.getFontMetrics(small);
			
			g.setColor(Color.white);
			g.setFont(small);
			g.drawString(msg, (G_WIDTH - metr.stringWidth(msg))/2, G_HEIGHT / 2);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose(); //wie final verhindert Änderung des JFrames
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/**if (cha.isVisible() == false) {
			ingame = false; //für berührung mit Gegner
		}*/
		
		cha.move();
		checkCollisions();
		repaint();
	}
	
	public void checkCollisions() {
		
		Rectangle rChar = cha.getBounds();
		
		for (int j = 0; j < trees.size(); j++) {
			Tree t = (Tree) trees.get(j);
			Rectangle rTree = t.getBounds();
			
			if (rChar.intersects(rTree)) { //stoppen bei berührung mit einem baum
				
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
				
			}   // noch Collision mit stehendem gegner und Ziel
		}
	}
	
	public class KAdapter extends KeyAdapter { 
		
		public void keyPressed(KeyEvent e) {
			cha.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			cha.keyReleased(e);
		}
		
	}
}
