package AlphaGame;
/** 
 *   The class containing the changes for the background
 *   @author Team Two
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.Scanner;

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
	int v=225;


	Thread animator;

	boolean k = false;
	boolean h = false;
	boolean done = false;

	boolean failure = false;
	/** 
	 *   Variable holding the current x coordinate of the background image
	 */
	int backX;


	int[] holeStart;
	int[] holeEnd;

	int i;
	final static int holeSize = 108;
	int holes;


	Path wall1 = Paths.get("Images/Levels/firstLevel.png");
	Path level = Paths.get("Images/Levels/Level1.txt");


	/** 
	 *   Constructs the new Background object with a timer, a new character object, and an action listener
	 * @throws FileNotFoundException 
	 */
	public Background() throws FileNotFoundException{
		guy = new Character();
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon(wall1.toString());
		background = i.getImage();
		time = new Timer(1, this);
		time.start();

		holeInit();
	}

	private void holeInit() throws FileNotFoundException{
		File file = new File(level.toString());    
		Scanner scan = new Scanner(file);

		int i=0;
		holes = scan.nextInt();
		holeStart = new int[holes];
		holeEnd = new int[holes];

		for(i=0;i<holes;++i){
			holeStart[i]=scan.nextInt();
			holeEnd[i]=holeStart[i]+holeSize;
		}
		scan.close();
	}

	/** 
	 *   Holds the methods that are performed every time the timer "ticks"
	 */
	public void actionPerformed(ActionEvent e){

		guy.move();


		backX = backX - 1;

		for (i=0; i<holes; i++) {
			holeStart[i] = holeStart[i]-1-guy.getdx();
			holeEnd[i] = holeEnd[i]-1-guy.getdx();
		}
		for (i=0; i<holes; i++) {
			if (getX() >= holeStart[i]-50 && getX() <= holeEnd[i]-50 && v == 225) {
				v = 440;
				failure=true;

			}
		}
		repaint();
	}

	/** 
	 *   Creates a 2D graphics object and is used to paint the new screen every time it is called
	 */


	public void paint(Graphics g){
		if(guy.dy == 1 && !k &&!failure){
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
		while(!done){

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
		if (!h){
			v--;
		}

		if(v==175){
			h = true;

		}
		if(h && v<=225){
			v++;
			if(v==225){
				done = true;
				guy.still = guy.i.getImage();
			}
		}
	}
}
