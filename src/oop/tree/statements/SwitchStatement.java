package oop.tree.statements;

import java.util.List;

public class SwitchStatement extends Statement {

    Expression expression;
    List<SwitchClause> switchClause;

    public SwitchStatement(Expression exp, List<SwitchClause> switchClause) {
        expression = exp;
        this.switchClause = switchClause;
    }
}
