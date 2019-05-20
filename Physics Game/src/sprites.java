import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class sprites {
	
	BufferedImage[] tanks = new BufferedImage[4];
	// Blue tank, blue barrel, red tank, red barrel
	
	BufferedImage[] weapons = new BufferedImage[2];
	// Bullet, split
	
	BufferedImage[] buildings = new BufferedImage[2];
	// Ammo building, silo
	
	BufferedImage[] background = new BufferedImage[4];
	// Day, sun, night, moon
	
	BufferedImage[] victories = new BufferedImage[2];
	// Blue win, red win
	
	BufferedImage laser;
	// laser
	
	Font[] fonts = new Font[4];
	// Normal, bold, expanded, expanded bold
	
	public sprites() {
		for (int i = 0; i < 4; i++) {
			try {
				fonts[i] = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Sprites/Font" + i + ".ttf"));
			} catch (IOException | FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		fonts[0] = fonts[0].deriveFont(16f);
		fonts[1] = fonts[1].deriveFont(24f);
		fonts[2] = fonts[2].deriveFont(30f);
		fonts[3] = fonts[3].deriveFont(40f);
		
		for (int i = 0; i < 4; i++) {
			try {
				tanks[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Tank" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 2; i++) {
			try {
				weapons[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Weapon" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 2; i++) {
			try {
				buildings[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Building" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 4; i++) {
			try {
				background[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Background" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 2; i++) {
			try {
				victories[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Victory" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			laser = ImageIO.read(getClass().getResourceAsStream("Sprites/Laser.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
