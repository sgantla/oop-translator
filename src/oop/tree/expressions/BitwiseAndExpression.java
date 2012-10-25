package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class BitwiseAndExpression extends BinaryExpression {

    public BitwiseAndExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "&";

    }

    public Type getReturnType() {
        if(leftExpression.getReturnType() == BooleanT.TYPE)
            returnType = BooleanT.TYPE;
        else {
            NumberT.Kind leftReturn = ((NumberT)leftExpression.getReturnType()).getKind();
            NumberT.Kind rightReturn = ((NumberT)rightExpression.getReturnType()).getKind();

            if(leftReturn.compareTo(rightReturn) < 0)
                returnType = rightExpression.getReturnType();
            else
                returnType = leftExpression.getReturnType();
        }

        return returnType;
    }
}
