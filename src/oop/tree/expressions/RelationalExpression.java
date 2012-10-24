package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class RelationalExpression extends BinaryExpression {

    public RelationalExpression(Expression leftExp, String operator, Expression rightExp)
    {

        leftExpression = leftExp;
        this.operator = operator;
        rightExpression = rightExp;
        //returnType = new Boolean();
    }
}
