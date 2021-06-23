import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class title {
	
	sprites sp = new sprites();
	// sprites
	
	boolean day;
	// day or night
	
	int sunX;
	int sunY;
	// coordinates of sun/moon
	
	int selection = 0;
	// what is being selected
	
	public title(boolean d, int x, int y) {
		day = d;
		sunX = x;
		sunY = y;
	}
	
	public void draw(Graphics g) {
		g.setFont(sp.fonts[3]);
		g.setColor(Color.WHITE);
		g.drawImage((day) ? sp.background[0] : sp.background[2], 0, 0, null);
		g.drawImage((day) ? sp.background[1] : sp.background[3], sunX, sunY + 200, null);
		g.drawString("Bomb Squad", 100, 200);
		g.setFont(sp.fonts[1]);
		g.drawString("Play", 125, 250);
		g.drawString("Controls", 125, 300);
		g.drawString("Weapons", 125, 350);
		g.drawString("Credits", 125, 400);
		g.drawString("Menu", 125, 450);
		g.drawString(">", 100, selection * 50 + 250);
		g.setFont(sp.fonts[0]);
		drawCenteredString(g, "[Space to Continue]", new Rectangle(0, 400, 600, 200), sp.fonts[0]);
	}
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		// Set the font
		g.setFont(font);
		// Draw the String
		g.drawString(text, x, y);
	}
}
