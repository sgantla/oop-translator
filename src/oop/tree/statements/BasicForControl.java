package oop.tree.statements;

import oop.tree.*;
import oop.tree.expressions.*;

import xtc.type.*;

import java.util.List;

public class BasicForControl extends ForControl {
    
    List<Modifiers> modifiers; //opt, for the variable beiing initialized
    Type type; //opt, for the variable being initialized
    List<Statement> declarators; //opt, can initialize multiple variables in for loop
    List<Expression> declaratorExpressionList; //opt, expressions for initalizing
    Expression expression; //opt, boolean expression for when to terminate    
    List<Expression> expressionList; //opt, expressions for incrementing variables

    public BasicForControl (List<Modifiers> modifiers, Type type, List<Statement> declarators, List<Expression> declaratorExpressionList,
            Expression expression, List<Expression> expressionList) {

        this.modifiers = modifiers;
        this.type = type;
        this.declarators = declarators;
        this.declaratorExpressionList = declaratorExpressionList;
        this.expression = expression;
        this.expressionList = expressionList;
    }
}

