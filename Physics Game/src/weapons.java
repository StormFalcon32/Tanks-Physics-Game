import java.awt.Color;
import java.awt.Graphics;

public class weapons {
	sprites sp = new sprites();
	// sprites
	
	boolean day;
	// day or night
	
	int sunX;
	int sunY;
	// coordinates of sun/moon
	
	int selection = 0;
	// what is being selected
	
	public weapons(boolean d, int x, int y) {
		day = d;
		sunX = x;
		sunY = y;
	}
	
	public void draw(Graphics g) {
		g.setFont(sp.fonts[3]);
		g.setColor(Color.WHITE);
		g.drawImage((day) ? sp.background[0] : sp.background[2], 0, 0, null);
		g.drawImage((day) ? sp.background[1] : sp.background[3], sunX, sunY + 200, null);
		g.drawString("Weapons", 100, 50);
		g.setFont(sp.fonts[1]);
		g.drawString("Bullet", 125, 200);
		g.drawString("Blue Bird", 125, 250);
		g.drawString("Armor Piercer", 125, 500);
		g.setFont(sp.fonts[0]);
		g.drawString("20 damage", 250, 200);
		g.drawString("180 damage (60 per piece)", 300, 250);
		g.drawString("Splits into 3 at the vertex:", 300, 300);
		g.drawString("+30 degrees 5Vx / (2sqrt3)", 300, 350);
		g.drawString("0 degrees Vx / 2", 300, 400);
		g.drawString("-30 degrees 5Vx / (2sqrt3)", 300, 450);
		g.drawString("Damage is Vf / 2", 375, 500);
	}
}
