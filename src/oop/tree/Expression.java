package oop.tree;

public abstract class Expression extends Node {

    // this should be set in the constructor based on what the arguments are
    // ie: additive expression will return an int if its expressions both return ints
    public abstract Type getReturnType();
}
