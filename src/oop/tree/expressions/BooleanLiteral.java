package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

public class BooleanLiteral extends Literal {

    public String value;

    public BooleanLiteral(String value) {
        this.value = value;
        type = BooleanT.TYPE;
    }
}
