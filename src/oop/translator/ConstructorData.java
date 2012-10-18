package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class ConstructorData {
    private String constructorName;
    private MethodT methodType;
    private BlockTranslator body;
    
    public ConstructorData(MethodT methodType, BlockTranslator body) {
	this.methodType = methodType;
	this.constructorName = methodType.getName();
	this.body = body;
    }
    
    public String getConstructorName() {
	return constructorName;
    }
    
    public MethodT getMethodType() {
	return methodType;
    }
    
    public BlockTranslator getBodyTranslator() {
	return body;
    }
}