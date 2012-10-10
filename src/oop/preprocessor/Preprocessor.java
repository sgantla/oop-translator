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

/**
* Preprocesses source file to prepare for Java to C++ translation stage
*/
class Preprocessor {
   
    private static final String FULLY_QUALIFIED_ROOT_CLASS_NAME = "java.lang.Object";
    /**
    * Preprocesses a single input source file.
    * Resolves all external file and library dependencie, locates and parses all files
    * to generate ASTs and symbol tables. Determines fully qualified class names and parents for each class,
    * and then constructs class hierarchy tree.
    * @param sourceFileName the file path of the input source file
    * @return The {@link CompilationUnitPod} that encapsulates compilation unit data for the root of the tree (i.e. java.lang.Object)
    */
    public static CompilationUnitPod process(String sourceFileName) throws Exception {

	/* File path can be relative or absolute. Use canonical file to ensure absolute/unique path */
	File sourceFile = new File(sourceFileName).getCanonicalFile();

	/* Get list of possible input directories for use when trying to resolve external classes
	   Analogous to the "-classpath" flag for java/javac */
	List<File> inputDirs = getInputDirectories();
	    
	/* Get all external dependencies and their ASTs and symbol tables */
	Set<RawClassData> dependencySet= DependencyResolver.resolve(sourceFile, inputDirs);

	/* Split set entries that contains multiple classes into one entry for each class
	   Will  have no effect if each source file contains only one class */
	Set<RawClassData> classDependencySet = splitRawDataSetByClass(dependencySet);
	
	/* The directory path of the source file will be used to determine relative paths of each dependency file */
	String rootCanonicalPath = sourceFile.getParentFile().getCanonicalPath();
	
	/* Generate initialized compilation unit pods based on dependency set,
	   map fully qualified class names to pods, for use in creating class hierarchy tree
	   After this step, the compilation pods carry no parent/child information */
	Map<String, CompilationUnitPod> compUnitMap = initializeCompilationUnitPods(rootCanonicalPath, classDependencySet);

	/* Add pods for library classes (e.g. Object, String, Class, etc)
	   Get what WILL be the root pod after tree is constructed, which will always be the Object pod */
	CompilationUnitPod rootPod = addLibraryCompilationUnitPods(compUnitMap);
	
	/* Create tree structure by setting parent/child fields in each pod */
	populateTreeFields(compUnitMap);
	
	return rootPod;
    }
  
    private static List<File> getInputDirectories() {
	String classPathString = System.getProperty("java.class.path");
	String[] classPaths = classPathString.split(System.getProperty("path.separator"));
	    
	List<File> inputDirs = new ArrayList<File>();
	for (int i = 0; i < classPaths.length; i++) {
	    
	    inputDirs.add(new File(classPaths[i]));
	}
	
	return inputDirs;
    }

    /**
    * Split all entries in data set that contain multiple classes into one entry for each class.
    * @param set the set of {@link RawClassData} to process
    * @return The new set, with one entry for each class
    */
    private static Set<RawClassData> splitRawDataSetByClass(Set<RawClassData> set) {
	Set<RawClassData> newSet = new HashSet<RawClassData>();

	for (RawClassData datum : set) {
	    Node origAst = datum.getAst();
	    File file = datum.getInputFile();
	    SymbolTable table = datum.getSymbolTable();

	    /* Get set of ASTs that contains only one ClassDeclaration each */
	    Set<Node> classAsts = AstPreprocessorUtilities.splitAstByClass(origAst);
	    
	    for (Node ast : classAsts) {
		RawClassData classData = new RawClassData(ast, table, file);
		newSet.add(classData);
	    }
	}

	return newSet;
    }

    /** 
    * Initialize {@link CompilationUnitPod}s by copying fields from {@link RawClassData} and determining fully qualified class names.
    * This is a first pass of creating the {@link CompilationUnitPod}s. No child or parent fields are resolved in this stage.
    * @param rootCanonicalPath the root path (canonical) of the original source file
    * @param classDependencySet set of {@link RawClassData} containing one entry for each class
    * @return a mapping of fully qualified class names to their respective {@link CompilationUnitPod}
    */
    private static Map<String, CompilationUnitPod> initializeCompilationUnitPods(String rootCanonicalPath, Set<RawClassData> classDependencySet) 
	    throws Exception {
	Map<String, CompilationUnitPod> compUnitMap = new HashMap<String, CompilationUnitPod>();

	for (RawClassData entry : classDependencySet) {
	    File file = entry.getInputFile();
	    
	    /* Get relative path from the original source file to the dependency  
	       May be useful later if we want to output C++ files in the same class hierarchy */
	    File relativeDir = getRelativeDir(rootCanonicalPath, file.getParentFile().getCanonicalPath());
	    
	    SymbolTable table = entry.getSymbolTable();
	    Node node = entry.getAst();
	    String qualifiedClassName = AstPreprocessorUtilities.getQualifiedClassName(node);

	    CompilationUnitPod compUnitPod = new CompilationUnitPod(node, table, qualifiedClassName, relativeDir);	
	    compUnitMap.put(qualifiedClassName, compUnitPod);
	}

	return compUnitMap;
    }

    private static File getRelativeDir(String rootPath, String dirPath) {
	File relativeDir;
	if (dirPath.equals(rootPath)) {
	    relativeDir = new File(".");
	} else {
	    int index = dirPath.indexOf(rootPath);
	    
	    if (index >= 0 && index + rootPath.length() < dirPath.length()) {
		relativeDir = new File(dirPath.substring(index + rootPath.length()));
	    } else {
		relativeDir = new File(".");
	    }
	}

	return relativeDir;
    }

    /**
    * Placeholder function that creates an empty {@link CompilationUnitPod} for java.lang.Object.
    * We'll have to figure out how we want to store data for the library classes
    * @param compUnitMap mapping of fully qualified class names to {@link CompilationUnitPod}s
    */
    private static CompilationUnitPod addLibraryCompilationUnitPods(Map<String, CompilationUnitPod> compUnitMap) {
    	
	CompilationUnitPod pod = new CompilationUnitPod(null, null, "java.lang.Object", null, true);
	compUnitMap.put("java.lang.Object", pod);
	return pod;
    }

    /** Determines parent of {@link CompilationUnitPod} and fills in parent/child fields in each one, creating 
    * the tree structure. Done in a second pass so that fully qualified class names are available.
    * @param compUnitMap mapping of fully qualified class names to {@link CompilationUnitPod}s
    */
    private static void populateTreeFields(Map<String, CompilationUnitPod> compUnitMap) {
	for (CompilationUnitPod pod : compUnitMap.values()) {
	    if (!pod.isLibraryClass()) {
		String qualifiedClassName = pod.getQualifiedClassName();
		String parentName = AstPreprocessorUtilities.getParentName(pod.getJavaAst());
		Set<String> imports = AstPreprocessorUtilities.getImports(pod.getJavaAst());
		
		CompilationUnitPod parentPod = findParentPod(qualifiedClassName, parentName, imports, compUnitMap);
	    
		pod.setParentPod(parentPod);
		parentPod.addChildPod(pod);
	    }
	}
    }
    
    /**
    * Get the parent pod for a given class. 
    * @param qualifiedClassName the qualified class name of the child class
    * @param parentName the name of the parent class. can either be unqualifed or qualified
    * @param imports a set of all import declarations of the child class
    * @param compUnitMap a mapping of fully qualified class names to {@link CompilationUnitPod} for all classes
    * @return the {@link CompilationUnitPod} of the parent class
    */
    private static CompilationUnitPod findParentPod(String qualifiedClassName, String parentName, Set<String> imports, Map<String, CompilationUnitPod> compUnitMap) {

	// If no parent name, its implied that the class is a direct child of the predefined root class (i.e. java.lang.Object)
	if (parentName == null) {
	    if (compUnitMap.containsKey(FULLY_QUALIFIED_ROOT_CLASS_NAME)) {
		return compUnitMap.get(FULLY_QUALIFIED_ROOT_CLASS_NAME);
	    }
	// If parent name is fully qualified, use its unmodified name as a key
	} else if (parentName.contains(".")) { 
	    if (compUnitMap.containsKey(parentName)) {
		return compUnitMap.get(parentName);
	    }
	} else { // If parent name is not fully qualified, try to resolve using package/import information

	    // First attempt: Search for direct import of the parent class
	    for (String importDeclaration : imports) {
		if (importDeclaration.endsWith("." + parentName)) {
		    if (compUnitMap.containsKey(importDeclaration)) {
			return compUnitMap.get(importDeclaration);
		    }
		}
	    }

	    // Second attempt: Search child class's package for parent class
	    {
		int lastDelim = qualifiedClassName.lastIndexOf(".");
		String packageName;

		// If child is in a non-default package
		if (lastDelim >= 0) {
		    packageName = qualifiedClassName.substring(0, lastDelim + 1); // lastDelim + 1 to keep the trailing period

		} else { // child in default package
		    packageName = "";
		}
		    
		String candidateName = packageName + parentName;
		if (compUnitMap.containsKey(candidateName)) {
		    return compUnitMap.get(candidateName);
		}
	    }

	    /* Third attempt: Search for parent class in a full package that was imported
	       e.g. inheriting from "File" after importing "java.io.*" */
	    for (String importDeclaration : imports) {
		if (importDeclaration.endsWith("*")) {
		    String packageName = importDeclaration.substring(0, importDeclaration.length() - 1);
		    String candidateName = packageName + parentName;

		    if (compUnitMap.containsKey(candidateName)) {
			return compUnitMap.get(candidateName);
		    }
		}
	    }
	}	
	
	// Parent class pod should have been found by now. Something went wrong. 
	return null;
    }
}