package oop.tree.expressions;

import oop.tree.*;
import oop.tree.interfaces.*;
import oop.tree.statements.*;
import oop.translator.*;
import oop.translatorTree.*;

import java.util.List;

public class TypeName extends Node {

    String primitiveType;
    List<String> qualifiedIdentifiers;
    List<TypeInstantiation> typeInstantiations;

    public TypeName(String primitiveType, List<String> qualifiedIdentifiers, List<TypeInstantiation> typeInstantiations) {
        this.primitiveType = primitiveType;
        this.qualifiedIdentifiers = qualifiedIdentifiers;
        this.typeInstantiations = typeInstantiations;
    }
}
