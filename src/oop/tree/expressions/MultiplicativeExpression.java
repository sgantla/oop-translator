package oop.tree.expressions;

import oop.tree.*;
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

        NumberT.Kind leftReturn = ((IntegerT)left.getReturnType()).getKind();
        NumberT.Kind rightReturn = ((IntegerT)right.getReturnType()).getKind();

        if(leftReturn.compareTo(rightReturn) < 0)
            returnType = rightExpression.getReturnType();
        else
            returnType = leftExpression.getReturnType();
    }
}
