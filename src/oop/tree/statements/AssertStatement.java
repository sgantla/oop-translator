package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

public class AssertStatement extends Statement {

    Expression booleanExpression; //opt
    Expression valueExpression;

    public AssertStatement(Expression bExp, Expression vExp) {
        booleanExpression = bExp;
        valueExpression = vExp;
    }
}
