package oop.tree.statements;

public class DoWhileStatement extends Statement {

    Statement statement;
    Expression expression;
    
    public DoWhileStatement(Statement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
    }
}
