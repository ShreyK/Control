package control.Main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	public static int WIDTH, HEIGHT;

	public Main(String title) {
		super(title);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("GAME"));
		WIDTH = 800;
		HEIGHT = 600;
		System.out.println(WIDTH + " " + HEIGHT);
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.setTargetFrameRate(60);
		// app.setMouseGrabbed(true);
		app.setShowFPS(false);
		app.start();
	}

	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new Menu());
		this.addState(new Game());
		this.addState(new EndGame());
	}
}
