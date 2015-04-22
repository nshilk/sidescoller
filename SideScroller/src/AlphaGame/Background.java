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

import java.util.Date;

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


	Path wall1 = Paths.get("Resources/Images/Levels/firstLevel.png");
	Path wall2 = Paths.get("Resources/Images/Levels/secondLevel.png");
	Path wall3 = Paths.get("Resources/Images/Levels/thirdLevel.png");

	int lvlID;
	
	int tick = 5;

	Date date = new Date();
	
	boolean stuck;
	
	int startingHeight;
	
	int landingHeight;

	/** 
	 *   Constructs the new Background object with a timer, a new character object, and an action listener
	 * @throws FileNotFoundException 
	 */
	public Background(String name) throws FileNotFoundException{
		guy = new Character();

		addKeyListener(new AL());

		time = new Timer(tick, this);
		time.start();

		obstacleInit(name);

		ImageIcon i=null;


		if(lvlID==1){
			i = new ImageIcon(wall1.toString());
		}
		if(lvlID==2){
			i = new ImageIcon(wall2.toString());
		}
		if(lvlID==3){
			i = new ImageIcon(wall3.toString());
		}

		background = i.getImage();
		stuck=false;
		landingHeight=225;
		startingHeight=225;
		backX=0;

	}

	private void obstacleInit(String level) throws FileNotFoundException{
		File file = new File(level);    
		Scanner scan = new Scanner(file);

		int i=0;

		lvlID=scan.nextInt();

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
		requestFocus();
		guy.move();

		if(backX!=-3500)
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


//		for (i=0; i<holes; i++) {
//			if (getX() >= holeStart[i]-50 && getX() <= holeEnd[i]-50 && v == 225) {
//				v = 440;
//				failure=true;
//
//			}
//		}
//		for (i=0; i<redBox; i++) {
//			if (getX() >= redBoxStart[i]-50 && getX() <= redBoxEnd[i]-50 && v >= 220 - boxHeight) {
//				v = 440;
//				failure=true;
//			}
//		}
		
		boolean otherStuck= false;
		boolean withinBox = false;
		
		for (i=0; i<greenBox; i++) {

			if (getX() >= greenBoxStart[i]-50 &&getX() <= greenBoxEnd[i]-50) {	
				landingHeight= 225-boxHeight;
				withinBox=true;
				
				
				if (v >= 225) {
				otherStuck = true;
				guy.setStuck(true);
				guy.setdx(-1);
				}
				else{
					guy.setStuck(false);
					
				}
				
			}
			if(!otherStuck){
				guy.setStuck(false);
			}else{
				if(guy.getX()<0){
					failure=true;
				}
			}
			if(!withinBox){
				landingHeight = 225;
				if(done){
					v=landingHeight;
				}
				
			}
			
		}
		otherStuck = false;
		withinBox=false;
		
		
//		if (getX() < 0) {
//			failure = true;
//			v=440;
//		}

		
//		if(guy.getX()<0||guy.getX()>800){
//			failure=true;
//		}
		
		if(guy.getX()<0||guy.getX()>700){
			guy.setdx(0);
		}

		if(failure){
			setFocusable(false);
			time.stop();
			try {
				Frame.lvlEnd();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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

		if((guy.getX()+(backX*(-1)))>Frame.getScore()){
			Frame.setScore((guy.getX()+(backX*(-1))));
		}

		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
		g2d.drawString("Your score: "+Frame.getScore(), 10, 25);

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

			cycle(startingHeight, landingHeight);

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


	public void cycle(int start, int land){
		if (!h){
			v-=2;
		}

		if(v==start-80){
			h = true;

		}
		if(h && v<=land){
			v+=2;
			if(v==land){
				done = true;
				guy.still = guy.i.getImage();
			}
		}
	}
}
