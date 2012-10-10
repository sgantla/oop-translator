package oop.preprocessor;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;

/**
* Class used to test the output of the preprocessor
*/
class PreprocessTester {

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

	// Print the entire tree
	rootPod.print();
    }
}