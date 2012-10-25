package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class SelectionExpression extends UnaryExpression {
	// Name of the field that you're accessing
    public String name;

    public SelectionExpression(Expression expression, String name) {
        this.expression = expression; 
        this.name = name;
    }
}
