package AlphaGame;
/** 
*   The class containing the changes for the background
*   @author Team Two
*/

import java.awt.*;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class Background extends JPanel implements ActionListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	*   The creation of a "Character" object, guy
	*/
	Character guy;
	/** 
	*   The variable reference to the background image
	*/
	public Image background;
	/** 
	*   The creation of a "timer" object, time
	*/
	Timer time;
	int v=256;
	
	
	Thread animator;
	
	boolean k = false;
	boolean h = false;
	boolean done = false;
	/** 
	*   Variable holding the current x coordinate of the background image
	*/
	int backX;
	
	/** 
	*   Constructs the new Background object with a timer, a new character object, and an action listener
	*/
	public Background(){
		guy = new Character();
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon("Images/Levels/testA.png");
		background = i.getImage();
		time = new Timer(5, this);
		time.start();
	}
	
	/** 
	*   Holds the methods that are performed every time the timer "ticks"
	*/
	public void actionPerformed(ActionEvent e){
		guy.move();
		backX = backX - 1;
		repaint();
	}
	
	/** 
	*   Creates a 2D graphics object and is used to paint the new screen every time it is called
	*/
	
	
	public void paint(Graphics g){
		if(guy.dy == 1 && k==false ){
			k = true;
			animator = new Thread(this);
			animator.start();
		}
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, backX, 0, null);
			
		g2d.drawImage(guy.getImage(), guy.getX(), v, null);
			
	}
	
	/** 
	*   Extension of the KeyAdapter using the key methods in the Character class
	*/
	private class AL extends KeyAdapter{
		public void keyReleased(KeyEvent e){
			guy.keyReleased(e);
		}
		public void keyPressed(KeyEvent e){
			guy.keyPressed(e);
		}
	}
	
	public void run(){
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		while(done==false){
			
			cycle();
			
			timeDiff = System.currentTimeMillis()-beforeTime;
			sleep = 10 - timeDiff;
			if(sleep < 0){
				sleep = 2;
			}
			try{
					Thread.sleep(sleep);
			} catch(InterruptedException e){
				
			}
			beforeTime = System.currentTimeMillis();
			}
		done = false;
		k = false;
		h = false;
	}
	

	public void cycle(){
		if (h==false){
			v--;
		}
		
		if(v==200){
			h = true;
			
		}
		if(h==true && v<=256){
			v++;
			if(v==256){
				done = true;
				guy.still = guy.i.getImage();
			}
		}
	}
}
