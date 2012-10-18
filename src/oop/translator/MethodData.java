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

public class MethodData {
    private String methodName;
    private MethodT methodType;
    private ClassT classType;
    
    public boolean equals(MethodData otherData) {
	return methodName.equals(otherData.methodName);
    }
    
    public MethodData(MethodT methodType, ClassT classType) {
	this.methodName = methodType.getName();
	this.methodType = methodType;
	this.classType = classType;
    }
    
    public MethodData(String methodName) {
	this.methodName = methodName;
    }
    
    public String getMethodName() {
	return methodName;
    }
    
    public MethodT getMethodType() {
	return methodType;
    }
    
    public ClassT getClassType() {
	return classType;
    }
    
    public void setMethodName(String methodName) {
	this.methodName = methodName;
	this.methodType = new MethodT(this.methodType.getResult(), methodName, this.methodType.getParameters(), false, this.methodType.getExceptions());
    }
    
    public void setMethodType(MethodT methodType) {
	this.methodType = methodType;
    }
    
    public void setClassType(ClassT classType) {
	this.classType = classType;
    }
}