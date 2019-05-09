import java.awt.Color;
import java.awt.Graphics;

public class classic {
	
	object p1;
	object p2;
	hill h;
	mountain m;
	building b1;
	building b2;
	
	public classic() {
		p1 = new object(100, 500 - 5, 10, 5, Color.BLUE);
		p2 = new object(490, 500-5, 10, 5, Color.RED);
		h = new hill(150, 500 - 50, 50, 50);
		m = new mountain(250, 500 - 150, 100, 150);
		b1 = new building(400, 500 - 10, 20, 10);
		b2 = new building(420, 500 - 20, 20, 20);
		
	}
	
	public void move() {
		
	}
	
	public void draw(Graphics g) {
		g.drawString("This is classic mode", 100, 200);
		g.drawString("Use space bar to go to title screen", 100, 230);
	
		p1.draw(g);
		g.drawString("Tank 1", 100, 520);
		
		p2.draw(g);
		g.drawString("Tank 2", 490, 550);
		
		h.draw(g);
		g.drawString("Hill", 150, 550);
		
		m.draw(g);
		g.drawString("Mountain", 250, 520);
		
		b1.draw(g);
		g.drawString("Building 1", 400, 550);
		
		b2.draw(g);
		g.drawString("Building 2 (w/ ammo)", 420, 520);
	}

}
