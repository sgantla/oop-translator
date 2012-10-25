package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class BasicCastExpression extends UnaryExpression {

    TypeName typeName;
    int dimensions; //opt

    //Do we need a ThrowStatement? Or some sort of check to see if a ThrowStatement is needed?

    public BasicCastExpression(TypeName typeName, int dimensions, Expression expression) {
        this.typeName = typeName;
        this.dimensions = dimensions;
        this.expression = expression;

        //TODO: returnType = ??
    }
}
