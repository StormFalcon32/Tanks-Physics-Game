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
	
	ArrayList<object> obstacles = new ArrayList<object>();
	// map obstacles
	
	public classic() {
		p1 = new tank(100, 400 - 10, Color.BLUE);
		p1.angle = 75;
		p1.velocity = 75;
		p2 = new tank(490, 400 - 10, Color.RED);
		p2.angle = 105;
		p2.velocity = 75;
		// adds tanks and default settings
		
		obstacles.add(new silo(180));
		obstacles.add(new hill(150));
		obstacles.add(new hill(200));
		obstacles.add(new mountain(280));
		obstacles.add(new building(380));
		obstacles.add(new building(410));
		// adds obstacles
	}
	
	public void move() {
		p1.move();
		p2.move();
		// moves players
		
		for (object o : obstacles)
			o.move();
		// moves obstacles
		
		collision();
		// handles collision
	}
	
	public void draw(Graphics g) {
		
		p1.draw(g);
		p2.draw(g);
		// draws the players
		
		for (object o : obstacles) {
			o.draw(g);
		}
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
		g.drawString("Attack: " + p1.type, 10, 550);
		// player 1 stats
		
		g.drawString("Player 2", 310, 430);
		g.drawString("Health: " + p2.health + " Ammo: " + p2.ammo, 310, 460);
		g.drawString("Launch Angle: " + p2.angle, 310, 490);
		g.drawString("Launch Velocity: " + p2.velocity, 310, 520);
		g.drawString("Attack: " + p2.type, 310, 550);
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
		
		for (object o : obstacles) {
			if (o.dispStats) {
				g.drawString("X: " + o.x + " Y: " + (400 - o.y - o.h), o.x, o.y - 30);
				g.drawString("W: " + o.w + " H: " + o.h, o.x, o.y - 10);
			}
		}
		if (p1.dispStats) {
			g.drawString("X: " + p1.x + " Y: " + (400 - p1.y - p1.h), p1.x, p1.y - 30);
			g.drawString("W: " + p1.w + " H: " + p1.h, p1.x, p1.y - 10);
		}
		if (p2.dispStats) {
			g.drawString("X: " + p2.x + " Y: " + (400 - p2.y - p2.h), p2.x, p2.y - 30);
			g.drawString("W: " + p2.w + " H: " + p2.h, p2.x, p2.y - 10);
		}
		// display coordinates if mouse hovers over anything
	}
	
	public void mouseClick(int x, int y) {
		for (object o : obstacles) {
			if (o.getHitBox().contains(x, y)) {
				System.out.println(o.health);
			}
		}
	}
	
	public void mouseMoved(int x, int y) {
		for (object o : obstacles) {
			if (o.getHitBox().contains(x, y)) {
				o.dispStats = true;
			} else {
				o.dispStats = false;
			}
		}
		if (p1.getHitBox().contains(x, y)) {
			p1.dispStats = true;
		} else {
			p1.dispStats = false;
		}
		if (p2.getHitBox().contains(x, y)) {
			p2.dispStats = true;
		} else {
			p2.dispStats = false;
		}
		// check if mouse is hovering over anything
	}
	
	public void collision() {
		for (attack a : p1.attacks) {
			Rectangle ar = a.getHitBox();
			
			for (object o : obstacles) {
				if (o.visible && ar.intersects(o.getHitBox())) {
					a.visible = false;
					o.health -= a.damage;
					
					if (o instanceof building)
						p1.ammo += ((building) o).ammoBonus;
				}
			}
			// checks obstacle collision
			// attacks disappear and deal damage
			
			if (p2.visible && ar.intersects(p2.getHitBox())) {
				a.visible = false;
				p2.health -= a.damage;
			}
			// checks player 2 collision
			// attacks disappear and deal damage
			
			if (ar.y > 400)
				a.visible = false;
			// if it goes out of bounds, it disappears
		}
		
		// ---- literally the same thing but for player 2 ----//
		
		for (attack a : p2.attacks) {
			Rectangle ar = a.getHitBox();
			
			for (object o : obstacles) {
				if (o.visible && ar.intersects(o.getHitBox())) {
					a.visible = false;
					o.health -= a.damage;
					
					if (o instanceof building)
						p2.ammo += ((building) o).ammoBonus;
				}
			}
			// checks obstacle collision
			// attacks disappear and deal damage
			
			if (p1.visible && ar.intersects(p1.getHitBox())) {
				a.visible = false;
				p1.health -= a.damage;
			}
			// checks player 1 collision
			// attacks disappear and deal damage
			
			if (ar.y > 400)
				a.visible = false;
			// if it goes out of bounds, it disappears
		}
	}
	
}
