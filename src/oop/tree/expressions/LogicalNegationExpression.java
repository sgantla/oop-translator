package oop.tree.expressions;

public class LogicalNegationExpression extends UnaryExpression {

    public LogicalNegationExpression(Expression expression) {
        this.expession = expression;
        operator = "!";
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
