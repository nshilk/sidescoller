package AlphaGame;
/** 
*   The Main Class for the test game
*   
*   @author Team Two
*/
import javax.swing.JFrame;

public class Frame {
	
	public Frame(){
		JFrame frame = new JFrame("2D Game");
		frame.add(new Background());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 448);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}

	public static void main(String[] args){
		new Frame();
	}
}
