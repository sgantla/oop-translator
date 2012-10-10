package oop.preprocessor;

import java.io.File;

import xtc.tree.Node;
import xtc.util.SymbolTable;

class RawClassData {
	
    private Node astRoot;
    private SymbolTable symbolTable;
    private File inputFile;

    public Node getAst() {
	return astRoot;
    } 

    public SymbolTable getSymbolTable() {
	return symbolTable;
    }
    
    public File getInputFile() {
	return inputFile;
    }

    public RawClassData(Node root, SymbolTable table, File file) {
	this.astRoot = root;
	this.symbolTable = table;
	this.inputFile = file;
    }
}

