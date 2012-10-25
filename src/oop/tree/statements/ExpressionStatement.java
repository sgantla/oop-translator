package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class ExpressionStatement extends Statement {

    Expression expression;
    
    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
}
