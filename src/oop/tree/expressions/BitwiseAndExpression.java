package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.BooleanT;

public class BitwiseAndExpression extends BinaryExpression {

    public BitwiseAndExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "&";
        //returnType = new Boolean();
    }
}
