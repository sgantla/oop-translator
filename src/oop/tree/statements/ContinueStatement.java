package oop.tree.statements;

public class ContinueStatement extends Statement {

    String label;

    public ContinueStatement() {
    }

    public ContinueStatement(String label) {
        this.label = label;
    }
}
