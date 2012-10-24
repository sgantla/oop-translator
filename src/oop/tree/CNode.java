package oop.tree;
import oop.translator.CppAstUtil;

public abstract class CNode {

    CNode _parent;
    CppAstUtil.NodeName name;
    
    public CppAstUtil.NodeName getName() {
      return this.name;
    }
}
