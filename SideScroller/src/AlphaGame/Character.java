package AlphaGame;
/** 
 *   The class containing the methods and variables for the character on the screen
 *   @author Team Two
 */
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.nio.file.*;

import javax.swing.ImageIcon;

public class Character {
	/** 
	 *   The "x" coordinate of the character
	 */
	int x;
	/** 
	 *   The "y" coordinate of the character
	 */
	int y;
	/** 
	 *   The change in "x"
	 */
	int dx;
	/** 
	 *   The change in "y"
	 */
	int dy;

	int left;



	/** 
	 *   The variable holding the reference to the character image
	 */
	Image still;
	ImageIcon i;
	ImageIcon j;

	Path still1 = Paths.get("Resources/Images/Character/stillTwo.png");
	Path jump1 = Paths.get("Resources/Images/Character/jumpTwo.png");

	/**
	 *	The constructor that assigns the mario image to the "still" variable and places the initial character in the bottom left corner of the screen
	 */
	public Character(){
		i = new ImageIcon(still1.toString());
		j = new ImageIcon(jump1.toString());

		still = i.getImage();
		x = 0;
		
		left = 150;
		y = 250;
	}

	/**
	 * 	Changes the "x" position of the character by adding change in "x" to "x"
	 */
	public void move(){
		x = x+dx;

	}

	/**
	 * 	Returns the current value of x
	 * @return x
	 */
	public int getX(){
		return x;
	}

	/**
	 * 	Returns the current value of y
	 * @return y
	 */
	public int getY(){
		return y;
	}

	public int getdx(){
		return dx;
	}
	
	public void setdx(int i){
		dx=i;
	}

	/**
	 * 	Returns the current image of the character
	 * @return still
	 */
	public Image getImage(){
		return still;
	}
	
	public void moveTo(int n) {
		x=n;
	
	}
	

	/**
	 * @param e
	 * 	Changes the value of dx (change in x) when a key is pressed
	 */
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT){
			dx = -1;
		}
		if(key == KeyEvent.VK_RIGHT){
			dx = 1;
		}
		if(key == KeyEvent.VK_UP){
			dy = 1;
			still = j.getImage();
		}
	}

	/**
	 * @param e
	 * 	Changes the value of dx (change in x) back to 0 when a key is released
	 */
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT){
			dx = 0;
		}
		if(key == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		if(key == KeyEvent.VK_UP){
			dy = 0;
			//still = i.getImage();
		}
	}
}
