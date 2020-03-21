package Mnk;

import java.io.PrintStream;

public class Output {
    static void inputError(PrintStream out) {
        out.println("Invalid input");
    }

    static void moveError(PrintStream out, Move move) {
        out.println("Point " + move + " is busy");
    }

    static void position(PrintStream out, Position position) {
        out.println("Position");
        out.println(position);
    }

    static void turn(PrintStream out) {
        out.println("Enter row and column");
    }

    static void cell(PrintStream out, Cell cell) {
        out.println(cell + "'s move");
    }

    static void gameOver(PrintStream out, int result) {
        System.out.println("Game result: " + result + "\nDo you want to play again?(Y, N)");
    }
}
