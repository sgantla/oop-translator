package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class IntegerLiteral extends Literal {

    public String value;
    
    public IntegerLiteral(String value) {
        this.value = value;
        type = new IntegerT(NumberT.Kind.INT);
    }
}
