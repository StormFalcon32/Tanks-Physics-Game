import java.awt.Color;

public class silo extends object {
	
	/*
	 * silos, in gray, give the atomic bomb they don't reload
	 */
	
	public hill hill;
	
	public silo(int x, hill hill) {
		super(x, 380, 10, 20);
		c = Color.GRAY;
		health = 20;
		this.hill = hill;
	}
}
