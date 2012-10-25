package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class ContinueStatement extends Statement {

    public String label;

    public ContinueStatement(String label) {
        this.label = label;
    }
}
