package game;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	
	    public static synchronized void play(final String fileName) 
	    {
	        // Note: use .wav files
	 
	        new Thread(new Runnable() { 
	            public void run() {
	                try {
	                    Clip clip = AudioSystem.getClip();
	                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\trap.wav"));
	                    clip.open(inputStream);
	                    clip.start(); 
	                } catch (Exception e) {
	                    System.out.println("play sound error: " + e.getMessage() + " for " + "trap.wav");
	                }
	            }
	        }).start();
	    }
	}


