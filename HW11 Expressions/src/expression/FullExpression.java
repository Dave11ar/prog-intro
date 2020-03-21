package expression;

public interface FullExpression extends Expression, DoubleExpression, TripleExpression {
    void toString(StringBuilder into);
    void toMiniString(StringBuilder into);
    int getPriority();
    boolean getImportant();
    String getOperation();
    boolean needToCheck();
}
