package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

import java.util.List;

public class NewArrayExpression extends UnaryExpression {

    TypeName typeName;
    List<Expression> concreteDimensions; //opt
    int dimensions; //opt

    public NewArrayExpression(TypeName typeName, List<Expression> concreteDimension, int dimensions, Expression expression) {
        this.typeName = typeName;
        this.concreteDimensions = concreteDimensions;
        this.dimensions = dimensions;
        this.expression = expression;
    }

    public Type getReturnType() {
        return returnType;
    }
}
