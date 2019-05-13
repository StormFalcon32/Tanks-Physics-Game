import java.awt.Color;

public class building extends object {
	
	/*
	 * buildings, in orange, give ammo once destroyed after a little while, they come back
	 */
	
	int ammoBonus = 40;
	// default ammo
	
	long reloadTime = 0;
	// for reloading after destruction
	
	public building(int x) {
		super(x, 390, 20, 10);
		c = Color.ORANGE;
		health = 20;
	}
	
	public void move() {
		if (health <= 0 && visible) {
			visible = false;
			reloadTime = System.currentTimeMillis();
		}
		// if the building has been destroyed, remove it for a little while
		
		if (System.currentTimeMillis() - reloadTime >= 5000) {
			health = 20;
			visible = true;
		}
		// reload the building once its ready
	}
}
