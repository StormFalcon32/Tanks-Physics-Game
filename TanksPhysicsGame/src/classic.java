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
	
	String[] types = { "Bullet [10]", "Blue Bird [30]", "Armor Piercer [40]", "Orbital Strike [ALL]" };
	// weapon names
	
	ArrayList<obstacle> obstacles = new ArrayList<obstacle>();
	// map obstacles
	
	ArrayList<building> buildings = new ArrayList<building>();
	// map buildings
	
	// ---- TEST ----//
	obstacle o;
	
	int mx = 0;
	int my = 0;
	// previous mouse position
	
	sprites sp = new sprites();
	// sprites
	
	boolean day;
	// day or night
	
	int gameState = 0;
	// is the game finished
	
	int[] yPoints = { 250, 200, 170, 155, 150, 155, 170, 200, 250 };
	int[] xPoints = { 100, 150, 200, 250, 300, 350, 400, 450, 500 };
	// trajectory of sun/moon
	
	int sunX;
	int sunY;
	// coordinates of sun/moon
	
	int count = 0;
	// starting animation
	
	public classic() {
		p1 = new tank(45, 400 - 10, 1, sp);
		p1.angle = 75;
		p1.velocity = 75;
		p2 = new tank(545, 400 - 10, 2, sp);
		p2.angle = 105;
		p2.velocity = 75;
		// adds tanks and default settings
		
		int randomWeather = (int) (Math.random() * 9);
		if (randomWeather > 4) {
			day = true;
		} else {
			day = false;
		}
		sunX = xPoints[randomWeather];
		sunY = yPoints[randomWeather];
		// determine weather randomly
		
		int random = ((int) (Math.random() * 2)) + 4;
		obstacles.add(new obstacle("silohill", random * 50, 150, new silo(random * 50 + 75 - 8, 400 - 32, sp.buildings[1], sp)));
		obstacles.add(new obstacle("hill", 100, random * 50 - 100, null));
		obstacles.add(new obstacle("mountain", random * 50 + 150, 500 - (random * 50 + 150), null));
		// adds obstacles randomly
		
		buildings.add(new building(140, obstacles.get(1).ypoints[obstacles.get(1).xToIndex[150]] - 10, sp.buildings[0], sp));
		buildings.add(new building(440, obstacles.get(2).ypoints[obstacles.get(2).xToIndex[450]] - 10, sp.buildings[0], sp));
		// add ammo buildings
	}
	
	public void move() {
		p1.move();
		p2.move();
		// moves players
		for (obstacle o : obstacles) {
			if (o.xToIndex[p1.x + 5] != -1) {
				p1.y = o.ypoints[o.xToIndex[p1.x + 5]] - 10;
			}
			
			if (o.xToIndex[p2.x + 5] != -1) {
				p2.y = o.ypoints[o.xToIndex[p2.x + 5]] - 10;
			}
		}
		
		for (obstacle o : obstacles) {
			o.move();
		}
		// moves obstacles
		for (building b : buildings)
			b.move(p1.x, p2.x);
		// moves buildings
		collision();
		// handles collision
	}
	
	public void draw(Graphics g) {
		if (count <= 60) {
			g.translate(0, (60 - count) * 4);
			count++;
		}
		if ((p1.laser != null && p1.laser.visible) || (p2.laser != null && p2.laser.visible)) {
			g.translate(0, (int) (Math.random() * 10));
		}
		g.setFont(sp.fonts[0]);
		g.drawImage((day) ? sp.background[0] : sp.background[2], 0, -400, null);
		g.drawImage((day) ? sp.background[1] : sp.background[3], sunX, sunY, null);
		p1.draw(g);
		p2.draw(g);
		// draws the players
		
		for (obstacle o : obstacles)
			o.draw(g);
		// draws the obstacles
		
		for (building b : buildings)
			b.draw(g);
		// draws the buildings
		
		if (!p1.visible && (gameState == 0 || gameState == 1)) {
			g.drawImage(sp.victories[1], 0, 50, null);
			gameState = 1;
			g.setColor(Color.WHITE);
			g.drawString("Press Esc", 250, 300);
		}
		if (!p2.visible && (gameState == 0 || gameState == 2)) {
			g.drawImage(sp.victories[0], 0, 50, null);
			gameState = 2;
			g.setColor(Color.WHITE);
			g.drawString("Press Esc", 250, 300);
		}
		// win screen
		
		g.setColor(new Color(255, 235, 205));
		g.fillRect(0, 400, 600, 200);
		// bottom of the map
		
		if (count > 60) {
			
			g.setColor(Color.BLACK);
			// resets text color
			
			g.drawString("Cooldown: " + ((p1.lastTime == -1) ? 0 : Math.max(0, Math.round((p1.cooldownTime + p1.lastTime - System.currentTimeMillis()) / 1000))), 10, 430);
			g.drawString("Regen: " + ((p1.ammoTime == -1) ? 0 : Math.max(0, Math.round((p1.regenTime + p1.ammoTime - System.currentTimeMillis()) / 1000))), 150, 430);
			g.drawString("Health: " + p1.health, 10, 460);
			g.drawString(" Ammo: " + p1.ammo, 120, 460);
			g.drawString("Angle: " + p1.angle, 10, 490);
			g.drawString("Velocity: " + p1.velocity, 10, 520);
			g.drawString("Weapon: " + types[p1.type], 10, 550);
			// player 1 stats
			
			g.drawString("Cooldown: " + ((p2.lastTime == -1) ? 0 : Math.max(0, Math.round((p2.cooldownTime + p2.lastTime - System.currentTimeMillis()) / 1000))), 310, 430);
			g.drawString("Regen: " + ((p2.ammoTime == -1) ? 0 : Math.max(0, Math.round((p2.regenTime + p2.ammoTime - System.currentTimeMillis()) / 1000))), 450, 430);
			g.drawString("Health: " + p2.health, 310, 460);
			g.drawString(" Ammo: " + p2.ammo, 420, 460);
			g.drawString("Angle: " + p2.angle, 310, 490);
			g.drawString("Velocity: " + p2.velocity, 310, 520);
			g.drawString("Weapon: " + types[p2.type], 310, 550);
			// player 2 stats
			
			// g.drawString("Alter angle: A/D or Left/Right", 50, 50);
			// g.drawString("Alter velocity: W/S or Up/Down", 50, 80);
			// g.drawString("Fire: E or Space", 50, 110);
			// g.drawString("Title: R", 50, 140);
			//
			// g.drawString("Welcome to the demo build", 300, 50);
			// g.drawString("Hills and buildings can be destroyed", 300, 80);
			// g.drawString("Buildings reload very quickly here", 300, 110);
			// g.drawString("Destroy the hills for a surprise", 300, 140);
			// information
			if (p1.laser != null && p1.laser.visible) {
				p1.laser.draw(g);
			} else if (p1.laser != null) {
				p1.laser = null;
			}
			if (p2.laser != null && p2.laser.visible) {
				p2.laser.draw(g);
			} else if (p2.laser != null) {
				p2.laser = null;
			}
			drawStats(g);
		}
	}
	
	public void drawStats(Graphics g) {
		
		g.setColor(Color.WHITE);
		
		if (p1.getHitBox().contains(mx, my) && p1.visible) {
			g.drawString("X: " + (p1.x + 5), p1.x - 20, p1.y - 30);
			g.drawString("Y: " + (400 - p1.y - p1.h), p1.x - 20, p1.y - 10);
		}
		if (p2.getHitBox().contains(mx, my) && p2.visible) {
			g.drawString("X: " + (p2.x + 5), p2.x - 20, p2.y - 30);
			g.drawString("Y: " + (400 - p2.y - p2.h), p2.x - 20, p2.y - 10);
		}
		obstacle o = obstacles.get(0);
		silo s = o.silo;
		if (!o.visible && s.getHitBox().contains(mx, my) && s.visible) {
			g.drawString("X: " + (s.x + 8), s.x - 20, s.y - 30);
			g.drawString("Y: " + (400 - s.y - s.h), s.x - 20, s.y - 10);
		}
		
		for (building b : buildings) {
			if (b.getHitBox().contains(mx, my) && b.visible) {
				g.drawString("X: " + (b.x + 10), b.x - 20, b.y - 30);
				g.drawString("Y: " + (400 - b.y - b.h), b.x - 20, b.y - 10);
			}
		}
		// check if mouse is hovering over anything and display coordinates
	}
	
	public void mouseMoved(int x, int y) {
		mx = x;
		my = y;
	}
	
	public void moveTank(int p, boolean left) {
		if (p == 1) {
			if (left) {
				boolean good = true;
				if (p1.x - 50 <= -5) {
					good = false;
				}
				for (building b : buildings) {
					if (p1.x - 50 == b.x + 5 && b.visible) {
						good = false;
					}
				}
				if (good) {
					p1.movePos(-50);
				}
			} else {
				boolean good = true;
				if (p1.x + 50 >= p2.x) {
					good = false;
				}
				for (building b : buildings) {
					if (p1.x + 50 == b.x + 5 && b.visible) {
						good = false;
					}
				}
				if (good) {
					p1.movePos(50);
				}
			}
		} else {
			if (left) {
				boolean good = true;
				if (p2.x - 50 <= p1.x) {
					good = false;
				}
				for (building b : buildings) {
					if (p2.x - 50 == b.x + 5 && b.visible) {
						good = false;
					}
				}
				if (good) {
					p2.movePos(-50);
				}
			} else {
				boolean good = true;
				if (p2.x + 50 >= 595) {
					good = false;
				}
				for (building b : buildings) {
					if (p2.x + 50 == b.x + 5 && b.visible) {
						good = false;
					}
				}
				if (good) {
					p2.movePos(50);
				}
			}
		}
	}
	
	public void collision() {
		for (attack a : p1.attacks) {
			if (a instanceof splitbomb && ((splitbomb) a).split) {
				for (attack s : ((splitbomb) a).splits) {
					Rectangle ar = s.getHitBox();
					
					for (obstacle o : obstacles) {
						if (o.hitbox.intersects(ar) && o.visible && s.count > 5) {
							o.health -= s.getDamage();
							o.health = Math.max(0, o.health);
							s.visible = false;
						}
						silo silo = o.silo;
						if (!o.visible && silo != null && s.visible && silo.getHitBox().intersects(ar)) {
							silo.health -= s.getDamage();
							silo.health = Math.max(0, silo.health);
							if (silo.health == 0) {
								p1.hitSilo = true;
							}
							s.visible = false;
						}
					}
					for (building b : buildings) {
						if (b.getHitBox().intersects(ar) && b.visible) {
							s.visible = false;
							b.health = 0;
							p1.ammo += b.ammoBonus;
						}
					}
					// checks obstacle collision
					// attacks disappear and deal damage
					
					if (p2.visible && ar.intersects(p2.getHitBox())) {
						p2.health -= s.getDamage();
						p2.health = Math.max(0, p2.health);
						if (p2.health == 0) {
							p2.ammo = 0;
						}
						s.visible = false;
					}
					if (p1.visible && ar.intersects(p1.getHitBox()) && s.count > 5) {
						p1.health -= s.getDamage();
						p1.health = Math.max(0, p1.health);
						if (p1.health == 0) {
							p1.ammo = 0;
						}
						s.visible = false;
					}
					// checks player 2 collision
					// attacks disappear and deal damage
					
					if (ar.y > 400) {
						s.visible = false;
					}
					// if it goes out of bounds, it disappears
				}
			} else {
				Rectangle ar = a.getHitBox();
				
				for (obstacle o : obstacles) {
					if (o.hitbox.intersects(ar) && o.visible && a.count > 5) {
						o.health -= a.getDamage();
						o.health = Math.max(0, o.health);
						a.visible = false;
					}
					silo silo = o.silo;
					if (!o.visible && silo != null && a.visible && silo.getHitBox().intersects(ar)) {
						silo.health -= a.getDamage();
						silo.health = Math.max(0, silo.health);
						if (silo.health == 0) {
							p1.hitSilo = true;
						}
						a.visible = false;
					}
				}
				for (building b : buildings) {
					if (b.getHitBox().intersects(ar) && b.visible) {
						b.health = 0;
						p1.ammo += b.ammoBonus;
						a.visible = false;
					}
				}
				// checks obstacle collision
				// attacks disappear and deal damage
				
				if (p2.visible && ar.intersects(p2.getHitBox())) {
					p2.health -= a.getDamage();
					p2.health = Math.max(0, p2.health);
					if (p2.health == 0) {
						p2.ammo = 0;
					}
					a.visible = false;
				}
				if (p1.visible && ar.intersects(p1.getHitBox()) && a.count > 5) {
					p1.health -= a.getDamage();
					p1.health = Math.max(0, p1.health);
					if (p1.health == 0) {
						p1.ammo = 0;
					}
					a.visible = false;
				}
				// checks player 2 collision
				// attacks disappear and deal damage
				
				if (ar.y > 400) {
					a.visible = false;
				}
				// if it goes out of bounds, it disappears
			}
		}
		
		// ---- literally the same thing but for player 2 ----//
		
		for (attack a : p2.attacks) {
			if (a instanceof splitbomb && ((splitbomb) a).split) {
				for (attack s : ((splitbomb) a).splits) {
					Rectangle ar = s.getHitBox();
					
					for (obstacle o : obstacles) {
						if (o.hitbox.intersects(ar) && o.visible && s.count > 5) {
							o.health -= s.getDamage();
							o.health = Math.max(0, o.health);
							s.visible = false;
						}
						silo silo = o.silo;
						if (!o.visible && silo != null && s.visible && silo.getHitBox().intersects(ar)) {
							silo.health -= s.getDamage();
							silo.health = Math.max(0, silo.health);
							if (silo.health == 0) {
								p2.hitSilo = true;
							}
							s.visible = false;
						}
					}
					for (building b : buildings) {
						if (b.getHitBox().intersects(ar) && b.visible) {
							b.health = 0;
							p2.ammo += b.ammoBonus;
							s.visible = false;
						}
					}
					// checks obstacle collision
					// attacks disappear and deal damage
					
					if (p1.visible && ar.intersects(p1.getHitBox())) {
						p1.health -= s.getDamage();
						p1.health = Math.max(0, p1.health);
						if (p1.health == 0) {
							p1.ammo = 0;
						}
						s.visible = false;
					}
					if (p2.visible && ar.intersects(p2.getHitBox()) && s.count > 5) {
						p2.health -= s.getDamage();
						p2.health = Math.max(0, p2.health);
						if (p2.health == 0) {
							p2.ammo = 0;
						}
						s.visible = false;
					}
					// checks player 1 collision
					// attacks disappear and deal damage
					
					if (ar.y > 400) {
						s.visible = false;
					}
					// if it goes out of bounds, it disappears
				}
			} else {
				Rectangle ar = a.getHitBox();
				
				for (obstacle o : obstacles) {
					if (o.hitbox.intersects(ar) && o.visible && a.count > 5) {
						o.health -= a.getDamage();
						o.health = Math.max(0, o.health);
						a.visible = false;
					}
					silo silo = o.silo;
					if (!o.visible && silo != null && a.visible && silo.getHitBox().intersects(ar)) {
						silo.health -= a.getDamage();
						silo.health = Math.max(0, silo.health);
						if (silo.health == 0) {
							p2.hitSilo = true;
						}
						a.visible = false;
					}
				}
				for (building b : buildings) {
					if (b.getHitBox().intersects(ar) && b.visible) {
						a.visible = false;
						b.health = 0;
						p2.ammo += b.ammoBonus;
					}
				}
				// checks obstacle collision
				// attacks disappear and deal damage
				
				if (p1.visible && ar.intersects(p1.getHitBox())) {
					p1.health -= a.getDamage();
					p1.health = Math.max(0, p1.health);
					if (p1.health == 0) {
						p1.ammo = 0;
					}
					a.visible = false;
				}
				if (p2.visible && ar.intersects(p2.getHitBox()) && a.count > 5) {
					p2.health -= a.getDamage();
					p2.health = Math.max(0, p2.health);
					if (p2.health == 0) {
						p2.ammo = 0;
					}
					a.visible = false;
				}
				// checks player 1 collision
				// attacks disappear and deal damage
				
				if (ar.y > 400) {
					a.visible = false;
				}
				// if it goes out of bounds, it disappears
			}
		}
		if (p1.laser != null && p1.laser.visible) {
			if (p1.getHitBox().intersects(p1.laser.getHitBox())) {
				p1.health = 0;
			}
			if (p2.getHitBox().intersects(p1.laser.getHitBox())) {
				p2.health = 0;
			}
		}
		if (p2.laser != null && p2.laser.visible) {
			if (p1.getHitBox().intersects(p2.laser.getHitBox())) {
				p1.health = 0;
			}
			if (p2.getHitBox().intersects(p2.laser.getHitBox())) {
				p2.health = 0;
			}
		}
	}
	
	public void changeLoad() {
		if (p1.cooldownTime == 15000) {
			p1.regenTime = 10000;
			p1.cooldownTime = 3000;
			p2.regenTime = 10000;
			p2.cooldownTime = 3000;
		} else {
			p1.regenTime = 20000;
			p1.cooldownTime = 15000;
			p2.regenTime = 20000;
			p2.cooldownTime = 15000;
		}
	}
	
	public void changeTrajectory() {
		p1.viewTrajectory = !p1.viewTrajectory;
		p2.viewTrajectory = !p2.viewTrajectory;
	}
}
