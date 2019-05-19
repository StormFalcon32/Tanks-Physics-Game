import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class tank extends object {
	
	int ammo = 50;
	// ammo available, default 50
	
	double angle = 0;
	// launch angle in degrees, default 0
	
	double velocity = 0;
	// launch velocity, default 75
	int type = 0;
	// attack type
	
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	// controls
	
	ArrayList<attack> attacks = new ArrayList<attack>();
	
	int[] xPoints = new int[20];
	int[] yPoints = new int[20];
	// trajectory coordinates
	
	int bx;
	// x offset by 5 (use for bullet calculations)
	
	public tank(int x, int y, BufferedImage currSp, sprites sp) {
		super(x, y, 10, 10, currSp, sp);
		health = 100;
		bx = x + 5;
	}
	
	public void move() {
		for (attack a : attacks)
			a.move();
		// moves all attacks
		
		for (int x = 0; x != attacks.size(); x++) {
			if (!attacks.get(x).visible) {
				attacks.remove(x);
				x--;
			}
		}
		// removes all invisible attacks
		if (up && !down)
			velocity += 0.1;
		if (down && !up)
			velocity -= 0.1;
		if (left && !right)
			angle += 0.1;
		if (right && !left)
			angle -= 0.1;
		velocity = Math.round(velocity * 100.0) / 100.0;
		angle = Math.round(angle * 100.0) / 100.0;
		// adjusts
		if (health <= 0)
			visible = false;
		// if no health, invisible
		genTrajectory();
	}
	
	public void draw(Graphics g) {
		
		for (attack a : attacks) {
			a.draw(g);
		}
		if (!visible) {
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
		attack a = null;
		if (type == 0) {
			a = new attack(bx, y, angle, velocity, 20, type, sp.weapons[0], sp);
			// offset shot so it starts in the middle of the tank
		} else if (type == 1) {
			a = new splitbomb(bx, y, angle, velocity, 180, type, sp.weapons[1], sp);
		}
		attacks.add(a);
		ammo -= 10;
		// adds an attack, lowers ammo
	}
	
	public void switchW() {
		type = (type + 1) % 2;
	}
	
	public void genTrajectory() {
		
		bx = x + 5;
		double vx = velocity * Math.cos(Math.toRadians(-angle));
		double vy = velocity * Math.sin(Math.toRadians(-angle));
		
		for (int z = 0; z != 20; z++) {
			double coordX = bx + vx * z;
			double coordY = y + vy * z + (10 * z * z / 2);
			xPoints[z] = (int) coordX;
			yPoints[z] = (int) coordY;
		}
		// calculates the trajectory according to projectile motion
	}
	
}
