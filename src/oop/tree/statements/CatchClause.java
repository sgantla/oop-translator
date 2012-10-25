package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class CatchClause extends Statement {

    FormalParameter parameter;
    Statement statement;

    public CatchClause(FormalParameter parameter, Statement statement) {
        this.parameter = parameter;
        this.statement = statement;
    }
}
