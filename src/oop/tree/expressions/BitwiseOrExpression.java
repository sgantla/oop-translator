package oop.tree.expressions;

public class BitwiseOrExpression extends BinaryExpression {

    public BitwiseOrExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "|";
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
