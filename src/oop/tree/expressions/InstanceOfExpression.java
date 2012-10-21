package oop.tree.expressions;

public class InstanceOfExpression extends Expression {

    Type type;

    public InstanceOfExpression(Expression expression, Type type) {
        this.expression = expression;
        this.type = type;
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
