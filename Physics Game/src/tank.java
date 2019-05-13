import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class tank extends object {
	
	int ammo = 50;
	// ammo available, default 50
	
	int angle = 0;
	// launch angle in degrees, default 0
	
	int velocity = 0;
	// launch velocity, default 75
	
	String type = "Bullet";
	// attack type, default Bullet
	
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	// controls
	
	long lastTime = System.currentTimeMillis();
	// poll controls 50 times every second
	
	ArrayList<attack> attacks = new ArrayList<attack>();
	
	int[] xPoints = new int[20];
	int[] yPoints = new int[20];
	// trajectory coordinates
	
	int bx;
	// x offset by 5 (use for bullet calculations)
	
	public tank(int x, int y, Color c) {
		super(x, y, 10, 10, c);
		health = 100;
		bx = x + 5;
	}
	
	public void move() {
		for (attack a : attacks)
			a.move();
		// moves all attacks
		
		for (int x = 0; x != attacks.size(); x++) {
			if (!attacks.get(x).visible()) {
				attacks.remove(x);
				x--;
			}
		}
		// removes all invisible attacks
		long time = System.currentTimeMillis() - lastTime;
		if (time >= 25) {
			if (up && !down)
				velocity++;
			if (down && !up)
				velocity--;
			if (left && !right)
				angle++;
			if (right && !left)
				angle--;
			lastTime += time;
		}
		// adjusts
		if (health <= 0)
			invisible();
		// if no health, invisible
		genTrajectory();
	}
	
	public void draw(Graphics g) {
		
		for (attack a : attacks) {
			a.draw(g);
		}
		// classic draws attacks and deals with visibility
		if (!visible()) {
			return;
		}
		super.draw(g);
		g.drawPolyline(xPoints, yPoints, 20);
		// draws the trajectory
	}
	
	public void shoot() {
		bx = x + 5;
		if (ammo <= 0)
			return;
		// if low on ammo, don't shoot
		
		attack a = new attack(bx, y, angle, velocity, 20, c);
		// offset shot so it starts in the middle of the tank
		attacks.add(a);
		ammo -= 10;
		// adds an attack, lowers ammo
	}
	
	public void genTrajectory() {
		
		bx = x + 5;
		int vx = (int) (velocity * Math.cos(Math.toRadians(-angle)));
		int vy = (int) (velocity * Math.sin(Math.toRadians(-angle)));
		
		for (int z = 0; z != 20; z++) {
			int coordX = bx + vx * z;
			int coordY = y + vy * z + (10 * z * z / 2);
			xPoints[z] = coordX;
			yPoints[z] = coordY;
		}
		// calculates the trajectory according to projectile motion
	}
	
}
