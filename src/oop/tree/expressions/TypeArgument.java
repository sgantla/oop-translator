package oop.tree.expressions;

import java.util.List;

public class TypeArgument extends Node {

    List<Type> typeList;

    public TypeArgument(List<Type> typeList) {
        this.typeList = typeList;
    }
}
