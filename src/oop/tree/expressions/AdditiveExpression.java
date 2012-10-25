package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class AdditiveExpression extends BinaryExpression {

    public AdditiveExpression(Expression left, String operator, Expression right) {

        operator = "+";
        leftExpression = left;
        rightExpression = right;

        if (leftExpression.getReturnType() instanceof IntegerT && rightExpression.getReturnType() instanceof IntegerT) {

        NumberT.Kind leftReturn = ((IntegerT)left.getReturnType()).getKind();
        NumberT.Kind rightReturn = ((IntegerT)right.getReturnType()).getKind();

        if(leftReturn.compareTo(rightReturn) < 0)
            returnType = rightExpression.getReturnType();
        else
            returnType = leftExpression.getReturnType();
        }
        else {
            // returnType is string
        }
    }
}
