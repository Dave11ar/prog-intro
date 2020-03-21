package Mnk;

public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        for (int r = 0; r < MnkConst.n; r++) {
            for (int c = 0; c < MnkConst.m; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    Output.position(System.out, position);
                    Output.cell(System.out, cell);
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
