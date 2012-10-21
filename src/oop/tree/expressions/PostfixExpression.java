package oop.tree.expressions;

public class PostfixExpression extends UnaryExpression {

    public PostfixExpression(Expression expression, String operator) {
        this.expression = expression;
        this.operator = operator;
    }
}
