package oop.tree.expressions;

public class AdditiveExpression extends BinaryExpression {

    public AdditiveExpression(Expression left, String operator, Expression right) {

        operator = "+";
        leftExpression = left;
        rightExpression = right;
    }

    public Type getType()
    {

        //TODO: What goes here? How do we fill out return type?
        return returnType;
    }
}
