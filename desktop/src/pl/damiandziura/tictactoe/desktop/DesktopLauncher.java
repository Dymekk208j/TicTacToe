package pl.damiandziura.tictactoe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.damiandziura.tictactoe.TicTacToeGame;

import static pl.damiandziura.tictactoe.TicTacToeGame.DEFAULT_HEIGHT;
import static pl.damiandziura.tictactoe.TicTacToeGame.DEFAULT_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.height = DEFAULT_HEIGHT;
		config.width = DEFAULT_WIDTH;

		new LwjglApplication(new TicTacToeGame(), config);
	}
}
