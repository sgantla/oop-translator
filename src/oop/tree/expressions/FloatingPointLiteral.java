package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class FloatingPointLiteral extends Literal {

    public String value;
    
    public FloatingPointLiteral(String value) {
        this.value = value;
        type =  new FloatT(NumberT.Kind.DOUBLE);
    }
}
