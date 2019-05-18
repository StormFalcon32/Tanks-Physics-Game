import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class obstacle {
	
	String type;
	// mountain, hill, silohill
	
	int[] xpoints;
	int[] ypoints;
	// coordinates of each point
	
	int startX;
	int width;
	// location and dimensions
	
	Polygon hitbox;
	// hitbox
	
	public obstacle(String type, int x, int w) {
		this.type = type;
		startX = x;
		width = w;
		generate();
	}
	
	public void generate() {
		// for a random multiple of 50 greater than 50, create several points
		// the first and last point will always have a height of 0
		
		int numPoints = (width / 50) + 1;
		// number of points
		
		xpoints = new int[numPoints];
		ypoints = new int[numPoints];
		
		for (int x = 0; x != xpoints.length; x++) {
			xpoints[x] = 50 * x + startX;
			ypoints[x] = 400 - (int) (Math.random() * 175);
		}
		
		ypoints[0] = 400;
		ypoints[ypoints.length - 1] = 400;
		// sets the edges of obstacle to be at the bottom
		
		hitbox = new Polygon(xpoints, ypoints, numPoints);
		// creates hitbox
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillPolygon(hitbox);
	}
}
