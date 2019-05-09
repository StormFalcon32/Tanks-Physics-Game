import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class main extends JPanel implements ActionListener {
	
	/* How does this engine work?
	 * Every couple milliseconds, the entire frame updates- triggering a chain of drawing and moving
	 * Almost everything- like this main class, the components, the objects- has a draw and move method
	 * 
	 * Components are handlers of objects and other variables
	 * They include the title screen, game screen, etc
	 * Each component has a draw and move method and each calls the draw/move method of its objects
	 * 
	 * The game is regulated by the game state, which is stored as a string
	 * Based on what the state currently is...
	 * Certain components are drawn, certain components are moved, certain inputs are accepted
	 */

	//---- INSTANCE VARIABLES ----//
	
	private static final long serialVersionUID = 7278894226467689035L;
	private Timer timer;
	// timer that triggers update at specific intervals
	private final int DELAY = 10;
	// specific interval is every 10 milliseconds
	
	String state;
	// game state
	
	
	// game components
	
	//---- CONSTRUCTOR ----//
	
	public main() {
	// instantiates the game panel	
		
		addKeyListener(new KAdapter());
		addMouseListener(new MAdapter());
		// adds key input
		setFocusable(true);
		setBackground(Color.WHITE);
		// sets background color to gray
		setFont(new Font("Arial", Font.PLAIN, 16));
		// changes font (for reference, the default font is Lucida Console Plain 14)
		timer = new Timer(DELAY, this);
		timer.start();      
		// creates and starts the timer
		
		state = "";
		// default game state
	}
	
	//---- DRAWING ----//

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		// draws the game
		Toolkit.getDefaultToolkit().sync();
	}

	private void draw(Graphics g) {
	/* draws the game every step
	 * draws components based on the game state (variable "state")
	 */
		
		g.setColor(Color.BLACK);
		// default color for text
		

	}
	
	//---- MOVEMENT ----//

	public void actionPerformed(ActionEvent e) {	
		move();
		repaint();
	}
	
	public void move() {
	/* moves the game every step
	 * moves components based on the game state (variable "state")
	 */
		
	}
	
	//---- KEY AND MOUSE INPUT ----//

	private class KAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
		}

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
		}
	}
	
	private class MAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {	
			
			int mx = e.getX();
			int my = e.getY();
			// mouse coordinates
			
			Rectangle mr = new Rectangle(mx, my, 1, 1);
			// mouse hitbox
			
		}
		
		public void mouseReleased(MouseEvent e) {
		
			int mx = e.getX();
			int my = e.getY();
			// mouse coordinates
			
			Rectangle mr = new Rectangle(mx, my, 1, 1);
			// mouse hitbox
			
		}
	}

}