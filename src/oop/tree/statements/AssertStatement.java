package oop.tree.statements;

public class AssertStatement extends Statement {

    Expression booleanExpression;
    Expression valueExpression;

    public AssertStatement(Expression bExp) {
        booleanExpression = bExp;
        valueExpression = null;
    }

    public AssertStatement(Expression bExp, Expression vExp) {
        booleanExpression = bExp;
        valueExpression = vExp;
    }
}
