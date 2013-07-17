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
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.Timer;

import game.MapEditor;
import game.game;
import game.Sounds;
import game.Server;
import game.Client;

	public class menu extends JFrame
		{
		 static final long serialVersionUID = 1L;
		 
		 private JPanel Surface;
		 private game game = new game(true, false, false);
		 private Server server = null;
		 private Client client = null;
		 private Timer timer, backgroundt, mplayer;


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
			setTitle("Smile Adventure");
			setSize(515, 538);
			setLocationRelativeTo(null);
			setResizable(false);
			setVisible(true);
			}
		
		public void rem(boolean check, boolean mplayer, boolean server) {
			this.remove(Surface);
			if (mplayer) {
				if (server) {
					game = new game(true, true, true);
					game.initMPlayerS(this.server);
				}
				else {
					game = new game(true, true, false);
					game.initMPlayerC(client);
				}
			}
			else game = new game(check, false, false);
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
		
		public void MCheck(){
			ActionListener MCheck = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (server.connection()) {
					mplayer.stop();
					rem(true, true, true);
				}
			}};
			mplayer = new Timer(5,MCheck);
			mplayer.start();
		}
		
		public void MCheckC(){
			ActionListener MCheck = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (client.connection()) {
					mplayer.stop();
					rem(true, true, false);
				}
			}};
			mplayer = new Timer(5,MCheck);
			mplayer.start();
		}
		
		public void newserver() {
			try {
				server = new Server();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if (server != null) {
					server.startServing();
					MCheck();
				}
			}
		}
		
		public void newclient() {
			client = new Client();
			if (client != null) MCheckC();
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
			
			JButton options = new JButton("Fortsetzen");			//after pause
			
			options.setPreferredSize( new Dimension(150,25));
			
			options.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
						pauseEnd();									//"restart" game
					}
				});
			
			GridBagConstraints optionsc = new GridBagConstraints();
			optionsc.gridx = 0;
			optionsc.gridy = 1;
			

			JButton start = new JButton("New Game");			//start button
			
			start.setPreferredSize( new Dimension(150,25));
			
			start.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					rem(true, false, false);
					}
				});
			
			GridBagConstraints startc = new GridBagConstraints();
			startc.gridx = 0;
			startc.gridy = 3;
			
			
			JButton save = new JButton("Save Game");             			//save button; working as intended
			
			save.setPreferredSize( new Dimension(150,25));
			
			save.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					if(game.getpause()){
						game.save();
					}
					}
				});
			
			
			GridBagConstraints savec = new GridBagConstraints();			//setting options for positioning in gridbag, some glues as placeholders to create space between buttons
			savec.gridx = 0;
			savec.gridy = 5;
			
			
			JButton load = new JButton("Load Game");			//load button
			
			load.setPreferredSize( new Dimension(150,25));
			
			load.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					rem(false, false, false);
					}
				});
			
			GridBagConstraints loadc = new GridBagConstraints();
			loadc.gridx = 0;
			loadc.gridy = 7;
			
			JButton multi = new JButton("Multiplayer");			//load button
			
			multi.setPreferredSize( new Dimension(150,25));
			
			multi.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{

					}
				});
			
			GridBagConstraints multic = new GridBagConstraints();
			multic.gridx = 0;
			multic.gridy = 9;
			
			
			JButton editor = new JButton("Map Editor");			//Map Editor button
			
			editor.setPreferredSize( new Dimension(150,25));
			
			editor.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					MapEditor Editor = new MapEditor();
					Editor.setVisible(true);
					}
				});
			
			GridBagConstraints editorc = new GridBagConstraints();
			editorc.gridx = 0;
			editorc.gridy = 11;
			
			
			
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
			closec.gridy = 13;
			
			JButton server = new JButton("server");             //close button; working as intended
			
			server.setPreferredSize( new Dimension(150,25));
			
			server.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
						newserver();					           
					}
				});
			
			
			GridBagConstraints serverc = new GridBagConstraints();			//setting options for positioning in gridbag, some glues as placeholders to create space between buttons
			serverc.gridx = 0;
			serverc.gridy = 15;
			
			JButton client = new JButton("Client");             //close button; working as intended
			
			client.setPreferredSize( new Dimension(150,25));
			
			client.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					newclient();					           
					}
				});
			
			
			GridBagConstraints clientc = new GridBagConstraints();			//setting options for positioning in gridbag, some glues as placeholders to create space between buttons
			clientc.gridx = 0;
			clientc.gridy = 17;
						
			
			Box placeholder3 = new Box(getDefaultCloseOperation());			// used to create space in menu frame
			
			GridBagConstraints placeholder3c = new GridBagConstraints();
			placeholder3c.gridx = 0;
			placeholder3c.gridy = 4;
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
			placeholder2c.gridy = 2;
			placeholder2c.fill = GridBagConstraints.BOTH;
			placeholder2c.weightx = 1.0;
			placeholder2c.weighty = 1.0;
			
			Box placeholder = new Box(getDefaultCloseOperation());
			
			GridBagConstraints placeholderc = new GridBagConstraints();
			placeholderc.gridx = 0;
			placeholderc.gridy = 0;
			placeholderc.fill = GridBagConstraints.BOTH;
			placeholderc.weightx = 1.0;
			placeholderc.weighty = 1.0;
			
			Box placeholder5 = new Box(getDefaultCloseOperation());			// used to create space in menu frame
			
			GridBagConstraints placeholder5c = new GridBagConstraints();
			placeholder5c.gridx = 0;
			placeholder5c.gridy = 8;
			placeholder5c.fill = GridBagConstraints.BOTH;
			placeholder5c.weightx = 1.0;
			placeholder5c.weighty = 1.0;
			
			Box placeholder6 = new Box(getDefaultCloseOperation());			// used to create space in menu frame
			
			GridBagConstraints placeholder6c = new GridBagConstraints();
			placeholder6c.gridx = 0;
			placeholder6c.gridy = 10;
			placeholder6c.fill = GridBagConstraints.BOTH;
			placeholder6c.weightx = 1.0;
			placeholder6c.weighty = 1.0;
			
			Box placeholder7 = new Box(getDefaultCloseOperation());			// used to create space in menu frame
			
			GridBagConstraints placeholder7c = new GridBagConstraints();
			placeholder7c.gridx = 0;
			placeholder7c.gridy = 12;
			placeholder7c.fill = GridBagConstraints.BOTH;
			placeholder7c.weightx = 1.0;
			placeholder7c.weighty = 1.0;
			
			Box placeholder8 = new Box(getDefaultCloseOperation());			// used to create space in menu frame
			
			GridBagConstraints placeholder8c = new GridBagConstraints();
			placeholder8c.gridx = 0;
			placeholder8c.gridy = 14;
			placeholder8c.fill = GridBagConstraints.BOTH;
			placeholder8c.weightx = 1.0;
			placeholder8c.weighty = 1.0;
			
			
			surface.add(placeholder, placeholderc);								//adding all components to menu frame
			surface.add(options, optionsc);
			surface.add(placeholder2, placeholder2c);
			surface.add(start, startc);
			surface.add(placeholder3, placeholder3c);
			surface.add(save, savec);
			surface.add(placeholder4, placeholder4c);
			surface.add(load, loadc);
			surface.add(placeholder5, placeholder5c);
			surface.add(multi, multic);
			surface.add(placeholder6, placeholder6c);
			surface.add(editor, editorc);
			surface.add(placeholder7, placeholder7c);
			surface.add(close, closec);
			surface.add(placeholder8, placeholder8c);
			surface.add(server, serverc);
			surface.add(client, clientc);
			
			return surface;
			}
	}

