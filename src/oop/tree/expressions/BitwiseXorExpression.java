package oop.tree.expressions;

public class BitwiseXorExpression extends BinaryExpression {

    public BitwiseXorExpression(Expression left, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operation = "^";
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
