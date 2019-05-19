import java.awt.Graphics;

public class title {
	
	sprites sp = new sprites();
	// sprites
	
	public title() {
		
	}
	
	public void move() {
		
	}
	
	public void draw(Graphics g) {
		g.setFont(sp.fonts[3]);
		g.drawImage(sp.background[0], 0, 0, null);
		g.drawString("Bomb Squad", 100, 150);
		g.drawImage(sp.background[1], 200, 300, null);
	}
	
}
