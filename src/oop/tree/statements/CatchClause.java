package oop.tree.statements;

public class CatchClause extends Statement {

    FormalParameter parameter;
    Statement statement;

    public CatchClause(FormalParameter parameter, Statement statement) {
        this.parameter = parameter;
        this.statement = statement;
    }
}
