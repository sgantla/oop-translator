package oop.tree.statements;

public class ThrowStatement extends Statement {

    Expression expression;

    public ThrowStatement(Expression expression) {
        this.expression = expression;
    }
}
