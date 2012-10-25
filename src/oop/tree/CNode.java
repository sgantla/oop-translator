package oop.tree;
import oop.translator.CppAstUtil;

<<<<<<< HEAD
public abstract class CNode {

    CNode _parent;
    CppAstUtil.NodeName name;
    
    public CppAstUtil.NodeName getName() {
      return this.name;
=======
public abstract class CNode
{
    private CNode parent;
    public CNode getParent() {
	return parent;
    }
    public CNode setParent(CNode parent) {
	this.parent = parent;
>>>>>>> 8e25523a345fe15d92add84f0a2eb678949aa96e
    }
}
