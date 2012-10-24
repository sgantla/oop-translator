package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class WhileStatement extends Statement {

    Expression expression;
    Statement statement;

    public WhileStatement(Expression exp, Statement statement) {
        expression = exp;
        this.statement = statement;
    }
}
