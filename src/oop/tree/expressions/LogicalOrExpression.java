package oop.tree.expressions;

public class LogicalOrExpression extends BinaryExpression {

    public LogicalOrExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "||";
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
