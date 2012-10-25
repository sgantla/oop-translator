package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class LogicalNegationExpression extends UnaryExpression {

    public LogicalNegationExpression(Expression expression, String operator) {
        this.expression = expression;
        operator = "!";
        returnType = BooleanT.TYPE;
    }
}
