package pl.damiandziura.tictactoe.screens;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import pl.damiandziura.tictactoe.Field;
import pl.damiandziura.tictactoe.TicTacToeGame;

public class GameScreen extends AbstractScreen {
    private boolean win = false;

    private Button back;
    public static boolean turn = false; // true - circle, false -x
    public static int Xmoves = 0;
    public static int Omoves = 0;
    private Field field[];
    private Label informationLabel;


    public GameScreen(TicTacToeGame game) {
        super(game, "Game");
        init();
    }


    private void init() {
        initFields();
        initLabels();
        initButtons();

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

    private void initLabels() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        Color color = new Color();
        color.set(1, 1, 1, 1);
        labelStyle.fontColor = color;
        informationLabel = new Label("", labelStyle);
        informationLabel.setX(0);
        informationLabel.setY(300);
        stage.addActor(informationLabel);
    }

    private void initFields() {
        field = new Field[9];

        int x = 165;
        int y = 380;
        for (int a = 0; a < 9; a++) {
            field[a] = new Field(stage, x, y);
            x += 150;
            if ((a + 1) % 3 == 0) {
                y -= 130;
                x = 165;
            }
        }
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
        checkWin();
    }

    private void checkWin() {
        for (int b = 0; b < 3; b++) {
            int a = b * 3;
            int score = field[a].getValue() + field[a + 1].getValue() + field[a + 2].getValue();
            if (score == -3) {
                setWinner(Field.value.circle);
                break;
            } else if (score == 3) {
                setWinner(Field.value.x);
                break;
            }
        }

        for (int b = 0; b < 3; b++) {
            int score = field[b].getValue() + field[b + 3].getValue() + field[b + 6].getValue();
            if (score == -3) {
                setWinner(Field.value.circle);
                break;
            } else if (score == 3) {
                setWinner(Field.value.x);
                break;
            }
        }


        int score = field[0].getValue() + field[4].getValue() + field[8].getValue();
        if (score == -3) {
            setWinner(Field.value.circle);
        } else if (score == 3) {
            setWinner(Field.value.x);
        }

        score = field[2].getValue() + field[4].getValue() + field[6].getValue();
        if (score == -3) {
            setWinner(Field.value.circle);
        } else if (score == 3) {
            setWinner(Field.value.x);
        }

        if(win == false && (Xmoves+Omoves) == 9)
        {
            Xmoves = -1;
            Omoves = -1;
            game.setScreen(new VictoryScreen(game));
        }

    }

    private void setWinner(Field.value value) {
        win = true;
        if(value == Field.value.circle)
        {
            Xmoves = -1;

        }else
        {
            Omoves = -1;

        }

        game.setScreen(new VictoryScreen(game));
    }

}


