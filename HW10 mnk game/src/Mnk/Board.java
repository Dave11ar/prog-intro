package Mnk;

public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move, int no);
}
