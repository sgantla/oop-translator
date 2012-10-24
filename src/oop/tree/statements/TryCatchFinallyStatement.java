package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

import java.util.List;

public class TryCatchFinallyStatement extends Statement {

    Statement statement;
    List<CatchClause> catchClauses;
    List<Statement> catchStatements; 

    public TryCatchFinallyStatement(Statement statement, List<CatchClause> catchClauses, List<Statement> catchStatements) {
        this.statement = statement;
        this.catchClauses = catchClauses; 
        this.catchStatements = catchStatements;
    }
}
