import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class classic {
	
	/*
	 * normal mode
	 */
	
	tank p1;
	tank p2;
	// player tanks
	
	String[] types = { "Bullet", "Blue Bird" };
	// weapon names
	
	ArrayList<obstacle> obstacles = new ArrayList<obstacle>();
	// map obstacles
	
	// ---- TEST ----//
	obstacle o;
	
	int mx = 0;
	int my = 0;
	// previous mouse position
	
	sprites sp = new sprites();
	// sprites
	
	public classic() {
		p1 = new tank(50, 400 - 10, 1, sp);
		p1.angle = 75;
		p1.velocity = 75;
		p2 = new tank(550, 400 - 10, 2, sp);
		p2.angle = 105;
		p2.velocity = 75;
		// adds tanks and default settings
		
		obstacles.add(new obstacle("hill", 100, 200));
		obstacles.add(new obstacle("hill", 300, 200));
		// adds obstacles
	}
	
	public void move() {
		p1.move();
		p2.move();
		// moves players
		
		collision();
		// handles collision
	}
	
	public void draw(Graphics g) {
		
		p1.draw(g);
		p2.draw(g);
		// draws the players and their attacks
		// if attack was shot before player died, don't remove it yet
		
		for (obstacle o : obstacles)
			o.draw(g);
		// draws the obstacles
		
		g.setColor(new Color(255, 235, 205));
		g.fillRect(0, 400, 600, 200);
		// bottom of the map
		
		g.setColor(Color.BLACK);
		// resets text color
		
		g.drawString("Player 1", 10, 430);
		g.drawString("Health: " + p1.health + " Ammo: " + p1.ammo, 10, 460);
		g.drawString("Launch Angle: " + p1.angle, 10, 490);
		g.drawString("Launch Velocity: " + p1.velocity, 10, 520);
		g.drawString("Attack: " + types[p1.type], 10, 550);
		// player 1 stats
		
		g.drawString("Player 2", 310, 430);
		g.drawString("Health: " + p2.health + " Ammo: " + p2.ammo, 310, 460);
		g.drawString("Launch Angle: " + p2.angle, 310, 490);
		g.drawString("Launch Velocity: " + p2.velocity, 310, 520);
		g.drawString("Attack: " + types[p2.type], 310, 550);
		// player 2 stats
		
		g.drawString("Alter angle: A/D or Left/Right", 50, 50);
		g.drawString("Alter velocity: W/S or Up/Down", 50, 80);
		g.drawString("Fire: E or Space", 50, 110);
		g.drawString("Title: R", 50, 140);
		
		g.drawString("Welcome to the demo build", 300, 50);
		g.drawString("Hills and buildings can be destroyed", 300, 80);
		g.drawString("Buildings reload very quickly here", 300, 110);
		g.drawString("Destroy the hills for a surprise", 300, 140);
		// information
		
		drawStats(g);
	}
	
	public void drawStats(Graphics g) {
		
		if (p1.getHitBox().contains(mx, my) && p1.visible) {
			g.drawString("X: " + (p1.x + 5), p1.x - 10, p1.y - 30);
			g.drawString("Y: " + (400 - p1.y - p1.h), p1.x - 10, p1.y - 10);
		}
		if (p2.getHitBox().contains(mx, my) && p2.visible) {
			g.drawString("X: " + (p2.x + 5), p2.x - 10, p2.y - 30);
			g.drawString("Y: " + (400 - p2.y - p2.h), p2.x - 10, p2.y - 10);
		}
		// check if mouse is hovering over anything and display coordinates
	}
	
	public void mouseMoved(int x, int y) {
		mx = x;
		my = y;
	}
	
	public void mouseClick() {
		
	}
	
	public void collision() {
		for (attack a : p1.attacks) {
			if (a instanceof splitbomb) {
				for (attack s : ((splitbomb) a).splits) {
					Rectangle ar = s.getHitBox();
					
					for (obstacle o : obstacles) {
						if (o.hitbox.intersects(ar)) {
							s.visible = false;
						}
					}
					// checks obstacle collision
					// attacks disappear and deal damage
					
					if (p2.visible && ar.intersects(p2.getHitBox())) {
						s.visible = false;
						p2.health -= s.damage;
						if (p2.health == 0) {
							p2.ammo = 0;
						}
					}
					// checks player 2 collision
					// attacks disappear and deal damage
					
					if (ar.y > 400)
						s.visible = false;
					// if it goes out of bounds, it disappears
				}
			} else {
				Rectangle ar = a.getHitBox();
				
				for (obstacle o : obstacles) {
					if (o.hitbox.intersects(ar)) {
						a.visible = false;
					}
				}
				// checks obstacle collision
				// attacks disappear and deal damage
				
				if (p2.visible && ar.intersects(p2.getHitBox())) {
					a.visible = false;
					p2.health -= a.damage;
					if (p2.health == 0) {
						p2.ammo = 0;
					}
				}
				// checks player 2 collision
				// attacks disappear and deal damage
				
				if (ar.y > 400)
					a.visible = false;
				// if it goes out of bounds, it disappears
			}
		}
		
		// ---- literally the same thing but for player 2 ----//
		
		for (attack a : p2.attacks) {
			if (a instanceof splitbomb) {
				for (attack s : ((splitbomb) a).splits) {
					Rectangle ar = s.getHitBox();
					
					for (obstacle o : obstacles) {
						if (o.hitbox.intersects(ar)) {
							s.visible = false;
						}
					}
					// checks obstacle collision
					// attacks disappear and deal damage
					
					if (p1.visible && ar.intersects(p1.getHitBox())) {
						s.visible = false;
						p1.health -= s.damage;
						if (p1.health == 0) {
							p1.ammo = 0;
						}
					}
					// checks player 1 collision
					// attacks disappear and deal damage
					
					if (ar.y > 400)
						s.visible = false;
					// if it goes out of bounds, it disappears
				}
			} else {
				Rectangle ar = a.getHitBox();
				
				for (obstacle o : obstacles) {
					if (o.hitbox.intersects(ar)) {
						a.visible = false;
					}
				}
				// checks obstacle collision
				// attacks disappear and deal damage
				
				if (p1.visible && ar.intersects(p1.getHitBox())) {
					a.visible = false;
					p1.health -= a.damage;
					if (p1.health == 0) {
						p1.ammo = 0;
					}
				}
				// checks player 1 collision
				// attacks disappear and deal damage
				
				if (ar.y > 400)
					a.visible = false;
				// if it goes out of bounds, it disappears
			}
		}
	}
}
