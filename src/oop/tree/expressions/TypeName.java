package oop.tree.expressions;

import java.util.List;

public class TypeName {

    String primitiveType;
    List<String> qualifiedIdentifiers;
    List<TypeInstantiation> instantiatedTypes;

    public TypeName(String primitiveType, List<String> qualifiedIdentifiers, List<TypeInstantiation> instantiatedTypes) {
        this.primitiveType = primitiveType;
        this.qualifiedIdentifiers = qualifiedIdentifiers;
        this.instantiatedTypes = instantiatedTypes;
    }
}
