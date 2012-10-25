package oop.tree;
import oop.translator.CppAstUtil;
import xtc.type.Type;
import xtc.tree.Location;

public abstract class CNode {

	private CNode parent;
	private Location location;
	private String scopeName;
	CppAstUtil.NodeName name;

	public CppAstUtil.NodeName getName() {
		return this.name;
	}

	public void setName(CppAstUtil.NodeName name) {
		this.name = name;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	public Location getLocation() {
		return this.location;
	}

	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}
	public String getScopeName() {
		return scopeName;
	}

	public CNode getParent() {
		return parent;
	}
	public void setParent(CNode parent) {
		this.parent = parent;
	}
}