import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class object {
	
	int x; int y; 
	// location
	
	int w; int h; 
	// dimensions
	
	int dx; int dy; 
	// current movement
	
	public object(int x, int y, int w, int h) {
		this.x = x; this.y = y;
		this.w = w; this.h = h;
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void move() {
		x += dx; y += dy;
		// moves according to the current movement
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getFutureHitBox() {
		return new Rectangle(x + dx, y + dy, w, h);
	}
}