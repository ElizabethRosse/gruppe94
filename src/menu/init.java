package menu;

import game.game;

import javax.swing.JFrame;

public class init extends JFrame {
	
	public boolean close;

	private static final long serialVersionUID = 1L;

	public init() {
		add(new game());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game");
		setSize(510, 555);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
