package oop.tree.expressions;

public class TypeInstantiation extends Node {

    String typeInstantiation;
    TypeArgument typeArguments; //opt

    public TypeInstantiation(String typeInstantiation, TypeArgument typeArgument) {
        this.typeInstantiation = typeInstantiation;
        this.typeArgument = typeArgument;
    }
}
