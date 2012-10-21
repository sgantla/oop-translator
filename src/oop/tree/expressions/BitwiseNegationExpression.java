package oop.tree.expressions;

public class BitwiseNegationExpression extends UnaryExpression {

    public BitwiseNegationExpression(Expression expression) {
        this.expression = expression;
        operator = "~";
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
