/*package menu;

import javax.swing.JFrame;
import game.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Frame extends JFrame {
	
	static final long serialVersionUID = 1L;

	private menu menu;
	private game game;

	public static void main(String[] args)
	{
		new Frame();
	}
	
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setTitle("Game");
		setSize(530, 530);
		setLocationRelativeTo(null);
		//setResizable(false);
		
		add(menu);
		
		setVisible(true);
		
		if(menu.Check()==1) {
			this.removeAll();
			game();
		}
		
	}
	
	public void game(){
		add(game);
	}

}*/
