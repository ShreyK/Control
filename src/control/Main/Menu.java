package control.Main;

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

public class Menu extends BasicGameState {
	private final int WIDTH, HEIGHT;
	private int x, y;
	public final static Color backColor = new Color(10, 10, 10);

	public Menu() {
		WIDTH = Main.WIDTH;
		HEIGHT = Main.HEIGHT;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(backColor);
		for (x = 0; x < Main.WIDTH; x+= 10) {
			for (y = 0; y < Main.HEIGHT; y+= 10) {
					g.drawRect(x, y , 10, 10);
			}
		}
		g.setColor(Color.green);
		g.drawString("CONTROL", WIDTH / 2 - 30, HEIGHT / 2);
		g.setColor(Color.white);
		g.drawString("Press Enter to Start", WIDTH / 2 - 90, HEIGHT / 2 + HEIGHT / 6);

	}
	@Override
	public int getID() {
		return 0;
	}

}
