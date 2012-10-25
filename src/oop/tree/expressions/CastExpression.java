package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class CastExpression extends UnaryExpression {

    Type type;

    public CastExpression(Type type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }
}
