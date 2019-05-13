import java.awt.Color;

public class building extends object {
	
	/*
	 * buildings, in orange, give ammo once destroyed after a little while, they come back
	 */
	
	int ammoBonus = 40;
	// default ammo
	
	int reloadCounter = 0;
	// for reloading after destruction
	
	public building(int x) {
		super(x, 390, 20, 10);
		c = Color.ORANGE;
		health = 20;
	}
	
	public void move() {
		if (health <= 0 && visible()) {
			invisible();
			reloadCounter = 100;
		}
		// if the building has been destroyed, remove it for a little while
		
		if (reloadCounter > 0) {
			reloadCounter--;
		} else {
			health = 20;
			setVisible();
		}
		// reload the building once its ready
	}
}
