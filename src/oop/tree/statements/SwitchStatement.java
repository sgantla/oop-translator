package oop.tree.statements;

import oop.tree.expressions.*;
import xtc.tree.*;

import java.util.List;

public class SwitchStatement extends Statement {

    public Expression expression;
    public List<SwitchClause> switchClause;

    public SwitchStatement(Expression exp, List<SwitchClause> switchClause) {
        expression = exp;
        this.switchClause = switchClause;
    }
}
