package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class ConditionalExpression extends TernaryExpression {

    public ConditionalExpression(Expression exp1, Expression exp2, Expression exp3) {
        operand1 = exp1;
        operand2 = exp2;
        operand3 = exp3;

        Type returnType1 = exp2.getReturnType();
        Type returnType2 = exp3.getReturnType();

        if (returnType1.equals(returnType2))
            returnType = returnType1;
        else {
            //TODO: What goes here?
        }
    }

    public Type getReturnType() {
        return returnType;
    }
}
