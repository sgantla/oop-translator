package oop.tree; 

import oop.preprocessor.*; 
import oop.translatorTree.*; 
import oop.translator.*; 
import oop.tree.*; 

import java.util.*;
import java.io.*;

/* Iterates through Pod Tree. Returns C++ AST root of each pod/class */
public class PodIterator {
    private List<CompilationUnitPod> childCompilationUnitPods = new ArrayList<CompilationUnitPod>();
	private List<CompilationUnitPod> remainingPodIterations = new ArrayList<CompilationUnitPod>();
	private CompilationUnitPod rootPod;
	
	public PodIterator(CompilationUnitPod root) {
		remainingPodIterations.add(root);
		//traversePodTree(root); 			// Ensures traversal is successful
	}

	/** A depth-first recursive traversal of the Pod Tree */
	public void traversePodTree(CompilationUnitPod currentPod) {
		CompilationUnitTranslator cppAstRoot = currentPod.getCppCompilationUnit(); ; 
		currentPod.print(); 
		
		List<CompilationUnitPod> children = currentPod.getChildren();
		for (CompilationUnitPod child : children) {
			if (child != null) {
				child.print(); 
				cppAstRoot = child.getCppCompilationUnit(); 
				//System.out.println("Class Declarations : " + (cppAstRoot.getClassDeclaration()).toString()); 
				traversePodTree(child);
			}
		}
	}
	
	public CompilationUnitPod next() {
		try {
			CompilationUnitPod nextPod = remainingPodIterations.remove(0);
			remainingPodIterations.addAll(nextPod.getChildren());
			return nextPod;
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("There are no more pods in this tree. Exiting Program..");
			System.exit(0);				// TODO: This seems ugly. May cause issues. 
		}
		
		CompilationUnitPod nullPod = new CompilationUnitPod(); 
		return nullPod; 
	}
	
	public boolean hasNext() {
		boolean result = (remainingPodIterations.isEmpty()) ? false : true;
		return result; 
	}
	
	/*public static void main(String[] args) throws Exception { 
		//System.out.println("hi");
		rootPod = Preprocessor.process("/Users/ishitaaloni/Documents/Senior Year 2012-2013/Object Oriented Programming/oop-translator/src/oop/printer/Test.java");
		PodIterator p = new PodIterator(rootPod); 
		
	}*/

}
