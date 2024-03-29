package oop.preprocessor;

import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.StringWriter;

import oop.translatorTree.*;
import oop.translator.*;

import xtc.tree.Node;
import xtc.tree.Printer;
import xtc.util.SymbolTable;
import xtc.type.*;

public class CompilationUnitPod {

	/* Fields */

	private final Node javaAstRoot;
	private final SymbolTable symbolTable;
	private final String qualifiedClassName;
	private final File relativeDir;
	private final boolean isLibraryClass;

	private CompilationUnitTranslator cppCompilationUnit;
	private List<CompilationUnitPod> childCompilationUnitPods = new ArrayList<CompilationUnitPod>();
	private CompilationUnitPod parentCompilationUnitPod;
	private InheritanceData inheritanceData;
	private ClassT classType;

	/* Constructors */
	
	/** Creates an empty compilation unit pod to return when the pod tree is empty. */ 
	public CompilationUnitPod() {
		this.javaAstRoot = null; 
		this.symbolTable = null; 
		this.qualifiedClassName = null; 
		this.relativeDir = null; 
		this.isLibraryClass = false; 
	}

	public CompilationUnitPod(Node ast, SymbolTable table, String qualifiedClassName, File relativeDir, boolean isLibrary) {
		this.javaAstRoot = ast;
		this.symbolTable = table;
		this.qualifiedClassName = qualifiedClassName;
		this.relativeDir = relativeDir;
		this.isLibraryClass = isLibrary;
	}

	public CompilationUnitPod(Node ast, SymbolTable table, String qualifiedClassName, File relativeDir) {
		this(ast, table, qualifiedClassName, relativeDir, false);
	}

	/* Getters */

	public Node getJavaAst() {
		return javaAstRoot;
	}

	public String getQualifiedClassName() {
		return qualifiedClassName;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public boolean isLibraryClass() {
		return isLibraryClass;
	}	

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public List<CompilationUnitPod> getChildren() {
		return childCompilationUnitPods;
	}

	public CompilationUnitPod getParentPod() {
		return parentCompilationUnitPod;
	}
	
    public CompilationUnitTranslator getCppCompilationUnit()
    {
        return cppCompilationUnit;
    }
	 
	public ClassT getClassType() {
		return classType;
	}

	public InheritanceData getInheritanceData() {
	return inheritanceData;
    }


	/* Setters */

	public void setClassType(ClassT classType) {
		this.classType = classType;
	}
	
    public void setInheritanceData(InheritanceData inheritanceData) {
	this.inheritanceData = inheritanceData;
    }

	public void setParentPod(CompilationUnitPod parent) {
		this.parentCompilationUnitPod = parent;
	}

	public void addChildPod(CompilationUnitPod child) {
		this.childCompilationUnitPods.add(child);
	}
	
    public void setCppCompilationUnit(CompilationUnitTranslator cppUnit)
    {
        cppCompilationUnit = cppUnit;
    }
	
	/** 
	 * Print a string representation of the pod and its children for use in debugging
	 */
	public void print() {
		print(0);
	}

	private void print(int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indent; i++)
			sb.append("  ");

		System.out.println(sb.toString() + "Compilation Unit Pod {");
		sb.append("  ");
		System.out.println(sb.toString() + "Qualified class name: " + this.qualifiedClassName);

		if (! this.isLibraryClass) {

			StringWriter symTableWriter = new StringWriter();
			Printer p = new Printer(symTableWriter);
			for (int i = 0; i < indent + 2; i++)
				p.incr();
			this.symbolTable.root().dump(p);
			p.flush();

			StringWriter astWriter = new StringWriter();
			p = new Printer(astWriter);
			for (int i = 0; i < indent + 2; i++)
				p.incr();
			p.format(this.javaAstRoot, false).pln();
			p.flush();

			System.out.println(sb.toString() + "Java AST:\n" + astWriter.toString());
			System.out.println(sb.toString() + "Java Symbol Table:\n" + symTableWriter.toString());
			System.out.println(sb.toString() + "Relative Dir: " + this.relativeDir);
		}

		System.out.println(sb.toString() + "Children:");

		for (CompilationUnitPod child : this.childCompilationUnitPods) {
			child.print(indent + 2);
		}

		System.out.println(sb.substring(0, sb.length() -2) + "}");
	}
}
