package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class InstanceOfExpression extends UnaryExpression {

    Type type;

    public InstanceOfExpression(Expression expression, Type type) {
        this.expression = expression;
        this.type = type;
        //returnType = new Boolean();
    }
}
