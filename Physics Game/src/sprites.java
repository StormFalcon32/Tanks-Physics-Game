import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class sprites {
	
	BufferedImage[] weapons = new BufferedImage[2];
	// Bullet, split
	
	BufferedImage[] tanks = new BufferedImage[4];
	// Blue tank, blue barrel, red tank, red barrel
	
	BufferedImage laser;
	
	public sprites() {
		for (int i = 0; i < 4; i++) {
			try {
				tanks[i] = ImageIO.read(this.getClass().getResourceAsStream("Sprites/Tank" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 2; i++) {
			try {
				weapons[i] = ImageIO.read(this.getClass().getResourceAsStream("Sprites/Weapon" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			laser = ImageIO.read(this.getClass().getResourceAsStream("Sprites/Laser.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
