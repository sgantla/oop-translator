package oop.preprocessor;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;
import java.io.File;
import xtc.util.SymbolTable;
import java.io.StringWriter;
import xtc.tree.Printer;
import xtc.tree.*;
import xtc.Constants;

/**
* Class used to test the output of the preprocessor
*/
public class PreprocessTester {

    /**
    * Prints string representation of preprocessor output.
    * @param args the source file to preprocess (only first argument is preprocessed)
    */
    public static void main(String[] args) throws Exception {
	if (args.length < 1) {
	    System.out.println("Error: no input source file.");
	    return;
	}
	
	String sourceFileName = args[0];

	// Get the root of the class hierarchy tree for the source file and all its dependencies/library compilation units
	CompilationUnitPod rootPod = Preprocessor.process(sourceFileName);
    }
}