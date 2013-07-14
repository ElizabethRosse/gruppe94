package game;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//import sun.audio.ContinuousAudioDataStream;

public class Sounds {
	   
	        // Note: use .wav files
	    	public static synchronized void play (int muse) {
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
	    		/*case 2: {
	    				try {
	    					final Clip clip = AudioSystem.getClip();
	    					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\Trololo.wav"));
	    					clip.open(inputStream);
	    					clip.start();
	    					
	    				} catch (Exception e) {
	    					System.out.println("play sound error: " + e.getMessage() + " for " + "Trololo.wav");
	    				}
	    				break;
	    		}*/
	    		case 3: {
    				try {
    					final Clip clip = AudioSystem.getClip();
    					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\obtain.wav"));
    					clip.open(inputStream);
    					clip.start();
    					
    				} catch (Exception e) {
    					System.out.println("play sound error: " + e.getMessage() + " for " + "obtain.wav");
    				}
    				break;
	    		}	   
	    		case 4: {
    				try {
    					final Clip clip = AudioSystem.getClip();
    					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\shot.wav"));
    					clip.open(inputStream);
    					clip.start();
    					
    				} catch (Exception e) {
    					System.out.println("play sound error: " + e.getMessage() + " for " + "shot.wav");
    				}
    				break;
	    		}	 	    	
	    		case 5: {
    				try {
    					final Clip clip = AudioSystem.getClip();
    					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\swing.wav"));
    					clip.open(inputStream);
    					clip.start();
    					
    				} catch (Exception e) {
    					System.out.println("play sound error: " + e.getMessage() + " for " + "swing.wav");
    				}
    				break;
	    		}	 	   	    		
	    		case 6: {
    				try {
    					final Clip clip = AudioSystem.getClip();
    					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\scream.wav"));
    					clip.open(inputStream);
    					clip.start();
    					
    				} catch (Exception e) {
    					System.out.println("play sound error: " + e.getMessage() + " for " + "scream.wav");
    				}
    				break;
	    		}		   
	    		case 7: {
    				try {
    					final Clip clip = AudioSystem.getClip();
    					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src\\game\\sounds\\levelup.wav"));
    					clip.open(inputStream);
    					clip.start();
    					
    				} catch (Exception e) {
    					System.out.println("play sound error: " + e.getMessage() + " for " + "levelup.wav");
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


