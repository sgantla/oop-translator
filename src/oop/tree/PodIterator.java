package oop.tree; 

import oop.preprocessor.*; 
import oop.translatorTree.*; 
import oop.translator.*; 
import oop.tree.*; 

import java.util.*;
import java.io.*;

/* Iterates through Pod Tree. Returns C++ AST root of each pod/class */
public class PodIterator {

	private static CompilationUnitPod rootPod;
	private static CompilationUnitPod currentPod;
	private static CompilationUnitTranslator cppAstRoot; 
	
	private List<CompilationUnitPod> childCompilationUnitPods = new ArrayList<CompilationUnitPod>();

	public PodIterator(CompilationUnitPod root) {
		traversePodTree(root); 
	}

	/** A depth-first recursive traversal of the Pod Tree */
	public void traversePodTree(CompilationUnitPod currentPod) {
		cppAstRoot = currentPod.getCppCompilationUnit(); 
		//traverseCppAST(cppAstRoot); 
		currentPod.print(); 
		
		List<CompilationUnitPod> children = currentPod.getChildren();
		for (CompilationUnitPod child : children) {
			if (child != null) {
				child.print(); 
				cppAstRoot = child.getCppCompilationUnit(); 
				//System.out.println("Class Declarations : " + (cppAstRoot.getClassDeclaration()).toString()); 
				//traverseCppAST(cppAstRoot); 
				traversePodTree(child);
			}
		}
	}

	//public void traverseCppAST(CompilationUnitTranslator cppAstRoot) {
		//currentCppAstNode = cppAstRoot; 
		//System.out.println("Class Declarations : " + (cppAstRoot.getClassDeclaration()).toString()); 

	//}
	
	public static void main(String[] args) throws Exception { 
		//System.out.println("hi");
		rootPod = Preprocessor.process("/Users/ishitaaloni/Documents/Senior Year 2012-2013/Object Oriented Programming/oop-translator/src/oop/printer/Test.java");
		PodIterator p = new PodIterator(rootPod); 
		
	}

}

