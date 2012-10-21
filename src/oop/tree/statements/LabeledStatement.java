package oop.tree.statements;

public class LabeledStatement extends Statement {

    String label;
    Statement statement;

    public LabeledStatement(String label, Statement statement) {
        this.label = label;
        this.statement = statement;
    }
}
