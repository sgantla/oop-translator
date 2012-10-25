package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class BreakStatement extends Statement {

    String label;

    public BreakStatement(String label) {
        this.label = label;
    }
}
