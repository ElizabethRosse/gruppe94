package game;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.ContinuousAudioDataStream;

public class Sounds {
	
	    public static synchronized void play(final String fileName, int muse) 
	    {
	        // Note: use .wav files
	    	switch(muse) {
	    		case 1 : {
	    				try {
	    					Clip clip = AudioSystem.getClip();
	    					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\trap.wav"));
	    					clip.open(inputStream);
	    					clip.start(); 
	    				} catch (Exception e) {
	    					System.out.println("play sound error: " + e.getMessage() + " for " + "trap.wav");
	    				}
	    				break;
	    		}
	    		case 2: {
	    				try {
	    					Clip clip = AudioSystem.getClip();
	    					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\Trololo 8-bit.wav"));
	    					clip.open(inputStream);
	    					clip.start();
	    					
	    				} catch (Exception e) {
	    					System.out.println("play sound error: " + e.getMessage() + " for " + "Trololo.wav");
	    				}
	    				break;
	    		}
    			}
	    	
	    	
	        new Thread(new Runnable() { 
	            public void run() {
	            
	            }
	        }).start();
	    }
	}


