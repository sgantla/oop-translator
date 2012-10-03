import xtc.tree.*;
import xtc.util.*;
import xtc.lang.*;

public class AstSymbolTablePair {
	
	private Node astRoot;
	private SymbolTable symbolTable;

	public Node getAst() {

		return astRoot;
	}
	
	public SymbolTable getSymbolTable() {

		return symbolTable;
	}
	
	public AstSymbolTablePair(Node root, SymbolTable table) {

		this.astRoot = root;
		this.symbolTable = table;
	}
}
