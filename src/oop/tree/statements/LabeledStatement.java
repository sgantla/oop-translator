package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class LabeledStatement extends Statement {

    String label;
    Statement statement;

    public LabeledStatement(String label, Statement statement) {
        this.label = label;
        this.statement = statement;
    }
}
