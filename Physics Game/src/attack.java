import java.awt.Color;

public class attack extends object {
	
	int sx;
	int sy;
	// starting positions
	
	double vx;
	double vy;
	// components of velocity in degrees
	
	double vI;
	// initial velocity
	
	double ax = 0;
	double ay = 10;
	// directional acceleration, default ay 10
	
	int damage = 20;
	// attack damage, default 10
	
	double time = 0;
	// current time in seconds, default 0
	
	int count = 0;
	// counter, every 5 increments = 1 second
	
	double aI;
	// initial angle
	
	public attack(int x, int y, double a, double v, int d, Color c) {
		super(x, y, 4, 4, c);
		sx = x;
		sy = y;
		aI = Math.toRadians(a);
		vI = v;
		vx = v * Math.cos(-aI);
		vy = v * Math.sin(-aI);
		damage = d;
		// instantiates some variables
	}
	
	public void move() {
		
		count++;
		time = (double) (count) / 20;
		// each increment is 1/20th of a second
		x = (int) (sx + vx * time + (ax / 2 * time * time));
		y = (int) (sy + vy * time + (ay / 2 * time * time));
		// updates current position according to projectile motion
		
	}
	
}
