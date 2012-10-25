package oop.printer; 

import java.util.*; 
import java.io.*; 

import xtc.lang.JavaFiveParser;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.statements.*;
import oop.tree.expressions.*;
import oop.tree.PodIterator; 
import oop.printer.*; 

//import oop.tree.*; 

import xtc.type.*;
import xtc.tree.*; 
import xtc.util.*;


public class Test {

	public static void main(String[] args) throws Exception {
		CompilationUnitPod rootPod; 
		
		try {
			rootPod = Preprocessor.process("/Users/ishitaaloni/Documents/Senior Year 2012-2013/Object Oriented Programming/oop-translator/src/oop/printer/Test.java");
			PodIterator podIterator = new PodIterator(rootPod);
			
			while (podIterator.hasNext()) {
				CompilationUnitPod currentPod = podIterator.next();
				
				String className = currentPod.getQualifiedClassName();
				FileWriter headerOut = new FileWriter(className + ".h");
				BufferedWriter headerWriter = new BufferedWriter(headerOut); 
				
				FileWriter implementationOut = new FileWriter(className + ".cc");
				BufferedWriter implementationWriter = new BufferedWriter(implementationOut); 
				
				CppPrinter ccFileCppPrinter = new CppPrinter(implementationWriter, headerWriter, rootPod);

				// TODO: Iterate through each pod and get C++ AST Tree to output some C++ code
				// Variables to define:
				 
				 
				//out.write(c);   

				headerOut.close();
				implementationOut.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace(); 
			System.out.println("File not found.");     	
		}
	}
}