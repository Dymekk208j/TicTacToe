package pl.damiandziura.tictactoe.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import pl.damiandziura.tictactoe.TicTacToeGame;

import static pl.damiandziura.tictactoe.TicTacToeGame.DEFAULT_WIDTH;


public class MainMenuScreen extends AbstractScreen
{
    private TextField text;
    private Button btn[];
    public MainMenuScreen(TicTacToeGame game) {
        super(game, "Menu");
        init();

    }

    private void init() {
        initButtons();

    }

    private void initButtons() {



        btn = new Button[3];

        Sprite spr1 = new Sprite(new Texture("GFX/GUI/ExBt0.png"));
        Sprite spr2 = new Sprite(new Texture("GFX/GUI/ExBt1.png"));
        btn[0] = new Button(new SpriteDrawable(spr1), new SpriteDrawable(spr2));
        float centerX = (DEFAULT_WIDTH/2) - btn[0].getWidth()/2;
        btn[0].setPosition(centerX, 180);
        stage.addActor(btn[0]);

        spr1 = new Sprite(new Texture("GFX/GUI/TbBt0.png"));
        spr2 = new Sprite(new Texture("GFX/GUI/TbBt1.png"));
        btn[1] = new Button(new SpriteDrawable(spr1), new SpriteDrawable(spr2));
        btn[1].setPosition(centerX, 280);
        stage.addActor(btn[1]);

        spr1 = new Sprite(new Texture("GFX/GUI/NgBt0.png"));
        spr2 = new Sprite(new Texture("GFX/GUI/NgBt1.png"));
        btn[2] = new Button(new SpriteDrawable(spr1), new SpriteDrawable(spr2));
        btn[2].setPosition(centerX, 380);
        stage.addActor(btn[2]);

        addButtonListeners();
    }

    private void addButtonListeners() {
        //New Game
        btn[2].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        //Scoreboard
        btn[1].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ScoreBoardScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        //Exit
        btn[0].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
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
