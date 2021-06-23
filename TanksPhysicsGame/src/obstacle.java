import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Arrays;

public class obstacle {
	
	String type;
	// hill, mountain, silohill
	
	int health = 140;
	// if silohill
	
	boolean visible = true;
	// for silohill
	
	int[] xpoints;
	int[] ypoints;
	// coordinates of each point
	
	int startX;
	int width;
	// location and dimensions
	
	Polygon hitbox;
	// hitbox
	
	silo silo;
	
	int[] xToIndex = new int[600];
	// xToIndex[i] is the index of the point who's absolute x coordinate is i
	
	Color c;
	// color
	
	public obstacle(String type, int x, int w, silo s) {
		this.type = type;
		startX = x;
		width = w;
		silo = s;
		generate();
		int num = (int) (Math.random() * 2);
		if (num == 0)
			c = Color.LIGHT_GRAY;
		else
			c = new Color(34, 147, 32);
	}
	
	public void move() {
		if (health <= 0 && type.equals("silohill")) {
			visible = false;
			for (int x = 0; x != xpoints.length; x++) {
				ypoints[x] = 400;
			}
			hitbox = new Polygon(xpoints, ypoints, xpoints.length);
			silo.move();
		}
	}
	
	public void generate() {
		
		if (!type.equals("silohill")) {
			// will have ammo building
			
			int numPoints = (width / 50) + 3;
			// number of points
			
			xpoints = new int[numPoints];
			ypoints = new int[numPoints];
			
			Arrays.fill(xToIndex, -1);
			
			for (int x = 0; x != xpoints.length; x++) {
				if (50 * x + startX == 150 || 50 * x + startX == 450) {
					// need to flatten for ammo building
					xToIndex[50 * x + startX - 10] = x;
					xToIndex[50 * x + startX] = x + 1;
					xToIndex[50 * x + startX + 10] = x + 2;
					xpoints[x] = 50 * x + startX - 10;
					xpoints[x + 1] = 50 * x + startX;
					xpoints[x + 2] = 50 * x + startX + 10;
					ypoints[x] = 400 - (int) (Math.random() * ((type.equals("mountain")) ? 150 : 50));
					ypoints[x + 1] = ypoints[x];
					ypoints[x + 2] = ypoints[x];
					x += 2;
					startX -= 100;
				} else {
					xToIndex[50 * x + startX] = x;
					xpoints[x] = 50 * x + startX;
					ypoints[x] = 400 - (int) (Math.random() * ((type.equals("mountain")) ? 150 : 50));
				}
			}
			
			ypoints[0] = 400;
			ypoints[ypoints.length - 1] = 400;
			// sets the edges of obstacle to be at the bottom
			
			hitbox = new Polygon(xpoints, ypoints, numPoints);
			// creates hitbox
		} else {
			// for a random multiple of 50 greater than 50, create several points
			// the first and last point will always have a height of 0
			
			int numPoints = (width / 50) + 1;
			// number of points
			
			xpoints = new int[numPoints];
			ypoints = new int[numPoints];
			
			Arrays.fill(xToIndex, -1);
			
			for (int x = 0; x != xpoints.length; x++) {
				xToIndex[50 * x + startX] = x;
				xpoints[x] = 50 * x + startX;
				ypoints[x] = 400 - (int) (Math.random() * 50 + 32);
			}
			
			ypoints[0] = 400;
			ypoints[ypoints.length - 1] = 400;
			// sets the edges of obstacle to be at the bottom
			
			hitbox = new Polygon(xpoints, ypoints, numPoints);
			// creates hitbox
		}
	}
	
	public void draw(Graphics g) {
		if (type.equals("silohill")) {
			silo.draw(g);
		}
		if (!visible) {
			return;
		}
		g.setColor(c);
		g.fillPolygon(hitbox);
	}
}
