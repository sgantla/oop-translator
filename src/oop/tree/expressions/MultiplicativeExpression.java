package oop.tree.expressions;

public class MultiplicativeExpression extends BinaryExpression {

    public MultiplicativeExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "*";
    }

    public Type getReturnType() {
        return returnType;
    }
}
