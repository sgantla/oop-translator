package oop.tree.statements;


public class ConditionalStatement extends Statement {

    Expression expression;
    Statement trueStatement;
    Statement falseStatement;

    public ConditionalStatement(Expression exp, Statement trueState, Statement falseState) {
        expression = exp;
        trueStatement = trueState;
        falseStatement = falseState;
    }
}
