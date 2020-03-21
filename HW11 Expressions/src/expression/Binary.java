package expression;

public interface Binary extends FullExpression {
    FullExpression getFirst();
    FullExpression getSecond();
}
