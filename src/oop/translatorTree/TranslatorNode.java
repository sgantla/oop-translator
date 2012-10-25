package oop.translatorTree;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public abstract class TranslatorNode {
	private TranslatorNode parent;
	private String qualifiedScopeName;

	public TranslatorNode(TranslatorNode parent) {
		this.parent = parent;
	}

	public TranslatorNode getParent() {
		return parent;
	}	
	public void setParent(TranslatorNode parent) {
		this.parent = parent;
	}

	public String getQualifiedScopeName() {
		return qualifiedScopeName;
	}
	public void setQualifiedScopeName(String qualifiedScopeName) {
		this.qualifiedScopeName = qualifiedScopeName;
	}

	abstract public void initialize(Node n);
}