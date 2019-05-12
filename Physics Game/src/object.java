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
	
	int health;
	// current health
	
	boolean visible;
	// defines visibility, true = visible, false = not
	
	Color c;
	// color (not part of final)
	
	public object(int x, int y, int w, int h) {
		this.x = x; this.y = y;
		this.w = w; this.h = h;
		health = 0;
		visible = true;
	}
	
	public object(int x, int y, int w, int h, Color c) {
		this.x = x; this.y = y;
		this.w = w; this.h = h;
		this.c = c;
		health = 0;
		visible = true;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	public void draw(Graphics g) {
		if(!visible) return;
		g.setColor(c);
		g.fillRect(x, y, w, h);
	}
	
	public void move() {
		if(health <= 0) 
			visible = false;
		// if no health, invisible
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getFutureHitBox() {
		return new Rectangle(x + dx, y + dy, w, h);
	}
}