package control.Objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import control.Main.Main;

public class Pixel extends ParentPixel {
	private int i;
	private int size;

	public Pixel(GameContainer container) {
		size = 20;
		speed = 5;
		health = 1;
		xPos = (float) container.getWidth() / 2 - size / 2;
		yPos = (float) container.getHeight() / 2 - size / 2;
		shape = new Rectangle(xPos, yPos, size, size);
	}

	public void move(int horizSpeed, int vertSpeed) {
		xPos += speed * horizSpeed;
		yPos += speed * vertSpeed;
		shape.setLocation(xPos, yPos);

	}

	public void update() {
		if (xPos < 0) {
			xPos = 0;
		} else if (xPos > Main.WIDTH - size) {
			xPos = Main.WIDTH - size;
		}
		if (yPos < 0) {
			yPos = 0;
		} else if (yPos > Main.HEIGHT - size) {
			yPos = Main.HEIGHT - size;
		}
		if (health < 1) {
			alive = false;
			health = 0;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fill(shape);
	}

	public int getSize() {
		return size;
	}
}
