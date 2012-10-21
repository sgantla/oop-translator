package oop.tree.statements;

public class DoWhileStatement extends Statement {

    Expression expression;
    Statement statement;
    
    public DoWhileStatement(Statement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
    }
}
