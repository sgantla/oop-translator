package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

import java.util.List;

public class CallExpression extends UnaryExpression {

    // expression is opt
    List<Type> typeArguments; //opt
    String name;
    List<Expression> arguments;

    public CallExpression(Expression expression, List<Type> typeArguments, String name, List<Expression> args) {

        this.expression = expression;
        this.typeArguments = typeArguments;
        this.name = name;
        this.arguments = args;
    }
}
