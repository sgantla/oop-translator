package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class BitwiseOrExpression extends BinaryExpression {

    public BitwiseOrExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        operator = "|";
        //returnType = new Boolean();
    }
}
