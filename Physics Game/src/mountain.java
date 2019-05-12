import java.awt.Color;

public class mountain extends object{
	
	/* mountains, in light gray, are the tallest obstacles
	 */

	public mountain(int x) {
		super(x, 250, 50, 150);
		c = Color.LIGHT_GRAY;
		health = 1000000;
	}
}
