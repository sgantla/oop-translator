package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

/* Initializes the C++ Compliation units, with namespace, any using declarations.
 *
 * Then initializes ClassDeclaration with name, modifiers, copies parent vtable.
 *
 * Initializes MethodDeclarations, FieldDeclarations, ConstructorDeclaration, 
 * handles name mangling of any of these declarations, then finish constructing
 * vtable for this class.
 */

public class PhaseOne {
    private CompilationUnitPod root;
    
    public PhaseOne(CompilationUnitPod rootPod) {
        root = rootPod;
    }
    
    public void translate() {
        translate(root);    
    }
    
    private void translate(CompilationUnitPod currentPod) {
        CompilationUnitTranslator cppAstRoot = new CompilationUnitTranslator(null);
        Translator.setCurrentPod(currentPod);
        // get ClassT using the name and the scope of the ast root
        cppAstRoot.initialize(currentPod.getJavaAst());
        currentPod.setCppCompilationUnit(cppAstRoot);
        
        List<CompilationUnitPod> children = currentPod.getChildren();
        for (CompilationUnitPod child : children) {
            translate(child);
        }
    }
}
