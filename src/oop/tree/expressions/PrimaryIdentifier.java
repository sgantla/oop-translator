package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class PrimaryIdentifier extends Literal {

    public String name;

    public PrimaryIdentifier(String name) {
        this.name = name;
    }
}
