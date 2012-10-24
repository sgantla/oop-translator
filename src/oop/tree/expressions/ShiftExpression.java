package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class ShiftExpression extends BinaryExpression {

    public ShiftExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        this.operator = operator;
    }

    public Type getReturnType() {
        return returnType;
    }
}