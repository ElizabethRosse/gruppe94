package menu;

/*Changelog
 * 
 * 5.5.13 : creating file      (Oliver Heldt)
 * 6.5.13 : added space between buttons (Oliver Heldt)
 * 7.5.13 : added static buttons (Oliver Heldt)
 *          added background color (Oliver Heldt)
 */
/* Description
 * opens the menu of the game with 3 buttons: start, options and close, black background
 */



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.Timer;

import game.game;
import game.Sounds;

	public class menu extends JFrame
		{
		 static final long serialVersionUID = 1L;
		 
		 private JPanel Surface;
		 private game game = new game(true);
		 //private boolean reset = false;
		 private Timer timer, backgroundt;


		public static void main(String[] args)
		{
			new menu();
		}


        //constructor
		public menu() 
			{
			Surface = CreateMenuFrame();
			
			
			add(Surface);
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
			setTitle("Ich will ein Glücksbärchie sein !!!");
			setSize(515, 538);
			setLocationRelativeTo(null);
			setResizable(false);
			setVisible(true);
			}
		
		public void rem(boolean check) {
			this.remove(Surface);
			game = new game(check);
			timer.start();
			add(game);
		}
		
		public void remG() {
			game.setVisible(false);
			add(Surface);
			
			
		}
		
		public void initBackgroundMusik() {
			
			ActionListener background = new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					Sounds.play(2);
				}
			};
			Sounds.play(2);
			backgroundt = new Timer(185000, background);
			backgroundt.start();
		}
		
		public void TimeCheck(){
			ActionListener TimeCheck = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (game.MENU()) {
					reset();
					timer.stop();
					
				}
			}};
			timer = new Timer(5,TimeCheck);
			timer.start();
		}
		
		public void reset() {
			game.setVisible(false);
			add(Surface);
		}
		
		public void pauseEnd() {
			if(game.getpause()){
				timer.start();
				game.retpause();
				game.setVisible(true);
				this.remove(Surface);
			}
		}
			
		public JPanel CreateMenuFrame ()
			{
			TimeCheck();
			initBackgroundMusik();
			
			final JPanel surface = new JPanel(new GridBagLayout());
			surface.setBackground(Color.WHITE);
			

			JButton start = new JButton("New Game");			//start button
			
			start.setPreferredSize( new Dimension(150,25));
			
			start.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					//dispose();								//closing old frame to prevent problems with the game panel
					//new init();								//connection to init
					
					rem(true);
					//TimeCheck();
					
					}
				});
			
			GridBagConstraints startc = new GridBagConstraints();
			startc.gridx = 0;
			startc.gridy = 3;
			
			JButton load = new JButton("Load Game");			//start button
			
			load.setPreferredSize( new Dimension(150,25));
			
			load.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					//dispose();								//closing old frame to prevent problems with the game panel
					//new init();								//connection to init
					
					rem(false);
					//TimeCheck();
					
					}
				});
			
			GridBagConstraints loadc = new GridBagConstraints();
			loadc.gridx = 0;
			loadc.gridy = 5;
			
			
			JButton options = new JButton("Fortsetzen");			//after pause
			
			options.setPreferredSize( new Dimension(150,25));
			
			options.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
						pauseEnd();					//hier muss noch eine verknupfung hin
					}
				});
			
			GridBagConstraints optionsc = new GridBagConstraints();
			optionsc.gridx = 0;
			optionsc.gridy = 1;
			
			
			
			JButton close = new JButton("Close Game");             //close button; working as intended
			
			close.setPreferredSize( new Dimension(150,25));
			
			close.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					System.exit(0);						           
					}
				});
			
			
			GridBagConstraints closec = new GridBagConstraints();			//setting options for positioning in gridbag, some glues as placeholders to create space between buttons
			closec.gridx = 0;
			closec.gridy = 7;
			
			
			
			
			Box placeholder3 = new Box(getDefaultCloseOperation());
			
			GridBagConstraints placeholder3c = new GridBagConstraints();
			placeholder3c.gridx = 0;
			placeholder3c.gridy = 0;
			placeholder3c.fill = GridBagConstraints.BOTH;
			placeholder3c.weightx = 1.0;
			placeholder3c.weighty = 1.0;
			
			Box placeholder4 = new Box(getDefaultCloseOperation());
			
			GridBagConstraints placeholder4c = new GridBagConstraints();
			placeholder4c.gridx = 0;
			placeholder4c.gridy = 6;
			placeholder4c.fill = GridBagConstraints.BOTH;
			placeholder4c.weightx = 1.0;
			placeholder4c.weighty = 1.0;
			
			Box placeholder2 = new Box(getDefaultCloseOperation());
			
			GridBagConstraints placeholder2c = new GridBagConstraints();
			placeholder2c.gridx = 0;
			placeholder2c.gridy = 4;
			placeholder2c.fill = GridBagConstraints.BOTH;
			placeholder2c.weightx = 1.0;
			placeholder2c.weighty = 1.0;
			
			Box placeholder = new Box(getDefaultCloseOperation());
			
			GridBagConstraints placeholderc = new GridBagConstraints();
			placeholderc.gridx = 0;
			placeholderc.gridy = 2;
			placeholderc.fill = GridBagConstraints.BOTH;
			placeholderc.weightx = 1.0;
			placeholderc.weighty = 1.0;
			
			Box placeholder5 = new Box(getDefaultCloseOperation());
			
			GridBagConstraints placeholder5c = new GridBagConstraints();
			placeholder5c.gridx = 0;
			placeholder5c.gridy = 8;
			placeholder5c.fill = GridBagConstraints.BOTH;
			placeholder5c.weightx = 1.0;
			placeholder5c.weighty = 1.0;
			
			
			surface.add(placeholder, placeholderc);
			surface.add(placeholder2, placeholder2c);
			surface.add(placeholder3, placeholder3c);
			surface.add(placeholder4, placeholder4c);
			surface.add(start, startc);
			surface.add(options, optionsc);
			surface.add(close, closec);
			surface.add(load, loadc);
			surface.add(placeholder5, placeholder5c);
			
			return surface;
			}
	}

