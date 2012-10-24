package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class PostfixExpression extends UnaryExpression {

    public PostfixExpression(Expression expression, String operator) {
        this.expression = expression;
        this.operator = operator;
    }

    public Type getReturnType() {
        return returnType;
    }
}
