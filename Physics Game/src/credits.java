import java.awt.Color;
import java.awt.Graphics;

public class credits {
	sprites sp = new sprites();
	// sprites
	
	boolean day;
	// day or night
	
	int sunX;
	int sunY;
	// coordinates of sun/moon
	
	int selection = 0;
	// what is being selected
	
	public credits(boolean d, int x, int y) {
		day = d;
		sunX = x;
		sunY = y;
	}
	
	public void move() {
		
	}
	
	public void draw(Graphics g) {
		g.setFont(sp.fonts[3]);
		g.setColor((day) ? Color.BLACK : Color.WHITE);
		g.drawImage((day) ? sp.background[0] : sp.background[2], 0, 0, null);
		g.drawImage((day) ? sp.background[1] : sp.background[3], sunX, sunY + 200, null);
		g.drawString("Credits", 100, 50);
		g.setFont(sp.fonts[1]);
		g.drawString("Ben", 125, 200);
		g.drawString("Joey", 125, 250);
		g.drawString("Lohith", 125, 300);
		g.drawString("Jason", 125, 350);
		g.drawString("Khalid", 125, 400);
		g.drawString("Preet", 125, 450);
		g.setFont(sp.fonts[0]);
		g.drawString("Lead Programmer + Art", 275, 200);
		g.drawString("Engine Programmer", 275, 250);
		g.drawString("Math + Design", 275, 300);
		g.drawString("Lead Artist", 275, 350);
		g.drawString("Art + Design + Presenter", 275, 400);
		g.drawString("Design + Presenter", 275, 450);
	}
}
