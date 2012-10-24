package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class ContinueStatement extends Statement {

    String label;

    public ContinueStatement(String label) {
        this.label = label;
    }
}
