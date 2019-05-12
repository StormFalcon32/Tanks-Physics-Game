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
	
	int frame = 0;
	// don't poll controls every frame
	
	ArrayList<attack> attacks = new ArrayList<attack>();
	
	int[] xPoints = new int[20];
	int[] yPoints = new int[20];
	// trajectory coordinates
	
	public tank(int x, int y, Color c) {
		super(x, y, 10, 5, c);
		health = 100;
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
		
		if (frame % 2 == 0) {
			if (up && !down)
				velocity++;
			if (down && !up)
				velocity--;
			if (left && !right)
				angle++;
			if (right && !left)
				angle--;
			frame = 1;
		} else {
			frame = 0;
		}
		// adjusts
		if (health <= 0)
			visible = false;
		// if no health, invisible
		genTrajectory();
	}
	
	public void draw(Graphics g) {
		if (!visible)
			return;
		// if invisible, don't draw
		
		x -= 5;
		super.draw(g);
		x += 5;
		// offsets the tank so it shoots from the middle
		
		for (attack a : attacks)
			a.draw(g);
		// draws all attacks
		
		g.drawPolyline(xPoints, yPoints, 20);
		// draws the trajectory
	}
	
	public void shoot() {
		if (ammo <= 0)
			return;
		// if low on ammo, don't shoot
		
		attack a = new attack(x, y, angle, velocity, 20, c);
		attacks.add(a);
		ammo -= 10;
		// adds an attack, lowers ammo
	}
	
	public void genTrajectory() {
		
		int vx = (int) (velocity * Math.cos(Math.toRadians(-angle)));
		int vy = (int) (velocity * Math.sin(Math.toRadians(-angle)));
		
		for (int z = 0; z != 20; z++) {
			int coordX = x + vx * z;
			int coordY = y + vy * z + (10 * z * z / 2);
			xPoints[z] = coordX;
			yPoints[z] = coordY;
		}
		// calculates the trajectory according to projectile motion
	}
	
}
