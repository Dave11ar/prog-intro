package expression;

public class Add extends BinaryExpression {
    public Add(FullExpression first, FullExpression second) {
        super(first, second, "+", 1, false, false);
    }

    public int evaluate(int value) {
        return first.evaluate(value) + second.evaluate(value);
    }

    @Override
    public FullExpression getFirst() {
        return first;
    }

    @Override
    public FullExpression getSecond() {
        return second;
    }

    public double evaluate(double value) {
        return first.evaluate(value) + second.evaluate(value);
    }

    public int evaluate(int x, int y, int z) {
        return first.evaluate(x, y, z) + second.evaluate(x, y, z);
    }
}
