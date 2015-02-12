package AlphaGame;
/** 
*   The class containing the changes for the background
*   @author Team Two
*/

import java.awt.*;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class Background extends JPanel implements ActionListener {
	/** 
	*   The creation of a "Character" object, mario
	*/
	Character mario;
	/** 
	*   The variable reference to the background image
	*/
	Image background;
	/** 
	*   The creation of a "timer" object, time
	*/
	Timer time;
	
	/** 
	*   Variable holding the current x coordinate of the background image
	*/
	int backX;
	
	/** 
	*   Constructs the new Background object with a timer, a new character object, and an action listener
	*/
	public Background(){
		mario = new Character();
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon("Background.png");
		background = i.getImage();
		time = new Timer(1, this);
		time.start();
	}
	
	/** 
	*   Holds the methods that are performed every time the timer "ticks"
	*/
	public void actionPerformed(ActionEvent e){
		mario.move();
		//mario.jump();
		backX = backX - 1;
		repaint();
	}
	
	/** 
	*   Creates a 2D graphics object and is used to paint the new screen every time it is called
	*/
	public void paint(Graphics g){
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, backX, 0, null);
		g2d.drawImage(mario.getImage(), mario.getX(), mario.getY(), null);
			
		
	}
	
	/** 
	*   Extension of the KeyAdapter using the key methods in the Character class
	*/
	private class AL extends KeyAdapter{
		public void keyReleased(KeyEvent e){
			mario.keyReleased(e);
		}
		public void keyPressed(KeyEvent e){
			mario.keyPressed(e);
		}
	}
}
