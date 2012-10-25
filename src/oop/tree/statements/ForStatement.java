package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class ForStatement extends Statement {

    public ForControl forControl;
    public Statement statement;

    public ForStatement(ForControl forControl, Statement statement) {
        this.forControl= forControl;
        this.statement= statement;
    }
}
