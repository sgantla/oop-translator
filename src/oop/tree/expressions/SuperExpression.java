package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class SuperExpression extends UnaryExpression {

    //expression opt
    
    //TODO: how are we handling this call?
    public SuperExpression(Expression expression) {

        this.expression = expression;
    }

    public Type getReturnType() {
        return null;
    }
}
