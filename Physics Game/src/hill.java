import java.awt.Color;

public class hill extends object{
	
	/* hills, in green, are mere obstacles
	 * however, they can be destroyed
	 */

	public hill(int x) {
		super(x, 350, 50, 50);
		c = Color.GREEN;
		health = 50;
	}
}
