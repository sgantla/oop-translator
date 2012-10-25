package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import java.util.List;

public class CallExpression extends UnaryExpression {

    // expression is opt
    public TypeArgument typeArguments; //opt
    public String name;
    public List<Expression> arguments;

    public CallExpression(Expression expression, TypeArgument typeArguments, String name, List<Expression> args) {

        this.expression = expression;
        this.typeArguments = typeArguments;
        this.name = name;
        this.arguments = args;
    }
}
