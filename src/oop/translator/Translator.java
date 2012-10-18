package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;
import xtc.Constants;

import java.util.*;
import java.io.*;

public class Translator {

    private static CompilationUnitPod rootPod;
    private static CompilationUnitPod currentPod;
    private static SymbolTable masterSymbolTable;
    
    public static void setCurrentPod(CompilationUnitPod pod) {
	currentPod = pod;
    }
    
    public static void reportInheritedData(InheritedData data) {
	currentPod.setInheritedData(data);
    }
    
    public static InheritedData retrieveInheritedData() {
	CompilationUnitPod parentPod = currentPod.getParentPod();
	if (parentPod != null && !parentPod.isLibraryClass()) {
	    return parentPod.getInheritedData().deepCopy();
	} else {
	    return new InheritedData();
	}	
    }
    
    public static ClassT getClassType () {
	return currentPod.getClassType();
    }
    
    public static String mangleMethodName(MethodT conflictingMethod, ClassT classType) {
	return conflictingMethod.getName(); 
    }
    
    public static String mangleFieldName(String conflictingDeclarator, ClassT classType) {
	String unqualifiedClassName = classType.getName();
	return conflictingDeclarator + "__" + unqualifiedClassName;
    }
    
    public static MethodT resolveMethodType(MethodDeclarationTranslator method) {
	return null; // not yet implemented
    }
    
    public static Type resolveDeclaratorType(String declarator, TranslatorNode node) {
	return null; // not yet implemented
    }
    
    private static ClassT getClassTypeFromFileScope(String fileScopeName, String qualifiedClassName) {
	SymbolTable.Scope scope = masterSymbolTable.getScope(fileScopeName);
	
	for (Iterator<String> symbolIter = scope.symbols(); symbolIter.hasNext();) {
	    String symbol = symbolIter.next();
	    Object value = scope.lookupLocally(symbol);
	    
	    if (value instanceof ClassT) {
		ClassT classType = (ClassT) value;
		if (classType.getQName().equals(qualifiedClassName)) {
		    return classType;
		}
	    }
	}
	
	return null;
    }
       
    private static void firstPass(CompilationUnitPod currentPod) {
        CompilationUnitTranslator cppAstRoot = new CompilationUnitTranslator(null);

        if (!currentPod.isLibraryClass()) {
	    Translator.setCurrentPod(currentPod);
	    
	    String qualifiedClassName = currentPod.getQualifiedClassName();
	    Node javaAstRoot = currentPod.getJavaAst();
	    String scopeName = javaAstRoot.getStringProperty(Constants.SCOPE);
	    currentPod.setClassType(getClassTypeFromFileScope(scopeName, qualifiedClassName));
	  
	    cppAstRoot.initialize(javaAstRoot);
	    currentPod.setCppCompilationUnit(cppAstRoot);
        }

        List<CompilationUnitPod> children = currentPod.getChildren();
        for (CompilationUnitPod child : children) {
            firstPass(child);
        }
    }
    
    public static void main(String[] args) throws Exception {
	if (args.length == 0) {
	    System.out.println("Error: no input file.");
	    return;
	}
	
	translate(args[0]);
    }
    public static void translate(String sourceFileName) throws Exception {
	rootPod = Preprocessor.process(sourceFileName);
	masterSymbolTable = Preprocessor.getMasterSymbolTable(rootPod);	
	firstPass(rootPod);
	
    }
}