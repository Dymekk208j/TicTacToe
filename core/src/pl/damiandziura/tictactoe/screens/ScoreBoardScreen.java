package pl.damiandziura.tictactoe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import pl.damiandziura.tictactoe.DAO;
import pl.damiandziura.tictactoe.TicTacToeGame;
import pl.damiandziura.tictactoe.score;

/**
 * Created by Dymek on 26.07.2017.
 */

public class ScoreBoardScreen extends AbstractScreen {

    private List<score> list = DAO.getDAO().getTop10();
    private Button back;
    private Label scores[];
    private Skin skin;

    public ScoreBoardScreen(TicTacToeGame game) {
        super(game, "ScoreBoard");
        init();

    }

    private void init() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        initButtons();
        initLabels();

    }

    private void initLabels() {
        scores = new Label[10];

        for(int x = 0; x < list.size(); x++) {
            String s = list.get(x).getName() + " ruchy: " + Integer.toString(list.get(x).getMoves());
            scores[x] = new Label(s, skin);
            scores[x].setPosition(323, 438-(x*34));
            scores[x].setSize(188, 32);
            stage.addActor(scores[x]);
        }

    }

    private void initButtons() {
        Sprite spr1 = new Sprite(new Texture("GFX/GUI/WrBt0.png"));
        Sprite spr2 = new Sprite(new Texture("GFX/GUI/WrBt1.png"));
        back = new Button(new SpriteDrawable(spr1), new SpriteDrawable(spr2));
        back.setPosition(10, 10);
        stage.addActor(back);

        //New Game
        back.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void render(float delta) {
        super.render(delta);
        update(delta);
        batch.begin();
        stage.draw();
        batch.end();
    }

    private void update(float delta) {
        stage.act();
    }
}
