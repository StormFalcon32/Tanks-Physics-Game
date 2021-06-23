import java.awt.image.BufferedImage;

public class building extends object {
	
	/*
	 * buildings, in orange, give ammo once destroyed after a little while, they come back
	 */
	
	int ammoBonus = 40;
	// default ammo
	
	long reloadTime = 0;
	// for reloading after destruction
	
	public building(int x, int y, BufferedImage currSp, sprites sp) {
		super(x, y, 20, 10, currSp, sp);
		health = 20;
	}
	
	public void move(int x1, int x2) {
		if (health <= 0 && visible) {
			visible = false;
			reloadTime = System.currentTimeMillis();
		}
		// if the building has been destroyed, remove it for a little while
		
		if (System.currentTimeMillis() - reloadTime >= 20000 && x + 5 != x1 && x + 5 != x2) {
			health = 20;
			visible = true;
		}
		// reload the building once its ready
	}
}
