package expression;

public class LeftShift extends BinaryExpression {
    public LeftShift(FullExpression first, FullExpression second) {
        super(first, second, "<<", 0, false);
    }

    public int evaluate(int value) {
        return first.evaluate(value) << second.evaluate(value);
    }

    public int evaluate(int x, int y, int z) {
        return first.evaluate(x, y, z) << second.evaluate(x, y, z);
    }
}
