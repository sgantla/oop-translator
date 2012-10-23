package oop.tree.expressions;

import java.util.List;

public class TypeName extends Node {

    String primitiveType;
    List<String> qualifiedIdentifiers;
    List<TypeInstantiation> typeInstantiations;

    public TypeName(String primitiveType, List<String> qualifiedIdentifiers, List<TypeInstantiation> typeInstantiations) {
        this.primitiveType = primitiveType;
        this.qualifiedIdentifiers = qualifiedIdentifiers;
        this.instantiatedTypes = typeInstantiations;
    }
}
