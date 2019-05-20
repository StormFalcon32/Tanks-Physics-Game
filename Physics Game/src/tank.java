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
	
	int player;
	// player num
	
	BufferedImage barrelSp;
	// barrel sprite
	
	ArrayList<attack> attacks = new ArrayList<attack>();
	
	int[] xPoints = new int[20];
	int[] yPoints = new int[20];
	// trajectory coordinates
	
	int bx;
	// x offset by 5 (use for bullet calculations)
	
	double lastTime = -1;
	// timer for cooldown
	
	public tank(int x, int y, int player, sprites sp) {
		super(x, y, 10, 10, (player == 1) ? sp.tanks[0] : sp.tanks[2], sp);
		this.barrelSp = (player == 1) ? sp.tanks[1] : sp.tanks[3];
		this.player = player;
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
			velocity = Math.min(300, velocity + 0.1);
		if (down && !up)
			velocity = Math.max(0, velocity - 0.1);
		if (left && !right) {
			if (player == 1) {
				angle = Math.min(185, angle + 0.1);
			} else {
				angle = Math.min(185, angle + 0.1);
			}
		}
		if (right && !left) {
			if (player == 1) {
				angle = Math.max(-5, angle - 0.1);
			} else {
				angle = Math.max(-5, angle - 0.1);
			}
		}
		velocity = Math.round(velocity * 100.0) / 100.0;
		angle = Math.round(angle * 100.0) / 100.0;
		// player controls and rounding
		if (health <= 0)
			visible = false;
		// if no health, invisible
		
		genTrajectory();
	}
	
	public void draw(Graphics g) {
		for (attack a : attacks)
			a.draw(g);
		if (!visible) {
			return;
		}
		double rads = Math.toRadians(-angle);
		g.drawImage(rotateImg(barrelSp, rads), (int) (x + 4.5 * Math.cos(rads) + ((player == 1) ? 2 : 3)), (int) (y + 4.5 * Math.sin(rads) + 2), null);
		// draw barrels (don't try and understand it, just accept that it works)
		
		super.draw(g);
		
		g.drawPolyline(xPoints, yPoints, 20);
		// draws the trajectory
	}
	
	public void movePos(int dx) {
		if (System.currentTimeMillis() - lastTime > 1000 || lastTime == -1) {
			x += dx;
			lastTime = System.currentTimeMillis();
		}
	}
	
	public void shoot() {
		bx = x + 5;
		if (ammo <= 0)
			return;
		// if low on ammo, don't shoot
		if (System.currentTimeMillis() - lastTime > 1000 || lastTime == -1) {
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
			lastTime = System.currentTimeMillis();
		}
		// only shoot after cooldown
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
