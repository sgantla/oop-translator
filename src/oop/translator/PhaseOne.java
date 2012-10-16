package oop.translator;

import java.util.List;
import java.util.ArrayList;

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
        CompilationUnitTranslator translator = new CompilationUnitTranslator(null);
        translator.initialize(currentPod.getJavaAST());
        currentPod.setCppCompilationUnit(translator);
        
        List<CompilationUnitPod> children = currentPod.getChildren();
        for (CompilationUnitPod child : children) {
            translate(child);
        }
    }
}
