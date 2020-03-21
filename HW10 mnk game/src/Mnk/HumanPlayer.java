package Mnk;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        Output.position(out, position);
        Output.cell(out, cell);
        Output.turn(out);
        while (true) {
            Move move = new Move(-1,-1, cell);
            while (move.getRow() == -1) {
                try {
                    String buff = in.nextLine();
                    Scanner sc = new Scanner(buff);
                    int row = sc.nextInt(); int column = sc.nextInt();
                    if (row < 0 || row >= MnkConst.n || column < 0 || column >= MnkConst.m) {
                        Output.inputError(out);
                        continue;
                    }
                    Move tryMove = new Move(row, column, cell);
                    if (!position.isValid(tryMove)) {
                        Output.moveError(out, tryMove);
                        continue;
                    }

                    move = tryMove;
                    break;
                } catch (NoSuchElementException e) {
                    Output.inputError(out);
                }
            }
            if (position.isValid(move)) {
                return move;
            }
            Output.moveError(out, move);
        }
    }
}
