package oop.tree.expressions;

public class RelationalExpression extends BinaryExpression {

    Expression leftExpression;
    Expression rightExpression;
    String operator;        // {<, >, <=, >=}

    public RelationalExpression(Expression leftExp, String operator, Expression rightExp)
    {

        leftExpression = leftExp;
        this.operator = operator;
        rightExpression = rightExp;
    }

    public Type getReturnType()
    {
        // xtc's Boolean type
        return BooleanT;
    }
}
