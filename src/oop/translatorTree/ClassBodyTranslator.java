package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;


import xtc.tree.*;
import xtc.Constants;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class ClassBodyTranslator extends TranslatorNode 
    implements ClassBody {
	
    private List<FieldDeclarationTranslator> fieldDeclarations = new ArrayList<FieldDeclarationTranslator>();
    
    private List<MethodDeclarationTranslator> privateMethodDeclarations = new ArrayList<MethodDeclarationTranslator>();
    private List<MethodDeclarationTranslator> staticMethodDeclarations = new ArrayList<MethodDeclarationTranslator>();
    private List<MethodDeclarationTranslator> virtualMethodDeclarations = new ArrayList<MethodDeclarationTranslator>();
    private List<MethodData> virtualMethodTable;
    
    private List<ConstructorDeclarationTranslator> constructorDeclarations = new ArrayList<ConstructorDeclarationTranslator>();
    private List<InitializationField> initializationList = new ArrayList<InitializationField>();
    
    private InheritedData inheritedData;
    
    public ClassBodyTranslator(TranslatorNode parent) {
	    super(parent);
    }
    
    /* ClassBody Members */
    public List<Declaration> getDeclarations() {
	    List<Declaration> retList = new ArrayList<Declaration>();
	    retList.addAll(fieldDeclarations);
	    retList.addAll(privateMethodDeclarations);
	    retList.addAll(staticMethodDeclarations);
	    retList.addAll(virtualMethodDeclarations);
	    retList.addAll(constructorDeclarations);
	    
	    return retList;
    }    
    
    /* CppAstNode Members */
    public CppAstUtil.NodeName getNodeType () {
	    return CppAstUtil.NodeName.ClassBody;
    }

    /* TranslatorNode Members */
    public void initialize(Node n) {  
    
    	// Record symbol table scope
	String scopeName = n.getStringProperty(Constants.SCOPE);
	setQualifiedScopeName(scopeName);
	
	// Get field, method, constructor data of parent to implement inheritance
	inheritedData = Translator.retrieveInheritedData();  
	
    	for (Object child : n) {
    	    if (child instanceof Node) {
		Node childNode = (Node) child;
		JavaAstUtil.NodeName childName = JavaAstUtil.NodeName.valueOf(childNode.getName());
		if (childName == JavaAstUtil.NodeName.FieldDeclaration) {
		    initializeFieldDeclaration(childNode);
		} else if (childName == JavaAstUtil.NodeName.MethodDeclaration) {
		    initializeMethodDeclaration(childNode); // The java AST will be simplified, so both methods and constructors will appear as MethodDeclaration
		} 
	    }
    	}

	finishFieldInitialization();
	finishMethodInitialization();
	//finishConstructorInitialization(); not fully implemented 
	
	// Report back with the modified data for use by any subclasses
	Translator.reportInheritedData(inheritedData);
    }
    
    private void initializeFieldDeclaration(Node fieldDecNode) {
    
	// Split the java AST node into such that each one has only one declarator
	List<Node> singleDeclarations = JavaAstUtil.splitFieldDeclarationByDeclarator(fieldDecNode);
	
	List<FieldDeclarationTranslator> parentFieldDeclarations = inheritedData.getFieldDeclarations();
	Set<String> parentDeclarators = new HashSet<String>();
	for (FieldDeclarationTranslator parentDeclaration : parentFieldDeclarations) {
	    parentDeclarators.add(parentDeclaration.getDeclarator());
	}
	
	for (Node declaration : singleDeclarations) {
	    FieldDeclarationTranslator fd = new FieldDeclarationTranslator(this);
	    fd.initialize(declaration);
	    
    	    String declarator = fd.getDeclarator();
    	    
    	    // If there is a naming conflict between parent and child field declarators
    	    if (parentDeclarators.contains(declarator)) { 
		String newDeclarator = Translator.mangleFieldName(declarator, Translator.getClassType());
		fd.setDeclarator(newDeclarator);
    	    }

	    // If declaration contains an expression (and isn't static), move the expression to the initialization list for the constructor
	    if (fd.hasExpression() && !fd.isStatic()) { 
		ExpressionTranslator expr = fd.removeExpression();
		initializationList.add(new InitializationField(fd, expr));
	    }
	    
	    // Save field declaration
	    fieldDeclarations.add(fd);
	}
    }
    
    private void initializeMethodDeclaration(Node methodDecNode) {
	
	// A null return value indicates a constructor
	if (methodDecNode.get(2) == null) {
	    initializeConstructorDeclaration(methodDecNode);
	    return;
	}
	
	MethodDeclarationTranslator md = new MethodDeclarationTranslator(this);
	md.initialize(methodDecNode);
	
	MethodT methodType = md.getMethodType();
	String methodName = methodType.getName();
	ClassT classType = Translator.getClassType();

	if (md.isPrivate()) {
	    privateMethodDeclarations.add(md);
	} else {
	    
	    List<MethodData> table;
	    if (md.isStatic()) {
		staticMethodDeclarations.add(md);
		table = inheritedData.getStaticMethodTable(); 
	    } else {
		virtualMethodDeclarations.add(md);
		table = inheritedData.getVirtualMethodTable(); 
	    }
	
	    int i = table.indexOf(new MethodData(methodName));
	    if (i >= 0) { // If the method name matches another name in the method table inherited from parent
	    
		String newMethodName = Translator.mangleMethodName(methodType, classType); // Will return the same name until we need to implement overloading
		
		if (!newMethodName.equals(methodName)) { 
		    md.setMethodName(newMethodName);
		    methodType = md.getMethodType();
		    
		    int j = table.indexOf(new MethodData(methodName));
		    if (j >= 0) { 
			table.set(j, new MethodData(methodType, classType)); // Overriding a method that was previously mangled in the parent class
		    } else {
			table.add(new MethodData(methodType, classType)); // First time that method with this signature was mangled
		    }
		} else {
		    table.set(i, new MethodData(methodType, classType)); // Overriding a method that was not mangled in the parent class
		}
	    } else {
		table.add(new MethodData(methodType, classType)); // First time this method signature has been seen
	    }
	}
    }
    
    private void initializeConstructorDeclaration(Node constructorDecNode) {
	ConstructorDeclarationTranslator cd = new ConstructorDeclarationTranslator(this);
	cd.initialize(constructorDecNode);
	constructorDeclarations.add(cd);
    }
    
    private void addStaticMethodReference(MethodData method) {
	// We need to have the subclass point to a static method defined in a parent class. Not yet implemented.
    }
    
    private void finishFieldInitialization() {
    
	// Add child field declarations after parent declarations
	List<FieldDeclarationTranslator> temp = new ArrayList<FieldDeclarationTranslator>();
	temp.addAll(inheritedData.getFieldDeclarations());
	temp.addAll(fieldDeclarations);
	
	// Set field declarations and inherited data to the full list of declarations
	fieldDeclarations = temp;
	inheritedData.setFieldDeclarations(temp);
    }
    
    private void finishMethodInitialization() {
    
	/* If the subclass has inherited static methods but not overriden them
	   We need to reference them somehow */
	List<MethodData> staticMethodTable = inheritedData.getStaticMethodTable(); 
	Set<String> staticMethodNames = new HashSet<String>();
	
	for (MethodDeclarationTranslator methodDec : staticMethodDeclarations) {
	    staticMethodNames.add(methodDec.getMethodName());
	}
	for (MethodData method : staticMethodTable) {
	    if (!staticMethodNames.contains(method.getMethodType().getName())) {
		addStaticMethodReference(method);
	    }
	}
	
	virtualMethodTable = inheritedData.getVirtualMethodTable(); 
    }
    
    private void finishConstructorInitialization() {
	List<InitializationField> temp = new ArrayList<InitializationField>();
	temp.addAll(inheritedData.getInitializationList());
	temp.addAll(initializationList);
	initializationList = temp;
	
	ConstructorDeclarationTranslator constructor;
	if (constructorDeclarations.size() > 0) {
	    constructor = constructorDeclarations.get(0);
	} else {
	    constructor = ConstructorDeclarationTranslator.newEmptyConstructor(Translator.getClassType(), this); 
	}
	constructor.setInitializationList(initializationList); 
	
	List<ConstructorData> constructorTable = inheritedData.getConstructorTable(); 
	
	if (constructorTable.size() > 0) {
	    ConstructorData parentConstructorData = constructorTable.get(0);
	    BlockTranslator parentConstructorBody = parentConstructorData.getBodyTranslator();
	    constructor.prependStatementBlock(parentConstructorBody); 
	}
	
	ConstructorData constructorData = new ConstructorData(constructor.getMethodType(), constructor.getConstructorBody());
	constructorTable.set(0, constructorData);

	inheritedData.setInitializationList(initializationList);
    }
}