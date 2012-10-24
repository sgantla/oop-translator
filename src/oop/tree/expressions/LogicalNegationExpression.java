package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;


public class LogicalNegationExpression extends UnaryExpression {

    public LogicalNegationExpression(Expression expression, String operator) {
        this.expression = expression;
        operator = "!";
        //returnType = new Boolean();
    }
}