package oop.tree.statements;

import java.util.List;
import java.util.ArrayList;

public class SwitchClause extends Statement {

    Expression caseClauseExpression;
    List<DeclarationOrStatement> caseClauseStatements;
    List<DeclarationOrStatement> defaultClause;

    public SwitchClause(Expression exp, List<DeclarationOrStatement> caseClauseStatements, List<DeclarationOrStatement> defaultClause) {
        caseClauseExpression = exp;
        this.caseClauseStatements = caseClauseStatements;
        this.defaultClause = defaultClause;
    }
}
