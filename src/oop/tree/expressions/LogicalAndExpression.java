package oop.tree.expressions;

public class LogicalAndExpression extends BinaryExpression {

    public LogicalAndExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "&&";
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
