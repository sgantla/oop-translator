package oop.tree.expressions;

public class BitwiseAndExpression extends BinaryExpression {

    public BitwiseAndExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "&";
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
