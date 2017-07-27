package pl.damiandziura.tictactoe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import pl.damiandziura.tictactoe.DAO;
import pl.damiandziura.tictactoe.TicTacToeGame;
import pl.damiandziura.tictactoe.score;

import static pl.damiandziura.tictactoe.screens.GameScreen.Omoves;
import static pl.damiandziura.tictactoe.screens.GameScreen.Xmoves;

public class VictoryScreen extends AbstractScreen {
    private TextField text;
    private TextButton btAccept, btBack;
    private Skin skin;
    private Label label;
    private Image imgX, imgO;
    private int moves;

    public VictoryScreen(TicTacToeGame game) {
        super(game, "Menu");
        init();
        initImages();

    }

    private void initImages() {
        imgX = new Image(new Texture("GFX/GUI/X.png"));
        imgX.setSize(146, 126);
        imgX.setPosition(327,280);
        imgX.setVisible(false);
        stage.addActor(imgX);

        imgO = new Image(new Texture("GFX/GUI/O.png"));
        imgO.setSize(146, 126);
        imgO.setPosition(327,280);
        imgO.setVisible(false);
        stage.addActor(imgO);
    }

    private void init() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        initButtons();
        initTextField();
        initButtonListeners();
        initImages();

        moves = (Omoves > Xmoves) ? Omoves : Xmoves;
        String stext = "W "+Integer.toString(moves)+" ruchach zwyciezyl: ";
        if(Omoves > -1)
        {
            imgO.setVisible(true);
        }else if(Xmoves > -1)
        {
            imgX.setVisible(true);
        }else
        {
            stext = "Remis";
            text.setVisible(false);
            btAccept.setVisible(false);
        }

        label = new Label(stext, skin);

        label.setPosition(400, 430, Align.center);
        label.setSize(250,30);
        stage.addActor(label);
    }

    private void initButtonListeners() {
        btAccept.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                DAO.getDAO().Insert(text.getText(), moves);
                Omoves = 0;
                Xmoves = 0;
                game.setScreen(new MainMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Omoves = 0;
                Xmoves = 0;
                game.setScreen(new MainMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }


    private void initTextField() {
        text = new TextField("Wprowadz imie", skin);
        text.setPosition(275, 220);
        text.setSize(250, 30);
        stage.addActor(text);
    }


    private void initButtons() {
        btAccept = new TextButton("Zapisz", skin);
        btAccept.setSize(100, 30);
        btAccept.setPosition(430, 150);
        stage.addActor(btAccept);

        btBack = new TextButton("Nie zapisuj", skin);
        btBack.setSize(100, 30);
        btBack.setPosition(270, 150);
        stage.addActor(btBack);
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
