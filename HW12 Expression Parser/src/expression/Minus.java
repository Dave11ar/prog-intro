package expression;

public class Minus extends UnaryExpression {
    public Minus(FullExpression expression) {
        super(expression, "-", 3, false);
    }

    public int evaluate(int x) {
        int a = expression.evaluate(x);
        return -a;
    }

    public int evaluate(int x, int y, int z) {
        int a = expression.evaluate(x, y, z);
        return -a;
    }
}
