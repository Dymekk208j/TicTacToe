package pl.damiandziura.tictactoe;

import com.badlogic.gdx.Game;

import pl.damiandziura.tictactoe.screens.MainMenuScreen;
import pl.damiandziura.tictactoe.screens.VictoryScreen;


public class TicTacToeGame extends Game {
    public final static int DEFAULT_WIDTH = 800;
    public final static int DEFAULT_HEIGHT = 600;


    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }

}
