package oop.tree.expressions;

public class CallExpression extends Expression {

    // expression is opt
    TypeArgument typeArguments; //opt
    String name;
    Argument arguments;

    public CallExpression(Expression expression, TypeArgument typeArguments, String name, Argument args) {

        this.expression = expression;
        this.typeArguments = typeArguments;
        this.name = Translator.getName(name); // not sure how we're going to do this, assuming its going to take place
                                              // in translator util function
        this.arguments = args;
    }
}
