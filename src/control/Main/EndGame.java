package control.Main;

import java.awt.Font;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class EndGame extends BasicGameState {
	private final int WIDTH, HEIGHT;
	private final String[] str = new String[] { "in Peace", "in Pieces", "in Pepperonies", "in Pixels" };
	private String string;
	private int x, y;
	private Random random = new Random();
	private TrueTypeFont font;
	public final static Color backColor = new Color(10, 10, 10);

	public EndGame() {
		WIDTH = Main.WIDTH;
		HEIGHT = Main.HEIGHT;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		string = str[random.nextInt(str.length)];
		Font awtFont = new Font("Times New Roman", Font.BOLD, 48);
		font = new TrueTypeFont(awtFont, true);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(backColor);
		for (x = 0; x < Main.WIDTH; x += 10) {
			for (y = 0; y < Main.HEIGHT; y += 10) {
				g.drawRect(x, y, 10, 10);
			}
		}
		g.setColor(Color.green);
		g.drawString("RIP", WIDTH / 2 - 100, HEIGHT / 2);
		g.drawString(string, WIDTH / 2 - 100, HEIGHT / 2 + 20);
		g.setColor(Color.white);
		g.drawString("Press Enter to Restart", WIDTH / 2 - 100, HEIGHT / 2 + HEIGHT / 6);
	}

	@Override
	public int getID() {
		return 2;
	}
}
