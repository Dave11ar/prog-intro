package Mnk;

import java.util.Map;

public class ServerBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.T, '-',
            Cell.I, '|',
            Cell.E, '.',
            Cell.N, ' '
    );
    private static final Map<Integer, Cell> NEXT = Map.of(
            1, Cell.X,
            2, Cell.O,
            3, Cell.T,
            4, Cell.I
    );

    private final Cell[][] cells;
    private Cell turn;
    private int empty = 0;
    private int playersCnt;
    private String space = " ";
    private int divN;
    private int divM;

    private boolean isRhombusBuild(int r, int c) {
        int left = MnkConst.m / 2 - r;
        int right = MnkConst.m / 2 + r;

        if (r >= MnkConst.n / 2) {
            r = MnkConst.n - r - 1;
            left = MnkConst.m / 2 - r;
            right = MnkConst.m / 2 + r;
        }
        if (MnkConst.m % 2 == 0) left--;

        return left <= c && c <= right || !MnkConst.rhombus;
    }

    public ServerBoard() {
        this.cells = new Cell[MnkConst.n][MnkConst.m];
        for (int r = 0; r < MnkConst.n; r++) {
            for (int c = 0; c < MnkConst.m; c++) {
                if (isRhombusBuild(r, c)) {
                    cells[r][c] = Cell.E;
                    empty++;
                } else {
                    cells[r][c] = Cell.N;
                }
            }
        }
        this.playersCnt = MnkConst.players.size();
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move, int no) {
        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;

        if (won(move, 1, 0) || won(move, 1, 1) ||
                won(move, 1, -1) || won(move, 0, 1)) {
            return Result.WIN;
        }

        if (empty == 0) {
            return Result.DRAW;
        }

        turn = NEXT.get(no % playersCnt + 1);
        return Result.UNKNOWN;
    }

    private boolean won(Move move, int du, int dv) {
        return count(move, du, dv) >= MnkConst.k;
    }

    private int count(Move move, int du, int dv) {
        return count(move.getRow(), move.getColumn(), du, dv);
    }

    private int count(int u, int v, int dv, int du) {
        return checker(v, u, dv, du) +
                checker(v, u, -dv, -du) - 1;
    }

    private int checker(int v, int u, int vDelta, int uDelta) {
        int cnt = 0;
        while (v >= 0 && u >= 0 && v < MnkConst.n && u < MnkConst.m
                && cells[v][u] == turn && isRhombus(v, u)) {
            v += vDelta;
            u += uDelta;
            cnt++;
        }
        return cnt;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < MnkConst.n
                && 0 <= move.getColumn() && move.getColumn() < MnkConst.m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell() && isRhombus(move.getRow(), move.getColumn());
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(space.repeat(1 + Integer.toString(MnkConst.n).length()));
        for (int c = 0; c < MnkConst.m; c++) {
            sb.append(c).append(space.repeat(Integer.toString(MnkConst.m).length() - Integer.toString(c).length() + 1));
        }
        sb.append('\n');

        for (int r = 0; r < MnkConst.n; r++) {
            sb.append(r).append(space.repeat(1 + Integer.toString(MnkConst.n).length() - Integer.toString(r).length()));
            for (int c = 0; c < MnkConst.m; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(space.repeat(Integer.toString(MnkConst.m).length()));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private boolean isRhombus(int r, int c) {
        return cells[r][c] != Cell.N;
    }
}
