import java.awt.Color;
import java.awt.Graphics;

public class classic {
	
	tank p1;
	tank p2;
	
	public classic() {
		p1 = new tank(100, 400 - 5, Color.BLUE);
		p2 = new tank(490, 400-5, Color.RED);
	}
	
	public void move() {
		p1.move();
		p2.move();
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(255, 235, 205));
		g.fillRect(0, 400, 600, 200);
		// bottom of the map
		
		g.setColor(Color.BLACK);
		// resets text color
		
		g.drawString("Player 1", 10, 430);
		g.drawString("Health:			" + p1.health, 10, 460);
		g.drawString("Launch Angle:		" + p1.angle, 10, 490);
		g.drawString("Launch Velocity:	" + p1.velocity, 10, 520);
		g.drawString("Attack:			" + p1.type, 10, 550);
		// player 1 stats
		
		g.drawString("Player 2", 310, 430);
		g.drawString("Health:			" + p2.health, 310, 460);
		g.drawString("Launch Angle:		" + p2.angle, 310, 490);
		g.drawString("Launch Velocity:	" + p2.velocity, 310, 520);
		g.drawString("Attack:			" + p2.type, 310, 550);
		// player 2 stats
		
		p1.draw(g);
		p2.draw(g);
		// draws the players
		
	}

}
