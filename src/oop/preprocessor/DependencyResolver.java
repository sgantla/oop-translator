package oop.preprocessor;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import xtc.lang.JavaAstSimplifier;
import xtc.lang.JavaFiveParser;
import xtc.lang.JavaAnalyzer;
import xtc.type.PackageT;
import xtc.parser.ParseException;
import xtc.parser.Result;
import xtc.tree.Node;
import xtc.tree.Printer;
import xtc.util.Tool;
import xtc.util.SymbolTable;
import xtc.util.Runtime;

class DependencyResolver {
		
    private static Runtime runtime;

    /* initialize runtime */
    static {
	runtime = new Runtime();

	runtime.dir("in", Runtime.INPUT_DIRECTORY, true, null);
	runtime.dir("out", Runtime.OUTPUT_DIRECTORY, false, null);

	runtime.initDefaultValues();
    }

    public static Set<RawClassData> resolve(File file, List<File> inputDirectories) throws Exception {
	List<File> dirList = runtime.getFileList(Runtime.INPUT_DIRECTORY);
	dirList.clear();
	dirList.addAll(inputDirectories);

	return resolveFileDependencies(file);
    }

    public static Set<RawClassData> resolve(File file) throws Exception {
	List<File> inputDirs = new ArrayList<File>();
	inputDirs.add(new File("."));

	return resolve(file, inputDirs);
    }

    private static Set<RawClassData> resolveFileDependencies(File file) throws Exception {
	Set<File> fileSet = new HashSet<File>();
	fileSet.add(file);

	return resolveFileSetDependencies(fileSet, new HashSet<File>());
    }

    private static Set<RawClassData> resolveFileSetDependencies(Set<File> files, Set<File> previouslyResolvedFiles) throws Exception {
	Set<RawClassData> set = getRawClassDataSet(files);
	Set<File> newFileDependencies = new HashSet<File>();

	for (RawClassData datum : set) {
	    SymbolTable table = datum.getSymbolTable();
	    Set<File> fileDependencies = getFileDependenciesFromSymbolTable(table);

	    for (File f : fileDependencies) {
		if (!files.contains(f) && !previouslyResolvedFiles.contains(f)) {
		    newFileDependencies.add(f);
		}
	    }
	}

	if (!newFileDependencies.isEmpty()) {
	    previouslyResolvedFiles.addAll(files);
	    Set<RawClassData> newDependenciesSet = resolveFileSetDependencies(newFileDependencies, previouslyResolvedFiles);

	    set.addAll(newDependenciesSet);
	}

	return set;
    }

    private static Set<RawClassData> getRawClassDataSet (Set<File> files) throws Exception {
	Set<RawClassData> set = new HashSet<RawClassData>();
	for (File file : files) {
	    set.add(getRawClassData(file));
	}

	return set;
    }

    private static RawClassData getRawClassData(File f) throws Exception {
	Node node = parse(runtime.getReader(f), f);

	node = (Node) new JavaAstSimplifier().dispatch(node);
	SymbolTable table = new SymbolTable();
	new JavaAnalyzer(runtime, table).dispatch(node);

	return new RawClassData(node, table, f);
    }

    private static Set<File> getFileDependenciesFromSymbolTable(SymbolTable table) throws Exception {
	Set<File> fileDependencies = new HashSet<File>();

	SymbolTable.Scope scope = table.current();
	for (Iterator<String> iter = scope.nested(); iter.hasNext();) {
	    String scopeName = iter.next();

	    if (isPackageScope(scopeName)) {
		SymbolTable.Scope subScope = table.getScope(table.root().qualify(scopeName));

		for (Iterator<String> subIter = subScope.nested(); subIter.hasNext();) {
		    String subScopeName = subIter.next();
		    String fileName = getFileName(subScopeName);

		    if (fileName != null && !fileName.trim().equals("")) {
			fileDependencies.add(new File(fileName).getCanonicalFile());
		    }
		}
	    }
	}

	return fileDependencies;
    }

    private static boolean isPackageScope(String fileScopeName) {
	Pattern p = Pattern.compile("^package\\((.*?)\\)$");
	Matcher m = p.matcher(fileScopeName);

	return (m.find());
    }

    private static String getFileName(String fileScopeName) {
	Pattern p = Pattern.compile("^file\\((.*?)\\)$");
	Matcher m = p.matcher(fileScopeName);

	return (m.find()) ? m.group(1) : null;
    }

    public static Node parse(Reader in, File file) throws IOException, ParseException {
	JavaFiveParser parser = new JavaFiveParser(in, file.toString(), (int)file.length());
	Result result = parser.pCompilationUnit(0);

	Node astRoot = (Node) parser.value(result);

	return astRoot;
    }
}