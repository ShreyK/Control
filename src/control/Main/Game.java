package control.Main;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import control.Environment.Map;
import control.Objects.EvilPixel;
import control.Objects.Pixel;

public class Game extends BasicGameState {
	private int count, timer;
	private float move;
	private final int WIDTH, HEIGHT;
	private Pixel pix;
	private ArrayList<EvilPixel> ePix;
	private int i, x, y;
	private float zone;
	private Map map;

	public Game() {
		WIDTH = Main.WIDTH;
		HEIGHT = Main.HEIGHT;
		map = new Map();
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		initialize(container);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		count++;
		if (container.getFPS() != 0) {
			if (count % container.getFPS() == 0) {
				timer++;
			}
		}
		if (move <= 0) {
			pix.setAlive(false);
		}
		if (!pix.isAlive()) {
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
			initialize(container);
		}
		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
			pix.move(0, -1);
			move += .6f;
		} else if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_DOWN)) {
			pix.move(-1, 0);
			move += .6f;
		} else if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_LEFT)) {
			pix.move(0, 1);
			move += .6f;
		} else if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			pix.move(1, 0);
			move += .6f;
		} else {
			move -= .03;
		}

		if (move > 3f) {
			move = 3f;
		}
		pix.update();
		map.update((int) pix.getX(), (int) pix.getY(), pix.getSize(), pix.getSize());
		zone = map((float) timer, 0, 60, 1, 5);
		spawn();
		for (i = 0; i < ePix.size(); i++) {
			EvilPixel e = (EvilPixel) ePix.get(i);
			if (e.isAlive()) {
				e.move(map);
			} else {
				ePix.remove(e);
			}
			if (pix.getShape().intersects(e.getShape())) {
				e.setAlive(false);
				pix.setAlive(false);
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		map.draw(g);
		g.setColor(Color.green);
		if (move < 3) {
			g.drawString("MOVE!!", WIDTH / 2 - 10, HEIGHT / 2 - HEIGHT / 4);
			g.drawString("" + new DecimalFormat("##.#").format(move), WIDTH / 2, HEIGHT / 2 - HEIGHT / 4 + 15);
		}
		g.drawString("Timer: " + timer, 5, HEIGHT - 20);
		pix.draw(g);
		for (i = 0; i < ePix.size(); i++) {
			EvilPixel e = (EvilPixel) ePix.get(i);
			e.draw(g);
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	public void initialize(GameContainer container) {
		pix = new Pixel(container);
		ePix = new ArrayList<EvilPixel>();
		timer = 0;
		zone = 1;
		count = 0;
		move = 3;
		map.reset();
	}

	public float map(float value, float istart, float istop, float ostart, float ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}

	public void spawn() {
		if (count % 60 == 0) {
			for (i = 0; i < zone; i++) {
				ePix.add(new EvilPixel());
			}
		}
	}
}
