package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class MultiplicativeExpression extends BinaryExpression {

    public MultiplicativeExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "*";
    }

    public Type getReturnType() {
        return returnType;
    }
}
