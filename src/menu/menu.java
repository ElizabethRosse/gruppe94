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



import game.Sounds;

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



	public class menu extends JFrame
		{
		 static final long serialVersionUID = 1L;


		public static void main(String[] args)
		{
			new menu();
		}


        //constructor
		public menu() 
			{
			add(CreateMenuFrame());
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
			setTitle("Game");
			setSize(530, 530);
			setLocationRelativeTo(null);
			//setResizable(false);
			setVisible(true);
			}
			
			
		public JPanel CreateMenuFrame ()
			{
			
			
			
			final JPanel surface = new JPanel(new GridBagLayout());
			surface.setBackground(Color.WHITE);
			

			JButton start = new JButton("Start Game");			//start button
			
			start.setPreferredSize( new Dimension(150,25));
			
			start.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					dispose();								//closing old frame to prevent problems with the game panel
					new init();								//connection to init
					}
				});
			
			GridBagConstraints startc = new GridBagConstraints();
			startc.gridx = 0;
			startc.gridy = 1;
			
			
			
			JButton options = new JButton("Options");			//option button, not yet used
			
			options.setPreferredSize( new Dimension(150,25));
			
			options.addActionListener(new ActionListener()       
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					Sounds.play("src\\game\\sounds\\Trololo 8-bit.wav",1);							//hier muss noch eine verknupfung hin
					}
				});
			
			GridBagConstraints optionsc = new GridBagConstraints();
			optionsc.gridx = 0;
			optionsc.gridy = 3;
			
			
			
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
			closec.gridy = 5;
			
			
			
			
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
			
			
			surface.add(placeholder, placeholderc);
			surface.add(placeholder2, placeholder2c);
			surface.add(placeholder3, placeholder3c);
			surface.add(placeholder4, placeholder4c);
			surface.add(start, startc);
			surface.add(options, optionsc);
			surface.add(close, closec);
			
			return surface;
			}
	}

