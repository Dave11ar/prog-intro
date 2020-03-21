package expression.parser;

import expression.TripleExpression;

public class Test {
    public static void main(final String[] args) {
        ExpressionParser parser = new ExpressionParser();

        TripleExpression test = parser.parse("((x) * (y)) / (reverse ((-475336393) - (752867329)))");

        int g =test.evaluate(-101971866, 1641629423, 871165933);

        return;
    }
}


//--((-(-5+((16*x)*y)))+(1*z))--11
//-(-(--5 + 16*x*y)+1*z)--11