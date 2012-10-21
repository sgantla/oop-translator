package oop.tree.statements;

public class ForStatement extends Statement {

    Expression initializationExpression;
    Expression booleanExpression;
    Expression nextExpression;
    Statement statement;

    public ForStatement(Expression initExpression, Expression boolExpression, Expression nextExpression, Statement statement) {
        initializationExpression = initExpression;
        booleanExpression = boolExpression;
        this.nextExpression = nextExpression;
        this.statement = statement;
    }
}
