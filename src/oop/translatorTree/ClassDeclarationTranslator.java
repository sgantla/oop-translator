package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.tree.*;
import oop.tree.expressions.*;
import oop.tree.statements.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.Constants;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class ClassDeclarationTranslator extends ClassDeclaration {

	private class Input { 
		List<String> modifiers = new ArrayList<String>();	
		String className;
		String extension;
	}
	private class Output {
		List<Modifiers> modifiers = new ArrayList<Modifiers>();	
		List<String> extensions = new ArrayList<String>();	
		String className;
		ClassBodyTranslator classBody;
	}
	private Input java = new Input();
	private Output cpp = new Output();

	public ClassDeclarationTranslator(CNode parent) {
	    setParent(parent);
	    setName(CppAstUtil.NodeName.ClassDeclaration);
	}
	public ClassDeclarationTranslator() {
	    setName(CppAstUtil.NodeName.ClassDeclaration);
	}

	/* ClassDeclaration Members */
	public List<Modifiers> getModifiers() {
		return cpp.modifiers;
	}
	public String getClassName() {
		return cpp.className;
	}
	public List<String> getExtensions() {
		return cpp.extensions;
	}
	public ClassBody getClassBody() {
		return cpp.classBody;
	}

	/* TranslatorNode Members */
	public void initialize(Node n) {

		// Get extension (inheritance) info
		Node extensionNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.Extension);
		if (extensionNode != null) {
			java.extension = JavaAstUtil.extractString(extensionNode);
		}

		// Get class name
		java.className = n.getString(1);
		cpp.className = java.className;
		cpp.modifiers.add(Modifiers.PUBLIC); // All classes will be public 

		// Create ClassBodyTranslator child
		Node classBodyNode = JavaAstUtil.getChildByName(n, JavaAstUtil.NodeName.ClassBody);
		if (classBodyNode != null) {
			cpp.classBody = new ClassBodyTranslator(this);
			cpp.classBody.initialize(classBodyNode);
		}
	}
}