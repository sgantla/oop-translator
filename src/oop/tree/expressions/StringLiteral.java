package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class StringLiteral extends Literal {

    public String value;
    
    public StringLiteral(String value) {
        this.value = value;
    }
}
