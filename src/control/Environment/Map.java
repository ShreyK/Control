package control.Environment;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import control.Main.Main;

public class Map {
	private int x, y;
	public final static Color backColor = new Color(10, 10, 10);
	private int[][] map;
	private int size;
	private boolean done;

	public Map() {
		size = 10;
		map = new int[Main.WIDTH / size][Main.HEIGHT / size];
		reset();
		done = false;
	}

	public void update(int xPos, int yPos, int width, int height) {
		int xLeft = xPos / size;
		int yLeft = yPos / size;
		int xRight = (xPos + width) / size;
		int yRight = (yPos + height) / size;
		int x = (xPos + width / 2) / size;
		int y = (yPos + height / 2) / size;
		if (x < getMaxX() && y < getMaxY())
			map[x][y] = 1;
		if (xLeft < getMaxX() && yLeft < getMaxY())
			map[xLeft][yLeft] = 1;
		if (xRight < getMaxX() && yRight < getMaxY())
			map[xRight][yRight] = 1;

		done = true;
		for (x = 0; x < map.length; x++) {
			for (y = 0; y < map[x].length; y++) {
				if (map[x][y] == 0) {
					done = false;
				}
			}
		}
	}
	public boolean isDone() {
		return done;
	}
	public void reset() {
		for (x = 0; x < map.length; x++) {
			for (y = 0; y < map[x].length; y++) {
				map[x][y] = 0;
			}
		}
	}

	public int getSize() {
		return size;
	}

	public int getMaxX() {
		return Main.WIDTH / size;
	}

	public int getMaxY() {
		return Main.HEIGHT / size;
	}

	public int getMap(int x, int y) {
		return map[x][y];
	}

	public void draw(Graphics g) {
		g.setColor(backColor);
		for (x = 0; x < map.length; x++) {
			for (y = 0; y < map[x].length; y++) {
				if (map[x][y] == 1) {
					g.fillRect(x * size, y * size, size, size);
				} else {
					g.drawRect(x * size, y * size, size, size);
				}
			}
		}
	}
}
