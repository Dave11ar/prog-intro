package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {
    private StringBuilder s;
    private int curPos;
    private enum Token {LEFTSHIFT, RIGHTSHIFT, ADD, SUBSTRACT, MULTIPLY, DIVIDE,
        MINUS, REVERSE, SQUARE, VARIABLE, CONST, DIGITS, END, OPEN, CLOSE, ABS};
    Token curToken;
    String curVarConst;

    public CommonExpression parse(String expression) {
        s = new StringBuilder();
        curPos = 0;
        curToken = Token.END;

        skipWhitespaces(expression);
        return parseShift();
    }

    private FullExpression parseShift(){
        FullExpression res = parseAddSub();

        while (true) {
            switch (curToken){
                case LEFTSHIFT:
                    res = new LeftShift(res, parseAddSub());
                    break;
                case RIGHTSHIFT:
                    res = new RightShift(res, parseAddSub());
                    break;
                default:
                    return res;
            }
        }
    }

    private FullExpression parseAddSub(){
        FullExpression res = parseMulDiv();

        while (true) {
            switch (curToken){
                case ADD:
                    res = new Add(res, parseMulDiv());
                    break;
                case SUBSTRACT:
                    res = new Subtract(res, parseMulDiv());
                    break;
                default:
                    return res;
            }
        }
    }

    private FullExpression parseMulDiv(){
        FullExpression res = parseUnary();

        while (true) {
            switch (curToken){
                case MULTIPLY:
                    res = new Multiply(res, parseUnary());
                    break;
                case DIVIDE:
                    res = new Divide(res, parseUnary());
                    break;
                default:
                    return res;
            }
        }
    }

    private FullExpression parseUnary(){
        nextToken();
        FullExpression res;

        switch (curToken){
            case MINUS:
                if (Character.isDigit(s.charAt(curPos))) {
                    curToken = Token.CONST;
                    curPos--;
                    intParse();
                    curPos++;
                    res = new Const(Integer.parseInt(curVarConst));
                    nextToken();
                    break;
                }
                res = new Minus(parseUnary());
                break;
            case DIGITS:
                res = new Digits(parseUnary());
                break;
            case REVERSE:
                res = new Reverse(parseUnary());
                break;
            case SQUARE:
                res = new Square(parseUnary());
                break;
            case VARIABLE:
                res = new Variable(curVarConst);
                nextToken();
                break;
            case CONST:
                res = new Const(Integer.parseInt(curVarConst));
                nextToken();
                break;
            case ABS:
                res = new Abs(parseUnary());
                break;
            case END:
                return null;
            default:
                res = parseShift();
                nextToken();
                break;
        }

        return res;
    }


    private void nextToken() {
        if (curPos >= s.length()) {
            curToken = Token.END;
            return;
        }

        Character curChar = s.charAt(curPos);

        switch (curChar) {
            case '+':
                curToken = Token.ADD;
                break;
            case '-':
                if (curToken == Token.VARIABLE || curToken == Token.CONST || curToken == Token.CLOSE) {
                    curToken = Token.SUBSTRACT;
                } else {
                    curToken = Token.MINUS;
                }
                break;
            case '*':
                curToken = Token.MULTIPLY;
                break;
            case '/':
                curToken = Token.DIVIDE;
                break;
            case '<':
                curToken = Token.LEFTSHIFT;
                curPos++;
                break;
            case '>':
                curToken = Token.RIGHTSHIFT;
                curPos++;
                break;
            case ')':
                curToken = Token.CLOSE;
                break;
            case '(':
                curToken = Token.OPEN;
                break;//9
            case 'a':
                curToken = Token.ABS;
                curPos += 2;
                break;
            case 'd':
                curToken = Token.DIGITS;
                curPos += 5;
                break;
            case 'r':
                curToken = Token.REVERSE;
                curPos += 6;
                break;
            case 's':
                curToken = Token.SQUARE;
                curPos += 5;
                break;
            case 'x':
            case 'y':
            case 'z':
                curToken = Token.VARIABLE;
                curVarConst = curChar.toString();
                break;
            default:
                curToken = Token.CONST;
                intParse();
                break;
        }
        curPos++;
    }

    private void intParse() {
        int l = curPos;
        if (s.charAt(curPos) == '-') {
            curPos++;
        }
        while (curPos < s.length() && Character.isDigit(s.charAt(curPos))) {
            curPos++;
        }

        curVarConst = s.substring(l, curPos);
        curPos--;
    }

    private void skipWhitespaces(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            if (!Character.isWhitespace(expression.charAt(i))) {
                s.append(expression.charAt(i));
            }
        }
    }
}
