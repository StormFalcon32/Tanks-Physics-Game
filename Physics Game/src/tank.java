import java.awt.Color;
import java.awt.Graphics;

public class tank extends object{
	
	int angle; 
	// launch angle, in degrees
	
	int velocity;
	// launch velocity
	
	String type;
	// attack type
	
	int[] xPoints = new int[30];
	int[] yPoints = new int[30];
	// just for testing!

	public tank(int x, int y, Color c) {
		super(x, y, 10, 5, c);
		health = 100;
		angle = 0;
		velocity = 0;
		type = "Bullet";
	}
	
	public void move() {
		genTrajectory();
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		g.drawPolyline(xPoints, yPoints, 30);
		
	}
	
	public void genTrajectory() {
		
		int vx = (int) (velocity * Math.cos(Math.toRadians(-angle)));
		int vy = (int) (velocity * Math.sin(Math.toRadians(-angle)));
		
		for(int z = 0; z != 30; z++) {
			int coordX = x + vx * z;
			int coordY = y + vy * z + (10 * z * z / 2);
			xPoints[z] = coordX;
			yPoints[z] = coordY;
		}
	}

}
