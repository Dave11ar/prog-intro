package expression;

public class Reverse extends UnaryExpression {
    Integer exp;

    public Reverse(FullExpression expression) {
        super(expression, "reverse", 3, false);
    }

    public int evaluate(int x) {
        exp = expression.evaluate(x);

        return reverseOperation();
    }

    public int evaluate(int x, int y, int z) {
        exp = expression.evaluate(x, y, z);

        return reverseOperation();
    }

    private int reverseOperation() {
        StringBuilder num;
        num = new StringBuilder(exp.toString()).reverse();

        int last = num.length() - 1;
        Long l;
        if (num.charAt(last) == '-') {
            num.deleteCharAt(last);
            l = Long.parseLong("-" + num.toString());
        } else {
            l = Long.parseLong(num.toString());
        }

        return l.intValue();
    }
}
