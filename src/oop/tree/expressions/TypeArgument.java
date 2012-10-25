package oop.tree.expressions;

import oop.tree.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.type.*;

import java.util.List;

public class TypeArgument extends CNode {

    List<Type> typeList;

    public TypeArgument(List<Type> typeList) {
        this.typeList = typeList;
    }
}
