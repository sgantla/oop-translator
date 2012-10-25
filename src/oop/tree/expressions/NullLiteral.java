package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class NullLiteral extends Literal {

    String value;
    
    public NullLiteral() {
        this.value = "";
    }
}
