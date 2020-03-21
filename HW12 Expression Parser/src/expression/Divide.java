package expression;

public class Divide extends BinaryExpression {
    public Divide(FullExpression first, FullExpression second) {
        super(first, second, "/", 2, true);
    }

    public int evaluate(int value) {
        return first.evaluate(value) / second.evaluate(value);
    }

    public int evaluate(int x, int y, int z) {
        return first.evaluate(x, y, z) / second.evaluate(x, y, z);
    }
}
