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

public class TitleScreen extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Image background;
	private Image arrow;

	Path title = Paths.get("Resources/Images/Title/TitleScreen.png");
	Path pointer = Paths.get("Resources/Images/Title/arrow.png");
	
	
	Path lvl1 = Paths.get("Resources/LevelData/Level1.txt");
	Path lvl2 = Paths.get("Resources/LevelData/Level2.txt");
	Path lvl3 = Paths.get("Resources/LevelData/Level3.txt");
	
	Timer think;
	
	int selector;
	boolean kRelease;
	

	/** 
	 *   Constructs the new Background object with a timer, a new character object, and an action listener
	 * @throws FileNotFoundException 
	 */
	public TitleScreen() throws FileNotFoundException{

		kRelease = true;
		selector = 148;
		
		addKeyListener(new Act());
		setFocusable(true);
		
		
		
		ImageIcon i = new ImageIcon(title.toString());
		background = i.getImage();
		ImageIcon j = new ImageIcon(pointer.toString());
		arrow = j.getImage();
		
		think = new Timer(1, this);
		think.start();


	}

	/** 
	 *   Holds the methods that are performed every time the timer "ticks"
	 */
	
	
	
	
	public void actionPerformed(ActionEvent e){
		
		repaint();
	}

	/** 
	 *   Creates a 2D graphics object and is used to paint the new screen every time it is called
	 */


	public void paint(Graphics g){

		super.paint(g);

		Graphics2D g3d = (Graphics2D) g;
		g3d.drawImage(background, 0, 0, null);
		g3d.drawImage(arrow, selector, 186, null);

	}

	/** 
	 *   Extension of the KeyAdapter using the key methods in the Character class
	 */
	
	private class Act extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();

			if(key == KeyEvent.VK_LEFT){
				
				if(selector==148&&kRelease){
					selector=577;
					kRelease=false;
				}
				if(selector==363&&kRelease){
					selector=148;
					kRelease=false;
				}
				if(selector==577&&kRelease){
					selector=363;
					kRelease=false;
				}

			}
			if(key == KeyEvent.VK_RIGHT){
				if(selector==148&&kRelease){
					selector=363;
					kRelease=false;
				}
				if(selector==363&&kRelease){
					selector=577;
					kRelease=false;
				}
				if(selector==577&&kRelease){
					selector=148;
					kRelease=false;
				}
			}
			
			if(key==KeyEvent.VK_ENTER){
				try {
					startLevel();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
		public void keyReleased(KeyEvent e){
			int key = e.getKeyCode();

			if(key == KeyEvent.VK_LEFT){
				kRelease=true;
			}
			if(key == KeyEvent.VK_RIGHT){
				kRelease=true;
			}
		}
		
	}
	

	
	public void startLevel() throws FileNotFoundException{
		setFocusable(false);
		if(selector==148){ //Level1
			Frame.lvlStart(lvl1.toString());
		}
		if(selector==363){ //Level2
			Frame.lvlStart(lvl2.toString());
		}
		if(selector==577){ //Level3
			Frame.lvlStart(lvl3.toString());
		}
		
	}

	


}
