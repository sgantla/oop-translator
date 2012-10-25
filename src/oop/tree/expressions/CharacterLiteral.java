package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class CharacterLiteral extends Literal {

    public String value;
    
    public CharacterLiteral(String value) {
        this.value = value;
        type = new IntegerT(NumberT.Kind.CHAR);
    }
}
