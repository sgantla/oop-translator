package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class CharacterLiteral extends Literal {

    String value;
    
    public CharacterLiteral(String value) {
        this.value = value;
    }
}
