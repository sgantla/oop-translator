package oop.tree.statements;

import java.util.List;
import java.util.ArrayList;

import oop.tree.expressions.*;
import oop.tree.*;

import java.util.*;
import java.io.*;

public class SwitchClause extends Statement {

	public Expression caseClauseExpression;
    public List<DeclarationOrStatement> caseClauseStatements;
    public List<DeclarationOrStatement> defaultClause;


    public SwitchClause(Expression exp, List<Statement> caseClauseStatements, List<Statement> defaultClause) {
        caseClauseExpression = exp;
        this.caseClauseStatements = caseClauseStatements;
        this.defaultClause = defaultClause;
    }
}
