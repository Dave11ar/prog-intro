package expression;

public class Square extends UnaryExpression {
    public Square(FullExpression expression) {
        super(expression, "square", 3, false);
    }

    public int evaluate(int x) {
        int a = expression.evaluate(x);
        return a * a;
    }

    public int evaluate(int x, int y, int z) {
        int a = expression.evaluate(x, y, z);
        return a * a;
    }
}
