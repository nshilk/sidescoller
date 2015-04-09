package AlphaGame;
import java.io.FileNotFoundException;


import java.util.Timer;


/** 
 *   The Main Class for the test game
 *   
 *   @author Team Two
 */
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame {
	
	static JFrame frame = new JFrame("2D Game");
	static TitleScreen title;
	static Background level;
	
	static int maxScore;

	public Frame() throws FileNotFoundException{
		maxScore=0;
		frame.add(title = new TitleScreen());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 448);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);	
		
		
	}
	
	public static void setScore(int score){
		maxScore = score;
	}
	public static int getScore(){
		return maxScore;
	}

	public static void lvlEnd() throws FileNotFoundException{
		frame.getContentPane().removeAll();
		
		frame.add(title = new TitleScreen());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.repaint();
	}
	
	
	public static void lvlStart(String name) throws FileNotFoundException{
		frame.getContentPane().removeAll();
		
		frame.add(level=new Background(name));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.repaint();
		
	}
	public static void main(String[] args) throws FileNotFoundException{
		
		
		
		Frame game =new Frame();
		
		
	}

	
	

}
