package pl.damiandziura.tictactoe;

/**
 * Created by Dymek on 27.07.2017.
 */

public class score {
    private String name;
    private int moves;

    public score(String name, int moves) {
        this.name = name;
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}
