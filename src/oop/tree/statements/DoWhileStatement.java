package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class DoWhileStatement extends Statement {

    Statement statement;
    Expression expression;
    
    public DoWhileStatement(Statement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
    }
}
