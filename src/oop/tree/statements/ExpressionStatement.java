package oop.tree;

public class ExpressionStatement extends Statement {

    Expression expression;
    
    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
}
