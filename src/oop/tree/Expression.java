package oop.tree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public abstract class Expression extends Node {

    String opeartor;
    Type returnType;
    // this should be set in the constructor based on what the arguments are
    // ie: additive expression will return an int if its expressions both return ints
    public abstract Type getReturnType();
}
