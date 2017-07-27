package pl.damiandziura.tictactoe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import pl.damiandziura.tictactoe.TicTacToeGame;

import static pl.damiandziura.tictactoe.TicTacToeGame.DEFAULT_HEIGHT;
import static pl.damiandziura.tictactoe.TicTacToeGame.DEFAULT_WIDTH;


public abstract class AbstractScreen implements Screen {
    protected TicTacToeGame game;
    protected Stage stage;
    private OrthographicCamera camera;
    protected SpriteBatch batch;

    private boolean Paused = false;

    public AbstractScreen(TicTacToeGame game, String ScreenName) {
        this.game = game;
        CreateCamera();
        stage = new Stage(new StretchViewport(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        batch = new SpriteBatch();
        Image background = new Image(new Texture("GFX/Screens/" + ScreenName + ".png"));
        background.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        background.setPosition(0,0);
        stage.addActor(background);
        Gdx.input.setInputProcessor(stage);

    }

    private void CreateCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        camera.update();
    }


    protected void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        setPaused(true);
    }

    @Override
    public void resume() {
        setPaused(false);
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

        game.dispose();
    }

    public boolean isPaused() {
        return Paused;
    }

    public void setPaused(boolean paused) {
        Paused = paused;
    }
}
