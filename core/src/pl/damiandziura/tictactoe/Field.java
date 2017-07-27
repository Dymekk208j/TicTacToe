package pl.damiandziura.tictactoe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static pl.damiandziura.tictactoe.screens.GameScreen.Omoves;
import static pl.damiandziura.tictactoe.screens.GameScreen.Xmoves;
import static pl.damiandziura.tictactoe.screens.GameScreen.turn;

public class Field {
    public enum value {
        circle,
        x
    }

    private Button btn;
    private final int width = 146;
    private final int hight = 126;

    private Image img;
    private Stage stage;
    private int posX, posY;
    private int type;

    public Field(Stage stage, int PosX, int PosY) {
        btn = new Button(new Button.ButtonStyle());
        btn.setWidth(width);
        btn.setHeight(hight);
        btn.setX(PosX);
        btn.setY(PosY);


        btn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (turn == true)//circle
                {
                    setValue(value.circle);
                } else//x
                {
                    setValue(value.x);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        this.stage = stage;
        posX = PosX;
        posY = PosY;

        stage.addActor(btn);
        this.type = 0;
    }

    public int getValue() {
        return type;
    }

    public void setValue(value v) {
        if (v == value.circle) {
            type = -1;
            img = new Image(new Texture("GFX/GUI/o.png"));
            Omoves++;

        } else {
            type = 1;
            img = new Image(new Texture("GFX/GUI/x.png"));
            Xmoves++;
        }
        img.setPosition(posX, posY);
        img.setSize(width, hight);
        stage.addActor(img);

        turn = !turn;
        btn.setDisabled(true);
    }
}
