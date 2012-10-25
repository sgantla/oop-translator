package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class EqualityExpression extends BinaryExpression {

    public EqualityExpression(Expression left, String operator, Expression right) {
       leftExpression = left;
       rightExpression = right;
       this.operator = operator;
       returnType = BooleanT.TYPE;
    }
}
