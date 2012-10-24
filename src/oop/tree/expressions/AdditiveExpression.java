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
    }

    public Type getType()
    {

        //TODO: What goes here? How do we fill out return type?
        return returnType;
    }
}
