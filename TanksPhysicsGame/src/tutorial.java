import java.awt.Color;
import java.awt.Graphics;

public class tutorial {
	sprites sp = new sprites();
	// sprites
	
	boolean day;
	// day or night
	
	int sunX;
	int sunY;
	// coordinates of sun/moon
	
	int selection = 0;
	// what is being selected
	
	public tutorial(boolean d, int x, int y) {
		day = d;
		sunX = x;
		sunY = y;
	}
	
	public void draw(Graphics g) {
		g.setFont(sp.fonts[3]);
		g.setColor(Color.WHITE);
		g.drawImage((day) ? sp.background[0] : sp.background[2], 0, 0, null);
		g.drawImage((day) ? sp.background[1] : sp.background[3], sunX, sunY + 200, null);
		g.drawString("Controls", 100, 50);
		g.setFont(sp.fonts[1]);
		g.drawString("Player 1: ", 125, 100);
		g.drawString("Player 2: ", 375, 100);
		g.setFont(sp.fonts[0]);
		g.drawString("WASD to aim", 125, 150);
		g.drawString("Q and E to move", 125, 200);
		g.drawString("F to switch weapon", 125, 250);
		g.drawString("Left Shift to shoot", 125, 300);
		g.drawString("Arrows to aim", 375, 150);
		g.drawString("N and M to move", 375, 200);
		g.drawString("B to switch weapon", 375, 250);
		g.drawString("Space to shoot", 375, 300);
		g.drawString("Esc to return to title screen, P for menu", 125, 450);
		g.drawString("Cursor to display coordinates", 125, 500);
		g.drawString("Gravity is 10 m/s/s", 125, 550);
	}
}
