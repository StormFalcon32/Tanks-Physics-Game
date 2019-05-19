import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Arrays;

public class obstacle {
	
	String type;
	// hill, mountain, silohill
	
	int health = 80;
	
	int[] xpoints;
	int[] ypoints;
	// coordinates of each point
	
	int startX;
	int width;
	// location and dimensions
	
	Polygon hitbox;
	// hitbox
	
	line[] lines;
	// lines (easy calculation for tank movement)
	// not in use for now (tanks just teleport instead of move)
	
	int[] xToIndex = new int[600];
	// xToIndex[i] is the index of the point who's absolute x coordinate is i
	
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
		int numLines = width / 50;
		// number of line segments
		
		lines = new line[numLines];
		
		xpoints = new int[numPoints];
		ypoints = new int[numPoints];
		
		Arrays.fill(xToIndex, -1);
		
		for (int x = 0; x != xpoints.length; x++) {
			xToIndex[50 * x + startX] = x;
			xpoints[x] = 50 * x + startX;
			ypoints[x] = 400 - (int) (Math.random() * ((type.equals("mountain")) ? 150 : 50));
		}
		
		ypoints[0] = 400;
		ypoints[ypoints.length - 1] = 400;
		// sets the edges of obstacle to be at the bottom
		
		for (int x = 1; x != xpoints.length; x++) {
			double slope = (ypoints[x] - ypoints[x - 1]) / (xpoints[x] - xpoints[x - 1]);
			lines[x - 1] = new line(slope, xpoints[x - 1]);
		}
		// calculate the lines
		
		hitbox = new Polygon(xpoints, ypoints, numPoints);
		// creates hitbox
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillPolygon(hitbox);
	}
}

class line {
	double slope;
	int x1;
	
	public line(double s, int x) {
		slope = s;
		x1 = x;
	}
	
	public int calc(int x) {
		return (int) (slope * (x - x1));
	}
}
