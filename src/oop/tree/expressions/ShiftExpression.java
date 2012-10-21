package oop.tree.expressions;

public class ShiftExpression extends BinaryExpression {

    public ShiftExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        this.operator = opeartor;
    }

    public Type getReturnType() {
        return returnType;
    }
}
