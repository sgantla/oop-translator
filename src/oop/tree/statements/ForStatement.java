package oop.tree.statements;

public class ForStatement extends Statement {

    ForControl forControl;
    Statement statement;

    public ForStatement(ForControl forControl, Statement statement)
        this.forControl= forControl;
        this.statement= statement;
    }
}
