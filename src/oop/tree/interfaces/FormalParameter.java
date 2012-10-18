package oop.tree.interfaces;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;

public interface FormalParameter extends CppAstNode {

    Modifier getModifiers();
    Type getType();
    String getName();
}

