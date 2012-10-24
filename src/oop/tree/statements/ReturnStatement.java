package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class ReturnStatement extends Statement {

    Expression returnExpression;

    public ReturnStatement(Expression expression) {
        returnExpression = expression;
    }
}
