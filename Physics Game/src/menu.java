import java.awt.Color;
import java.awt.Graphics;

public class menu {
	sprites sp = new sprites();
	// sprites
	
	boolean day;
	// day or night
	
	int sunX;
	int sunY;
	// coordinates of sun/moon
	
	int selection = 0;
	// what is being selected
	
	classic c;
	// game

	boolean load;
	// fast cooldown and regen
	
	boolean trajectory;
	// drawing of trajectories
	
	public menu(boolean d, int x, int y, classic c) {
		day = d;
		sunX = x;
		sunY = y;
		this.c = c;
	}
	
	public void draw(Graphics g) {
		g.setFont(sp.fonts[3]);
		g.setColor(Color.WHITE);
		g.drawImage((day) ? sp.background[0] : sp.background[2], 0, 0, null);
		g.drawImage((day) ? sp.background[1] : sp.background[3], sunX, sunY + 200, null);
		g.drawString("Menu", 100, 50);
		g.setFont(sp.fonts[1]);
		if(load) g.drawString("Faster Gameplay (ON)", 125, 200);
		else g.drawString("Faster Gameplay (OFF)", 125, 200);
		if(trajectory) g.drawString("Draw Trajectories (ON)", 125, 300);
		else g.drawString("Draw Trajectories (OFF)", 125, 300);
		g.setFont(sp.fonts[0]);
		g.drawString("Press O", 275, 250);
		g.drawString("Press P", 275, 350);
	}
}
