package oop.translator.tree;
import xtc.tree.Node;

abstract class TranslatorNode {
    private TranslatorNode parent;

    public void setParent(TranslatorNode parent) {
	this.parent = parent;
    }
    public TranslatorNode getParent() {
	return parent;
    }	
    
    public TranslatorNode(TranslatorNode parent) {
	this.parent = parent;
    }

    abstract public void initialize(Node n);
  
}