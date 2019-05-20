import java.awt.Graphics;
import java.awt.Rectangle;

public class laser {
	int x;
	sprites sp;
	int count = 0;
	boolean visible = true;
	int xOffset = 0;

	public laser(int x, sprites sp) {
		this.x = x;
		this.sp = sp;
	}

	public void draw(Graphics g) {
		xOffset = 5 - (int) (Math.random() * 10);
		g.translate(xOffset, 0);
		for (int i = 0; i <= Math.min(count, 40); i++) {
			g.drawImage(sp.laser, x - 50, (i - 1) * 10, null);
		}
		if (count > 100) {
			visible = false;
			g.translate(0, 0);
		}
		count++;
	}

	public Rectangle getHitBox() {
		return new Rectangle(x - 5, 0, 110, Math.min(count + 1, 40) * 10);
	}
}
