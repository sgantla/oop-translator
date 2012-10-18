package oop.translator;

/* Initializes the C++ Compliation units, with namespace, any using declarations.
 *
 * Then initializes ClassDeclaration with name, modifiers, copies parent vtable.
 *
 * Initializes MethodDeclarations, FieldDeclarations, ConstructorDeclaration, 
 * handles name mangling of any of these declarations, then finish constructing
 * vtable for this class.
 */
 
import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class PhaseZero
{
     
    public PhaseZero(CompilationUnitPod rootPod)
    {

    }
}
