import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class splitbomb extends attack {
	
	ArrayList<attack> splits = new ArrayList<attack>();
	
	double vertexTime;
	// time that projectile reaches vertex
	
	boolean split;
	// has the projectile split yet
	
	boolean left;
	// which way is the projectile going
	
	public splitbomb(int x, int y, double a, double v, int d, Color c) {
		super(x, y, a, v, d, c);
		if (aI > Math.PI / 2) {
			left = true;
		} else {
			left = false;
		}
		vertexTime = calcVertex();
		// TODO Auto-generated constructor stub
	}
	
	public double calcVertex() {
		return (-vy) / 10;
	}
	
	public boolean outOfRange() {
		if (split) {
			for (attack a : splits) {
				if (a.y <= 400) {
					return false;
				}
			}
			return true;
		}
		return y > 400;
	}
	
	public void move() {
		if (!split) {
			super.move();
		} else {
			for (attack a : splits) {
				a.move();
			}
		}
		if (!split && time > vertexTime) {
			split = true;
			double newV = (5 * vx) / (2 * Math.sqrt(3));
			splits.add(new attack(x, y, 0, vx / 2, damage, c));
			splits.add(new attack(x, y, 30, newV, damage, c));
			splits.add(new attack(x, y, 330, newV, damage, c));
		}
	}
	
	public void clear() {
		for (int x = 0; x != splits.size(); x++) {
			splits.remove(x);
			x--;
		}
	}
	
	public void draw(Graphics g) {
		if (!split) {
			super.draw(g);
		} else {
			for (attack a : splits) {
				a.draw(g);
			}
		}
	}
}
