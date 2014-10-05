package control.Objects;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import control.Environment.Map;
import control.Main.Main;

public class EvilPixel extends ParentPixel {
	private int w, h;
	private int pos;
	private Random random = new Random();
	private int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
	private Color[] colors = new Color[] { Color.blue, Color.yellow, Color.red, Color.green, Color.orange, Color.lightGray };
	private Color c;

	public EvilPixel() {
		pos = random.nextInt(4);
		speed = 2;
		if (pos == LEFT) {
			xPos = 0;
			yPos = random.nextFloat() * Main.HEIGHT;
		} else if (pos == RIGHT) {
			xPos = Main.WIDTH;
			yPos = random.nextFloat() * Main.HEIGHT;
		} else if (pos == UP) {
			xPos = random.nextFloat() * Main.WIDTH;
			yPos = 0;
		} else if (pos == DOWN) {
			xPos = random.nextFloat() * Main.WIDTH;
			yPos = Main.HEIGHT;
		}
		w = 40 + random.nextInt(20);
		h = 30 + random.nextInt(20);
		shape = new Rectangle(xPos, yPos, w, h);
		c = colors[random.nextInt(colors.length)];
	}

	public void move(Map map) {
		int size = map.getSize();
		float x = (xPos + w / 2) / size;
		float y = (yPos + h / 2) / size;
		if (x < map.getMaxX() && y < map.getMaxY() && map.getMap((int) x, (int) y) == 1) {
			speed = 1;
		} else {
			speed = 2;
		}
		if (pos == LEFT) {
			xPos += speed;
		} else if (pos == RIGHT) {
			xPos -= speed;
		} else if (pos == UP) {
			yPos += speed;
		} else if (pos == DOWN) {
			yPos -= speed;
		}
		shape.setLocation(xPos, yPos);
		if (xPos > Main.WIDTH || xPos < 0 || yPos > Main.HEIGHT || yPos < 0) {
			alive = false;
		}
	}

	public void draw(Graphics g) {
		g.setColor(c);
		g.fill(shape);
		g.setColor(Color.gray);
		g.draw(shape);
	}
}
