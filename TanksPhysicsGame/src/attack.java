import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class attack extends object {
	
	int sx;
	int sy;
	// starting positions
	
	double vx;
	double vy;
	// components of velocity in degrees
	
	double currVx;
	double currVy;
	// current velocity
	
	int type;
	// Bullet, splitbomb, armor pierce, laser
	
	double vI;
	// initial velocity
	
	double ax = 0;
	double ay = 10;
	// directional acceleration, default ay 10
	
	private int damage = 20;
	// attack damage, default 20
	
	double time = 0;
	// current time in seconds, default 0
	
	int count = 0;
	// counter, every 5 increments = 1 second
	
	double aI;
	// initial angle
	
	double currA;
	// current angle
	
	public attack(int x, int y, double a, double v, int d, int t, BufferedImage currSp, sprites sp) {
		super(x, y, currSp.getWidth(), currSp.getHeight(), currSp, sp);
		sx = x;
		sy = y;
		aI = Math.toRadians(a);
		currA = aI;
		vI = v;
		vx = v * Math.cos(aI);
		vy = v * Math.sin(-aI);
		currVx = vx;
		currVy = vy;
		damage = d;
		type = t;
		// instantiates some variables
	}
	
	public void draw(Graphics g) {
		if (!visible || count < 5) {
			return;
		}
		if (type == 1) {
			g.drawImage(currSp, x - (w / 2), y - (h / 2), null);
		} else {
			g.drawImage(rotateImg(currSp, -currA), x - (w / 2), y - (h / 2), null);
		}
	}
	
	public void move() {
		
		count++;
		time = (double) (count) / 20;
		// each increment is 1/20th of a second
		currVx = vx + ax * time;
		currVy = vy + ay * time;
		currA = Math.atan(-currVy / currVx) + ((currVx > 0) ? 0 : Math.PI);
		x = (int) (sx + vx * time + (ax / 2 * time * time));
		y = (int) (sy + vy * time + (ay / 2 * time * time));
		// updates current position according to projectile motion
	}
	
	public int getDamage() {
		if (!visible)
			return 0;
		if (type == 2) {
			int damage = (int) (Math.sqrt(currVx * currVx + currVy * currVy) / 2);
			return damage;
		}
		return damage;
	}
}
