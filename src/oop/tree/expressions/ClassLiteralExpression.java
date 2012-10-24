package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class ClassLiteralExpression extends Literal {

    Type type;
    
    public ClassLiteralExpression(Type type) {
        this.type = type;
    }
}
