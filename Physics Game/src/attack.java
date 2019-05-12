import java.awt.Color;

public class attack extends object {
	
	int sx;
	int sy;
	// starting positions
	
	int vx;
	int vy;
	// components of velocity in degrees
	
	double vxCurr;
	double vyCurr;
	// current velocity
	
	int ax = 0;
	int ay = 10;
	// directional acceleration, default ay 10
	
	int damage = 20;
	// attack damage, default 10
	
	double time = 0;
	// current time in seconds, default 0
	
	int count = 0;
	// counter, every 5 increments = 1 second
	
	public attack(int x, int y, int a, int v, int d, Color c) {
		super(x, y, 4, 4, c);
		sx = x;
		sy = y;
		vx = (int) (v * Math.cos(Math.toRadians(-a)));
		vy = (int) (v * Math.sin(Math.toRadians(-a)));
		vxCurr = vx;
		vyCurr = vy;
		damage = d;
		// instantiates some variables
	}
	
	public void move() {
		
		count++;
		time = (double) (count) / 20;
		// each increment is 1/20th of a second
		vxCurr += ax * time;
		vyCurr += ay * time;
		// updates current velocity
		x = (int) (sx + vx * time + (ax / 2 * time * time));
		y = (int) (sy + vy * time + (ay / 2 * time * time));
		// updates current position according to projectile motion
		
	}
	
}
