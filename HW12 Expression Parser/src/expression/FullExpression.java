package expression;

public interface FullExpression extends CommonExpression {
    void toString(StringBuilder into);
    void toMiniString(StringBuilder into);
    int getPriority();
    boolean getImportant();
    String getOperation();
}
