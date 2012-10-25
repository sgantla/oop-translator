 package oop.preprocessor;

import java.lang.StringBuilder;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import xtc.tree.Node;
import xtc.tree.GNode;
import xtc.util.SymbolTable;
import xtc.Constants;

public class AstPreprocessorUtilities {
   
    /**
    * Splits an AST into multiple ASTs, one for each class declared. 
    * Warning: the non-class declaration children of the root node (e.g. imports, package declarations) are
    * NOT deeply copied. Modifying these nodes in any of the ASTs returned will affect them all
    * @param ast the AST to split
    * @return set of ASTs, each one representing one class (i.e. has only one class declaration)
    */
    public static Set<Node> splitAstByClass(Node ast) {
	Set<Node> classAsts = new HashSet<Node>();
	
	if (ast.getName() != "CompilationUnit") {
	    return classAsts;
	}
	
	List<Object> nonClassDeclarationChildren = new ArrayList<Object>();
	List<Object> classDeclarationChildren = new ArrayList<Object>();
	
	for (Object child : ast) {
	    if (child instanceof GNode && ((GNode)child).getName() == "ClassDeclaration") {
		classDeclarationChildren.add(child);
	    } else {
		nonClassDeclarationChildren.add(child);
	    }
	}
	
	String scopeName = ast.getStringProperty(Constants.SCOPE);
	
	if (classDeclarationChildren.size() == 1) {
	    classAsts.add(ast);
	} else {
	    for (Object classDeclaration : classDeclarationChildren) {
		GNode node = GNode.create("CompilationUnit");
		node.setProperty(Constants.SCOPE, scopeName);
		node.addAll(nonClassDeclarationChildren);
		node.add(classDeclaration);
		
		classAsts.add(node);
	    }
	}
	
	return classAsts;
    }

    /**
    * Gets the qualified class name of a class in an AST.
    * @param ast an AST with only one class declaration
    * @return the fully qualified class name
    */
    public static String getQualifiedClassName(Node ast) {
	String className = "";
	String packageName = "";

	if (ast.getName().equals("CompilationUnit")) {
	    for (Object child : ast) {
		if (child instanceof GNode) {
		    GNode childNode = (GNode) child;
		    if (childNode.getName().equals("ClassDeclaration")) {
			
			className = (String) childNode.get(1);
			
		    } else if (childNode.getName().equals("PackageDeclaration")) {
			
			GNode qualifiedIdentifierNode = (GNode) childNode.get(1);		
			packageName = getQualifiedIdentifierString(qualifiedIdentifierNode);
		    }
		}
	    }
	}
	
	return (packageName == "") ? className : (packageName + "." + className);
    }
    
    /**
    * Gets the parent name of a class in an AST.
    * @param ast an AST with only one class declaration
    * @return the parent name. may be qualified or unqualified
    */
    public static String getParentName(Node ast) {
	if (ast.getName().equals("CompilationUnit")) {
	    for (Object child : ast) {
		if (child instanceof GNode) {
		    GNode childNode = (GNode) child;

		    if (childNode.getName().equals("ClassDeclaration")) {
			GNode extensionNode = (GNode) childNode.get(3);
			if (extensionNode == null) {
			    return null;
			}
			
			GNode typeNode = (GNode) extensionNode.get(0);
			GNode qualifiedIdentifierNode = (GNode) typeNode.get(0);
			
			return getQualifiedIdentifierString(qualifiedIdentifierNode);
		    }
		}
	    }
	}

	return null;
    }
   
    /**
    * Gets the import declaration strings for an AST.
    * @param ast an AST 
    * @return set of all import declarations for the ast
    */
    public static Set<String> getImports(Node ast) {
	Set<String> importSet = new HashSet<String>();

	if (ast.getName().equals("CompilationUnit")) {
	    for (Object child : ast) {
		if (child instanceof GNode) {
		    GNode childNode = (GNode) child;
		    if (childNode.getName().equals("ImportDeclaration")) {
			GNode qualifiedIdentifierNode = (GNode) childNode.get(1);
			String importName = getQualifiedIdentifierString(qualifiedIdentifierNode);
			    
			if (childNode.get(2) != null) {
			    importName += ".*";
			}

			importSet.add(importName);
		    }
		}
	    }
	}  
	
	return importSet;
    }

    /**
    * Gets the qualified itentifier string from a QualifiedIdentifier node.
    * @param qualifiedIdentifierNode the qualified identifier node
    * @return the qualified identifier string, with periods as delimeters
    */
    private static String getQualifiedIdentifierString(GNode qualifiedIdentifierNode) {
    
	
	if (!qualifiedIdentifierNode.getName().equals("QualifiedIdentifier") ||
		qualifiedIdentifierNode.isEmpty()) {
	    return "";
	}
	
	StringBuilder sb = new StringBuilder();
	for (Object o : qualifiedIdentifierNode) {
	    sb.append((String)o);
	    sb.append(".");
	}

	return sb.substring(0, sb.length() - 1).toString();
    }
}