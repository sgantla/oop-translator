import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import xtc.type.PackageT;

import xtc.lang.JavaAstSimplifier;
import xtc.lang.JavaFiveParser;
import xtc.lang.JavaAnalyzer;

import xtc.parser.ParseException;
import xtc.parser.Result;

import xtc.tree.Node;
import xtc.tree.Printer;

import xtc.util.Tool;
import xtc.util.SymbolTable;

/*

Proof of concept to demonstrate how to get file dependencies from a symbol table

Input: One or multiple names of java source files as arguments
Output: The file path (canonical), AST, and symbol table of each input and all dependencies of those inputs (recursively)

*/
public class DependencyResolution extends Tool {
		
	private String getFileName(String fileScopeName) {
		
		Pattern p = Pattern.compile("^file\\((.*?)\\)$");
		Matcher m = p.matcher(fileScopeName);

		return (m.find()) ? m.group(1) : null;

	}

	private Set<File> getFilesInPackageScope(SymbolTable table, SymbolTable.Scope scope, String packageName) throws Exception {
	
		Set<File> packageFiles = new HashSet<File>();

		for (Iterator<String> iter = scope.symbols(); iter.hasNext();) {
			
			String symbol = iter.next();
			Object symbolValue = scope.lookup(symbol);

			if (symbolValue instanceof PackageT) {

				PackageT pkg = (PackageT) symbolValue;
				SymbolTable.Scope subPackageScope = table.getScope(table.root().qualify(pkg.toString()));

				Set<File> subPackageFiles = getFilesInPackageScope(table, subPackageScope, pkg.getName());

				packageFiles.addAll(packageFiles);
			}	
		}

		for (Iterator<String> iter = scope.nested(); iter.hasNext();) {

			String scopeName = iter.next();
			String fileName = getFileName(scopeName);

			if (fileName != null) {
				packageFiles.add(new File(fileName).getCanonicalFile());
			}
		}
		
		return packageFiles;
	}

	private Set<File> getFileDependenciesFromSymbolTable(SymbolTable table) throws Exception {
		
		Set<File> fileDependencies = new HashSet<File>();
		
		SymbolTable.Scope scope = table.current();

		for (Iterator<String> iter = scope.symbols(); iter.hasNext();) {
				
			String symbol = iter.next();
			Object symbolValue = scope.lookup(symbol);

			if (symbolValue instanceof PackageT) {

				PackageT pkg = (PackageT) symbolValue;
				SymbolTable.Scope packageScope = table.getScope(scope.qualify(pkg.toString()));

				Set<File> packageFiles = getFilesInPackageScope(table, packageScope, pkg.getName());
				fileDependencies.addAll(packageFiles);
			}	
		}
	
		return fileDependencies;
	}

	public Node parse(Reader in, File file) throws IOException, ParseException {
		
		JavaFiveParser parser = new JavaFiveParser(in, file.toString(), (int)file.length());
		Result result = parser.pCompilationUnit(0);

		Node astRoot = (Node) parser.value(result);
		
		return astRoot;
	}

	private AstSymbolTablePair getAstAndSymbolTablePair(File f) throws Exception {

		Node node = parse(runtime.getReader(f), f);

		node = (Node) new JavaAstSimplifier().dispatch(node);
		SymbolTable table = new SymbolTable();
		new JavaAnalyzer(runtime, table).dispatch(node);

		return new AstSymbolTablePair(node, table);
	}

	private Map<File, AstSymbolTablePair> getAstAndSymbolTablePairMap (Set<File> files) throws Exception {

		Map<File, AstSymbolTablePair> map = new HashMap<File, AstSymbolTablePair>();

		for (File file : files) {

			map.put(file, getAstAndSymbolTablePair(file));
		}

		return map;
	}

	private Map<File, AstSymbolTablePair> resolveDependencies(Set<File> files, Set<File> previouslyResolvedFiles) throws Exception {

		Map<File, AstSymbolTablePair> map = getAstAndSymbolTablePairMap (files);
		Set<File> newFileDependencies = new HashSet<File>();

		for (AstSymbolTablePair pair : map.values()) {
		
			SymbolTable table = pair.getSymbolTable();
			Set<File> fileDependencies = getFileDependenciesFromSymbolTable(table);
		
			for (File f : fileDependencies) {
				if (!files.contains(f) && !previouslyResolvedFiles.contains(f)) {

					newFileDependencies.add(f);
				}
			}
		}
		
		if (! newFileDependencies.isEmpty()) {

			previouslyResolvedFiles.addAll(files);
			Map<File, AstSymbolTablePair> newDependenciesMap = resolveDependencies(newFileDependencies, previouslyResolvedFiles);
		
			map.putAll(newDependenciesMap);
		}

		return map;
		
	}

	private Map<File, AstSymbolTablePair> resolveDependencies(Set<File> files) throws Exception {
	
		return resolveDependencies(files, new HashSet<File>());

	}

	public void resolve(String[] filePaths) throws Exception {

		Set<File> files = new HashSet<File>();
		
		for (String filePath : filePaths) {
			files.add(new File(filePath).getCanonicalFile());
		}
		
		Map<File, AstSymbolTablePair> fileMap = resolveDependencies(files);

		for (Map.Entry<File, AstSymbolTablePair> entry : fileMap.entrySet()) {
				
			File f = entry.getKey();
			Node n = entry.getValue().getAst();
			SymbolTable t = entry.getValue().getSymbolTable();		
			
			StringWriter symTableWriter = new StringWriter();
			Printer p = new Printer(symTableWriter);
			t.root().dump(p);
			p.flush();
	
			StringWriter astWriter = new StringWriter();
			p = new Printer(astWriter);
			p.format(n, false).pln();
			p.flush();

			System.out.println("File: " + f + "\nAST:\n " + astWriter + "Symbol Table:\n" + symTableWriter + "\n");
		}

	}

	public DependencyResolution() {

		init();
		prepare();
	}

	public static void main(String[] args) throws Exception {

		new DependencyResolution().resolve(args);
	}

	public String getName() {

		return "Dependency Resolution POC";

	}
}
