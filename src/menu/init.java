package menu;

import game.game;

import javax.swing.JFrame;

public class init extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public init() {
		
		add(new game());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game");
		setSize(530, 530);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
