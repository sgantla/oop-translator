package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class FloatingPointLiteral extends Literal {

    String value;
    
    public FloatingPointLiteral(String value) {
        this.value = value;
        type =  new FloatT(NumberT.Kind.DOUBLE);
    }
}
