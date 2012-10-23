package oop.tree.expressions;

public class ClassLiteralExpression extends Literal {

    Type type;
    
    public ClassLiteralExpression(Type type) {
        this.type = type;
    }
}
