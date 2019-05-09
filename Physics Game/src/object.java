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
	
	Color c;
	// color (not part of final)
	
	public object(int x, int y, int w, int h) {
		this.x = x; this.y = y;
		this.w = w; this.h = h;
	}
	
	public object(int x, int y, int w, int h, Color c) {
		this.x = x; this.y = y;
		this.w = w; this.h = h;
		this.c = c;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
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