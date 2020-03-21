package Mnk;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int result;
        try {
            if ((MnkConst.rhombus && MnkConst.n != MnkConst.m) || (MnkConst.n < MnkConst.k && MnkConst.m < MnkConst.k)
                    || MnkConst.players.size() < 2 || MnkConst.n * MnkConst.m > 1000000) {
                throw new InputMismatchException("Invalid parameters");
            }
        } catch (ExceptionInInitializerError e) {
            throw new InputMismatchException("Invalid parameters");
        }

        Scanner sc = new Scanner(System.in);
        String play;

        do {
            result = new PlayerBoard().play();
            Output.gameOver(System.out, result);

            play = sc.nextLine();
            while (!play.equals("Y") && !play.equals("N")) {
                Output.inputError(System.out);
                play = sc.nextLine();
            }
        } while (play.equals("Y"));
    }
}
