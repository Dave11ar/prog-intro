package Mnk;

import java.util.List;

public class PlayerBoard implements Position {
    private ServerBoard trueBoard;

    PlayerBoard () {
        trueBoard = new ServerBoard();
    }

    public int play() {
        final Game game = new Game(false);
        return game.play(trueBoard);
    }

    @Override
    public boolean isValid(Move move) {
        return trueBoard.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return trueBoard.getCell(r, c);
    }
}
