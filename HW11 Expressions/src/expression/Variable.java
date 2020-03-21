package expression;

import java.util.Objects;

public class Variable implements FullExpression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public int evaluate(int value) {
        return value;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public void toString(StringBuilder into) {
        into.append(variable);
    }

    @Override
    public String toMiniString() {
        return variable;
    }

    @Override
    public void toMiniString(StringBuilder into) {
        into.append(variable);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Variable var = (Variable) obj;

        return getOperation().equals(var.getOperation());
    }

    @Override
    public String getOperation() {
        return variable;
    }

    public int hashCode() {
        return Objects.hash(variable);
    }


    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    public boolean getImportant() {
        return false;
    }

    public boolean needToCheck() {
        return false;
    }

    public double evaluate(double value) {
        return value;
    }

    public int evaluate(int x, int y, int z) {
        return getTriple(x, y, z);
    }

    private int getTriple(int x, int y, int z) {
        if (variable.equals("x")) {
            return x;
        } else if (variable.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
