package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;
import xtc.Constants;

import java.util.*;
import java.io.*;

public class CompilationUnitTranslator extends CppCompilationUnit {

	private class Input {
		String packageDec;      
		List<String> imports = new ArrayList<String>(); 
	}
	private class Output {
		List<String> namespaces = new ArrayList<String>();
		List<String> usingDeclarations = new ArrayList<String>();
		ClassDeclarationTranslator classDeclaration;
	}
	
	private Input java = new Input();
	private Output cpp = new Output();

	public CompilationUnitTranslator(CNode parent) {
	    setParent(parent);
	    setName(CppAstUtil.NodeName.CompilationUnit);
	}
	public CompilationUnitTranslator() {
	    setName(CppAstUtil.NodeName.CompilationUnit);
	}


	/* CompilationUnit Members */ 
	public List<String> getNameSpaceDeclarations() {
		return cpp.namespaces;
	}
	public List<String> getUsingDeclarations() {
		return cpp.usingDeclarations;
	}
	public ClassDeclaration getClassDeclaration() {
		return cpp.classDeclaration;
	}

	/* TranslatorNode Members */
	public void initialize(Node n) {
		String scopeName = n.getStringProperty(Constants.SCOPE);
		setQualifiedScopeName(scopeName);

		Node packageNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.PackageDeclaration);
		if (packageNode != null) {
			java.packageDec = JavaAstUtil.extractString(packageNode);
		}

		for (Node importNode : JavaAstUtil.getChildrenByName(n, JavaAstUtil.NodeName.ImportDeclaration)) {
			java.imports.add(JavaAstUtil.extractString(importNode));
		}

		Node classDecNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.ClassDeclaration);
		cpp.classDeclaration = new ClassDeclarationTranslator(this);
		cpp.classDeclaration.initialize(classDecNode);
	}
}