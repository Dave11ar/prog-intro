package expression;

public class Abs extends UnaryExpression {
    public Abs(FullExpression expression) {
        super(expression, "abs", 3, false);
    }

    public int evaluate(int x) {
        return Math.abs(expression.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return Math.abs(expression.evaluate(x, y, z));
    }
}
