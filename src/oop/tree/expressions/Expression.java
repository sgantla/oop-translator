package oop.tree.expressions;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public abstract class Expression extends Expression {

    String operator;
    Type returnType;

    public Type getReturnType() { }
}
