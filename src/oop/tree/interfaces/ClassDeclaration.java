package oop.tree.interfaces;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;

public interface ClassDeclaration extends CppAstNode {
    List<Modifier> getModifiers();
    String getClassName();
    List<String> getExtensions();
    ClassBody getClassBody(); 
}