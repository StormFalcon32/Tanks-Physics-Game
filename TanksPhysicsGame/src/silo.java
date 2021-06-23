import java.awt.image.BufferedImage;

public class silo extends object {
	
	public silo(int x, int y, BufferedImage currSp, sprites sp) {
		super(x, y, 16, 32, currSp, sp);
		health = 20;
	}
	
}
