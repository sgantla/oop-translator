package oop.tree.expressions;

public class NewArrayExpression extends Expression {

    TypeName typeName;
    ConcreteDimensions concreteDimensions; //opt
    int dimensions; //opt
    //expression is opt

    public NewArrayExpression(TypeName typeName, ConcreteDimensions concreteDimension, int dimensions, Expression expression) {
        this.typeName = typeName;
        this.concreteDimensions = concreteDimensions;
        this.dimensions = dimensions;
        this.expression = expression;
    }
}
