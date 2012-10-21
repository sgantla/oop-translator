package oop.tree.expressions;

public class SelectionExpression extends UnaryExpression {

    public SelectionExpression(Expression expression, String operator) {
        this.expression = expression;
        this.operator = operator;
    }
}
