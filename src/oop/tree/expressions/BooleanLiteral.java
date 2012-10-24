package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.BooleanT;

public class BooleanLiteral extends Literal {

    String value;

    public BooleanLiteral(String value) {
        this.value = value;
    }
}
