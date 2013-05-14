package hhu.ProPra_SoSe13.Gruppe94;

import javax.swing.JFrame;

public class start extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public start() {
		add(new menu());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game");
		setLocationRelativeTo(null);
		//setSize(1000, 1000);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new start();

	}

}
