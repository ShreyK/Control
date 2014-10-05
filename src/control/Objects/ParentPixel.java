package control.Objects;

import org.newdawn.slick.geom.Shape;

public class ParentPixel {
	protected float xPos, yPos;
	protected int health;
	protected float baseSpeed, speed;
	protected boolean alive;
	protected Shape shape;

	public ParentPixel() {
		alive = true;
		baseSpeed = 1;
		speed = baseSpeed;
	}

	public float getX() {
		return xPos;
	}

	public float getY() {
		return yPos;
	}

	public void setX(float changeX) {
		xPos += changeX;
	}

	public void setY(float changeY) {
		yPos += changeY;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float changeSpeed) {
		speed += changeSpeed;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean rip) {
		alive = rip;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int changeHealth) {
		health += changeHealth;
	}

	public Shape getShape() {
		return shape;
	}
}
