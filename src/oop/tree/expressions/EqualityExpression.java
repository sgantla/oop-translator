package oop.tree.expressions;

public class EqualityExpression extends BinaryExpression {

    public EqualityExpression(Expression left, String operator, Expression right) {
       leftExpression = left;
       rightExpression = right;
       operator = "==";
    }

    public Type getReturnType() {
        return BooleanT;
    }
}
