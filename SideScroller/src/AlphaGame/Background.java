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
import java.util.Date;


import javax.swing.*;

public class Background extends JPanel implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;

	Character guy;
	public Image background;

	Timer time;
	int v=225;

	int i;

	Thread animator;

	boolean k = false;
	boolean h = false;
	boolean done = false;
	boolean failure = false;

	int backX;

	int holes;
	int[] holeStart;
	int[] holeEnd;
	final static int holeSize = 108;

	final static int boxSize = 27;
	final static int boxHeight = 27;
	final static int boxWidth = 27;
	int greenBox;
	int[] greenBoxStart;
	int[] greenBoxEnd;

	int redBox;
	int[] redBoxStart;
	int[] redBoxEnd;
	Date date = new Date();


	Path wall1 = Paths.get("Resources/Images/Levels/firstLevel.png");
	Path level = Paths.get("Resources/LevelData/Level1.txt");


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

		time = new Timer(5, this);
		time.start();

		obstacleInit();
	}

	private void obstacleInit() throws FileNotFoundException{
		File file = new File(level.toString());    
		Scanner scan = new Scanner(file);

		int i=0;

		if(scan.hasNext("hole")){
			scan.next();
			holes = scan.nextInt();
			holeStart = new int[holes];
			holeEnd = new int[holes];
			for(i=0;i<holes;++i){
				holeStart[i]=scan.nextInt();
				holeEnd[i]=holeStart[i]+holeSize;
			}
		}

		if(scan.hasNext("green")){
			scan.next();
			greenBox = scan.nextInt();
			greenBoxStart = new int[greenBox];
			greenBoxEnd = new int[greenBox];
			for(i=0;i<greenBox;++i){
				greenBoxStart[i]=scan.nextInt();
				greenBoxEnd[i]=greenBoxStart[i]+boxSize;
			}
		}

		if(scan.hasNext("red")){
			scan.next();
			redBox = scan.nextInt();
			redBoxStart = new int[redBox];
			redBoxEnd = new int[redBox];
			for(i=0;i<redBox;++i){
				redBoxStart[i]=scan.nextInt();
				redBoxEnd[i]=redBoxStart[i]+boxSize;
			}
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
		
		for (i=0; i<redBox; i++) {
			redBoxStart[i] = redBoxStart[i]-1-guy.getdx();
			redBoxEnd[i] = redBoxEnd[i]-1-guy.getdx();
			
		}
		
		for (i=0; i<greenBox; i++) {
			greenBoxStart[i] = greenBoxStart[i]-1-guy.getdx();
			greenBoxEnd[i] = greenBoxEnd[i]-1-guy.getdx();
			
		}
		
		
		
		for (i=0; i<holes; i++) {
			if (getX() >= holeStart[i]-50 && getX() <= holeEnd[i]-50 && v == 225) {
				v = 440;
				failure=true;

			}
		}
		for (i=0; i<redBox; i++) {
			if (getX() >= redBoxStart[i]-50 && getX() <= redBoxEnd[i]-50 && v >= 225 - boxHeight) {
				v = 440;
				failure=true;

			}
		}
		
		for (i=0; i<greenBox; i++) {
			long time = date.getTime();
			boolean isStuck = false;
			int box = 0;
			if (getX() >= greenBoxStart[i]-50 && getX() <= greenBoxEnd[i]-50) {
				if(!isStuck)
					box = i;
				
				isStuck = true;
				guy.moveTo(greenBoxStart[box]);
				
				

			}
			else isStuck = false;
		}
		if (getX() < 0) {
			failure = true;
			v=440;
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
			v = v - 2;
		}

		if(v==125){
			h = true;

		}
		if(h && v<=225){
			v= v+4;
			if(v==225){
				done = true;
				guy.still = guy.i.getImage();
			}
		}
	}
	
}
