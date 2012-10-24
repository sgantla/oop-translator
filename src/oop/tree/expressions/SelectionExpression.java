package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class SelectionExpression extends UnaryExpression {

    public SelectionExpression(Expression expression, String operator) {
        this.expression = expression;
        this.operator = operator;
    }

    public Type getReturnType() {
        return returnType;
    }
}
