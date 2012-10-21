package oop.tree.statements;

public class ReturnStatement extends Statement {

    Expression returnExpression;

    public ReturnStatement(Expression expression) {
        returnExpression = expression;
    }
}
