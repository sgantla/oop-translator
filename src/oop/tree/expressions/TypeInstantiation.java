package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

public class TypeInstantiation extends CNode {

    String typeInstantiation;
    TypeArgument typeArgument; //opt

    public TypeInstantiation(String typeInstantiation, TypeArgument typeArgument) {
        this.typeInstantiation = typeInstantiation;
        this.typeArgument = typeArgument;
    }
}
