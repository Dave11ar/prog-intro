package expression;

public class Multiply extends BinaryExpression {
    public Multiply(FullExpression first, FullExpression second) {
        super(first, second, "*", 2, false, true);
    }

    public int evaluate(int value) {
        return first.evaluate(value) * second.evaluate(value);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void toString(StringBuilder into) {
        super.toString(into);
    }

    @Override
    public String toMiniString() {
        return super.toMiniString();
    }

    @Override
    public void toMiniString(StringBuilder into) {
        super.toMiniString(into);
    }

    @Override
    public boolean equals(Object expression) {
        return super.equals(expression);
    }

    @Override
    public String getOperation() {
        return super.getOperation();
    }

    @Override
    public FullExpression getFirst() {
        return first;
    }

    @Override
    public FullExpression getSecond() {
        return second;
    }


    @Override
    public int getPriority() {
        return super.getPriority();
    }

    @Override
    public boolean getImportant() {
        return super.getImportant();
    }

    @Override
    public boolean needToCheck() {
        return super.needToCheck();
    }

    public double evaluate(double value) {
        return first.evaluate(value) * second.evaluate(value);
    }

    public int evaluate(int x, int y, int z) {
        return first.evaluate(x, y, z) * second.evaluate(x, y, z);
    }
}
