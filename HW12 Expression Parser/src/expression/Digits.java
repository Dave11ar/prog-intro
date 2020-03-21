package expression;

public class Digits extends UnaryExpression {
    String num;

    public Digits(FullExpression expression) {
        super(expression, "digits", 3, false);
    }

    public int evaluate(int x) {
        num = ((Integer)expression.evaluate(x)).toString();
        return Result();
    }

    public int evaluate(int x, int y, int z) {
        num = ((Integer)expression.evaluate(x, y, z)).toString();
        return Result();
    }

    private int Result() {
        int sum = 0;

        int i = 0;
        if (num.charAt(0) == '-') i++;
        for ( ; i < num.length(); i++) {
            sum += num.charAt(i) - '0';
        }
        return sum;
    }
}
