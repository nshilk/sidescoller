package AlphaGame;
import java.io.FileNotFoundException;


import java.util.Timer;

/** 
 *   The Main Class for the test game
 *   
 *   @author Team Two
 */
import javax.swing.JFrame;

public class Frame {
	
	static JFrame frame = new JFrame("2D Game");

	public Frame() throws FileNotFoundException{
		
		frame.add(new TitleScreen());
		
		//frame.add(new Background("Resources/LevelData/Level1.txt") );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 448);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);	
		
		
	}

	public static void lvlStart(String name) throws FileNotFoundException{
		frame.getContentPane().removeAll();
		frame.add(new Background(name));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 448);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.repaint();
		
	}
	public static void main(String[] args) throws FileNotFoundException{
		new Frame();
	}

	
	

}
