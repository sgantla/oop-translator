package oop.tree;

public abstract class CNode
{
    private CNode parent;
    public CNode getParent() {
	return parent;
    }
    public CNode setParent(CNode parent) {
	this.parent = parent;
    }
}
