import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class object {
	
	int x;
	int y;
	// location
	
	int w;
	int h;
	// dimensions
	
	double dx;
	double dy;
	// current movement
	
	int health;
	// current health
	
	boolean visible;
	// defines visibility, true = visible, false = not
	
	sprites sp;
	
	BufferedImage currSp;
	// current sprite
	
	public object(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		health = 0;
		visible = true;
	}
	
	public object(int x, int y, int w, int h, BufferedImage currSp, sprites sp) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.currSp = currSp;
		this.sp = sp;
		health = 0;
		visible = true;
	}
	
	public BufferedImage rotateImg(BufferedImage img, double rads) {
		
		double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
		int w = img.getWidth();
		int h = img.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin);
		int newHeight = (int) Math.floor(h * cos + w * sin);
		
		BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotated.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - w) / 2, (newHeight - h) / 2);
		
		int x = w / 2;
		int y = h / 2;
		
		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, null, 0, 0);
		g2d.dispose();
		
		return rotated;
	}
	
	public void draw(Graphics g) {
		if (!visible) {
			return;
		}
		g.drawImage(currSp, x, y, null);
	}
	
	public void move() {
		if (health <= 0 && !(this instanceof attack)) {
			visible = false;
			// if no health, invisible
		} else {
			x = (int) (x + dx);
			y = (int) (y + dy);
		}
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getFutureHitBox() {
		return new Rectangle(x + (int) (dx), y + (int) (dy), w, h);
	}
}
