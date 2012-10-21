package oop.tree.expressions;

public class CastExpression extends Expression {

    Type type;

    public CastExpression(Type type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }
}
