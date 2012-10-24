package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class ThrowStatement extends Statement {

    Expression expression;

    public ThrowStatement(Expression expression) {
        this.expression = expression;
    }
}
