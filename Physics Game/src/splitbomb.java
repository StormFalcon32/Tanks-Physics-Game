import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class splitbomb extends attack {
	
	ArrayList<attack> splits = new ArrayList<attack>();
	
	double vertexTime;
	// time that projectile reaches vertex
	
	boolean split;
	// has the projectile split yet
	
	public splitbomb(int x, int y, double a, double v, int d, int t, BufferedImage currSp, sprites sp) {
		super(x, y, a, v, d, t, currSp, sp);
		vertexTime = calcVertex();
		// TODO Auto-generated constructor stub
	}
	
	public double calcVertex() {
		return (-vy) / 10;
	}
	
	public void move() {
		if (!split) {
			super.move();
		} else {
			for (int x = 0; x != splits.size(); x++) {
				attack s = splits.get(x);
				if (s.visible) {
					s.move();
				} else {
					splits.remove(x);
					x--;
				}
			}
		}
		if (!split && time > vertexTime) {
			split = true;
			double newV = (5 * vx) / (2 * Math.sqrt(3));
			splits.add(new attack(x, y, 0, vx / 2, getDamage() / 3, type, currSp, sp));
			splits.add(new attack(x, y, 30, newV, getDamage() / 3, type, currSp, sp));
			splits.add(new attack(x, y, 330, newV, getDamage() / 3, type, currSp, sp));
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
