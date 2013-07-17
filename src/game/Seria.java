package game;

import java.util.ArrayList;
import java.io.Serializable;

public class Seria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//private ArrayList<Feuerball> FBall = null;
	//private ArrayList<Arrow> arrows = null;
	//private Sword sword = null;
	//private boolean swordTrue = false;
	int x = 100, y = 100;
	
		public Seria () {
			
		}
		
		/*public void setSword(Sword sword) {
			this.sword = sword;
		}
		
		public void setSwordTrue(boolean sword) {
			swordTrue = sword;
		}
		
		public void setFBall(ArrayList<Feuerball> FBall) {
			this.FBall = FBall;
		}
		
		public void setArrows(ArrayList<Arrow> arrows) {
			this.arrows = arrows;
		}*/
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
	
		/*public Sword getSword() {
			return sword;
		}
		
		public boolean getSwordTrue() {
			return swordTrue;
		}
		
		public ArrayList<Feuerball> getFBall() {
			return FBall;
		}
		
		public ArrayList<Arrow> getArrows() {
			return arrows;
		}*/
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	
}
