package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;


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
