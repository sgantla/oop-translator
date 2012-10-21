package oop.tree.expressions;

public class SubscriptExpression extends BinaryExpression {

    public SubscriptExpression(Expression left, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "";
    }

    public Type getReturnType() {
        return returnType;
    }
}
