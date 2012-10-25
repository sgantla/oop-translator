package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;

import xtc.tree.*;
import xtc.Constants;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class ClassBodyTranslator extends ClassBody {
	
    private List<FieldDeclarationTranslator> instanceFieldDeclarations = new ArrayList<FieldDeclarationTranslator>();
    private List<FieldDeclarationTranslator> staticFieldDeclarations = new ArrayList<FieldDeclarationTranslator>();
    
    private List<MethodDeclarationTranslator> instanceMethodDeclarations = new ArrayList<MethodDeclarationTranslator>();
    private List<MethodDeclarationTranslator> staticMethodDeclarations = new ArrayList<MethodDeclarationTranslator>();
    
    private List<ConstructorDeclarationTranslator> constructorDeclarations = new ArrayList<ConstructorDeclarationTranslator>();
    private List<Initializer> initializers = new ArrayList<Initializer>();
    
    private List<Block> instanceBlockDeclarations;
    private List<Block> staticBlockDeclarations;

    private VTable vtable; 
    private InstanceFieldStructData instanceFieldStructs;
    private ClassT classType;
    
    public ClassBodyTranslator(CNode parent) {
	setParent(parent);
	setName(CppAstUtil.NodeName.ClassBody);
    }
    public ClassBodyTranslator() {
	setName(CppAstUtil.NodeName.ClassBody);
    }
    
    /* ClassBody Members */
    public List<Declaration> getDeclarations() {

    }    
    
    /* TranslatorNode Members */
    public void initialize(Node n) {  
    
    	// Record symbol table scope
	String scopeName = n.getStringProperty(Constants.SCOPE);
	setScopeName(scopeName);
	
	// Get field, method, constructor data of parent to implement inheritance
	InheritanceData inheritanceData = Translator.retrieveInheritanceData();  
	vtable = inheritanceData.getVirtualMethodTable();
	instanceFieldStructs = inheritanceData.getInstanceFieldStructs();
	
	classType = Translator.getClassType():
	
	// Iterate through child nodes to, initializing field, method, and constructor declarations
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

    	// Add all newly declared instance fields of this class to a struct 
	InstanceFieldStructData instanceFieldStruct = new InstanceFieldStructData();
	
	instanceFieldStruct.setClassType(Translator.getClassType());
	instanceFieldStruct.setTypeName(Translator.getInstanceFieldStructTypeName(classType));
	instanceFieldStruct.setDeclarator(Translator.getInstanceFieldStructDeclarator(classType));
	instanceFieldStruct.setFieldDeclarations(fieldDeclarations);
	
	// Add new the newly-created struct to the full list of this classes fields (those inherited from parent + its own)
	instanceFieldStructs.add(instanceFieldStruct);

	// Report back with the modified inheritance data for use by any subclasses
	Translator.reportInheritanceData(inheritanceData);
    }
    
    
    private void initializeFieldDeclaration(Node fieldDecNode) {
    
	// Split the java AST node into such that each one has only one declarator
	List<Node> singleDeclarations = JavaAstUtil.splitFieldDeclarationByDeclarator(fieldDecNode);
	
	for (Node declaration : singleDeclarations) {
	    FieldDeclarationTranslator fd = new FieldDeclarationTranslator(this);
	    fd.initialize(declaration);
	    
    	    if (fd.isStatic()) {
		staticFieldDeclarations.add(fd);
    	    } else {
		if (fd.hasExpression()) {
		    Expression expr = fd.removeExpression();
		    initializers.add(new Initializer(fd, expr));
		} else {
		    initializers.add(new Initializer(fd, null));
		}
		instanceFieldDeclarations.add(fd);
    	    }
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

	if (md.isStatic()) {
	    staticMethodDeclarations.add(md);
	} else if (md.isPrivate()) { 
	    instanceMethodDeclarations.add(md);
	} else {
	    instanceMethodDeclarations.add(md);
	    
	    int i = vtable.indexOf(methodName);
	    if (i >= 0) { // If the method name matches another name in the method table inherited from parent
	    
		String newMethodName = Translator.getMangledMethodName(methodType); // Will return the same name until we need to implement overloading
		
		if (!newMethodName.equals(methodName)) { 
		    md.setMethodName(newMethodName);
		    methodType = md.getMethodType();
		    
		    int j = vtable.indexOf(methodName);
		    if (j >= 0) { 
			vtable.set(j, new MethodData(methodType, classType)); // Overriding a method that was previously mangled in the parent class
		    } else {
			vtable.add(new MethodData(methodType, classType)); // First time that method with this signature was mangled
		    }
		} else {
		    vtable.set(i, new MethodData(methodType, classType)); // Overriding a method that was not mangled in the parent class
		}
	    } else {
		vtable.add(new MethodData(methodType, classType)); // First time this method signature has been seen
	    }
	}
    }
    
    private void initializeConstructorDeclaration(Node constructorDecNode) {
	ConstructorDeclarationTranslator cd = new ConstructorDeclarationTranslator(this);
	cd.initialize(constructorDecNode);
	constructorDeclarations.add(cd);
    }
    
}
