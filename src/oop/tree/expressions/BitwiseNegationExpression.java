package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class BitwiseNegationExpression extends UnaryExpression {

    public BitwiseNegationExpression(Expression expression, String operator) {
        this.expression = expression;
        operator = "~";
    }

    public Type getReturnType() {
        return this.expression.getReturnType();
    }
}
