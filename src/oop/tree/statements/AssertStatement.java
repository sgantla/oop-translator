package oop.tree.statements;

public class AssertStatement extends Statement {

    Expression booleanExpression; //opt
    Expression valueExpression;

    public AssertStatement(Expression bExp, Expression vExp) {
        booleanExpression = bExp;
        valueExpression = vExp;
    }
}
