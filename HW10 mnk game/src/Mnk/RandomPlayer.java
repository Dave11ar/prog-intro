package Mnk;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(MnkConst.n);
            int c = random.nextInt(MnkConst.m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                Output.position(System.out, position);
                Output.cell(System.out, cell);
                return move;
            }
        }
    }
}
