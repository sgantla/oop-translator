package oop.tree.expressions;

public class BasicCastExpression extends Expression {

    TypeName typeName;
    int dimensions; //opt

    //Do we need a ThrowStatement? Or some sort of check to see if a ThrowStatement is needed?

    public BasicCastExpression(TypeName typeName, int dimensions, Expression expression) {
        this.typeName = typeName;
        this.dimensions = dimensions;
        this.expression = expression;
    }
}
