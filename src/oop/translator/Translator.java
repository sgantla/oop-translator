package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.expressions.*;
import oop.tree.CNode;
import oop.tree.*;

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
    private static Map<ClassT, List<Expression>> unresolvedExpressions = new HashMap<ClassT, List<Expression>>();
    private static ClassT currentClass;
    
    public static void setCurrentPod(CompilationUnitPod pod) {
	currentPod = pod;
    }
    
    public static void reportInheritanceData(InheritanceData data) {
	currentPod.setInheritanceData(data);
    }
    
    /* TODO: Figure out how we're handling Library classes, because we're going to need to return their inheritance information as well. */
    public static InheritanceData retrieveInheritanceData() {
	CompilationUnitPod parentPod = currentPod.getParentPod();
	if (parentPod != null && !parentPod.isLibraryClass()) {
	    return parentPod.getInheritanceData().deepCopy();
	} else {
	    return new InheritanceData();
	}	
    }
    
    public static void registerUnresolvedExpression(Expression expr) {
	ClassT classType = currentPod.getClassType();
	if (unresolvedExpressions.containsKey(classType)) {
	    unresolvedExpressions.get(classType).add(expr);
	} else {
	    List<Expression> expressions = new ArrayList<Expression>();
	    expressions.add(expr);
	    unresolvedExpressions.put(classType, expressions);
	}
    }
    
    public static ClassT getClassType() {
	return currentPod.getClassType();
    }
    
    public static String getInstanceFieldStructTypeName(ClassT classType) {
	return "__" + classType.getName() + "_FT";
    }
    public static String getInstanceFieldStructDeclarator(ClassT classType) {
	return "__" + classType.getName() + "_ftable";
    }
    
    public static void reportMethodModifiers(MethodT method, List<Modifiers> modifiers) {
	method.addAttribute(new Attribute("modifiers", modifiers));
	
    }
    
    public static void reportFieldModifiersAndLocation(Type type, List<Modifiers> modifiers, Location location) {
	type.addAttribute(new Attribute("modifiers", modifiers));
	type.addAttribute(new Attribute("location", location));
    }
  
    public static Type resolvePrimaryIdentifier(String identifier, String scopeName, Location location) {
	return null;
    }
    
    public static MethodT resolveCallExpression(Type callee, String name, List<Type> argTypes) {
	return null;
    }
    
    public static Type resolveSelectionExpression(Type selectee, String fieldName) {
	return null;
    }
    
    // TODO: fill out this method. Is there a limit to length of a method name in c++?
    public static String getMangledMethodName(MethodT conflictingMethod) {
	return conflictingMethod.getName(); 
    }
    
    public static MethodT resolveMethodType(CNode method) {
	
	String scopeName = method.getScopeName();
	SymbolTable.Scope scope = masterSymbolTable.getScope(scopeName);
	SymbolTable.Scope classScope = scope.getParent();
	
	/* Under the class scope, there is both a symbol that points to a method definition,
	   as well as a nested scope for that method. They have the same name, so we can use the scope name as a symbol here */
	Object value = classScope.lookupLocally(Utilities.unqualify(scopeName)); 
	if (value instanceof MethodT) {
	    return (MethodT) value;
	}
	
	return null;
    }
    
    public static Type resolveDeclaratorType(String declarator, CNode node) {
	
	// Find closest scope
	String qualifiedScopeName = null;
	while (node != null && ((qualifiedScopeName = node.getScopeName()) == null)) {
	    node = node.getParent();
	}
	
	// Look for declarator definition if scope was found
	if (qualifiedScopeName != null) {
	    SymbolTable.Scope scope = masterSymbolTable.getScope(qualifiedScopeName);
	    Object value = scope.lookupLocally(declarator);
	    if (value instanceof Type) {
		return (Type) value;
	    }
	}
	
	return null;	
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

    // TODO: We will need to call this whenever we MAY have to make a cast. This will determine
    // if a cast is necessary and return a cast expression that will solve the case. Note that
    // with downcasts we need to add a throws clause.
    /*public static Expression castCheck() {
    	return; 
    }*/
    
       
    private static void firstPass(CompilationUnitPod currentPod) {
        CompilationUnitTranslator cppAstRoot = new CompilationUnitTranslator(null);

        // TODO: What if it is a library class? Don't we still need to process it?
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
    
    private static void resolveExpressions() {
	for (ClassT classType : unresolvedExpressions.keySet()) {
	    currentClass = classType;
	    for (Expression expr : unresolvedExpressions.get(classType)) {
		expr.getReturnType();
	    }
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
	resolveExpressions();
    }
}
