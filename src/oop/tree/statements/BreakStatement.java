package oop.tree.statements;

public class BreakStatement extends Statement {

    String label;

    public BreakStatement() {
        this.label = "";
    }

    public BreakStatement(String label) {
        this.label = label;
    }
}
