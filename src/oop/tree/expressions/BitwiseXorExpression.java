package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class BitwiseXorExpression extends BinaryExpression {

    public BitwiseXorExpression(Expression left, String operator, Expression right) {
        leftExpression = left;
        rightExpression = right;
        this.operator = "^";
        //returnType = new Boolean();
    }
}
