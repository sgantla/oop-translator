package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class MethodData {

    private MethodT methodType;
    private ClassT classType;
    
    public boolean equals(Object otherData) {
	if (otherData instanceof MethodData) {
	    return this.getMethodName().equals(((MethodData)otherData).getMethodName());
	} else {
	    return super.equals(otherData);
	}
    }
    
    public MethodData(MethodT methodType, ClassT classType) {
	this.methodType = methodType;
	this.classType = classType;
    }
    
    public MethodT getMethodType() {
	return methodType;
    }
    
    public ClassT getClassType() {
	return classType;
    }

    public List<Type> getParameters() {
        return methodType.getParameters();
    }
    
    public Type getReturnType() {
        return methodType.getResult();
    }
    
    public String getMethodName() {
	return this.methodType.getName();
    }
    
    public void setMethodName(String methodName) {
	this.methodType = new MethodT(this.methodType.getResult(), methodName, this.methodType.getParameters(), false, this.methodType.getExceptions());
    }
    
    public void setMethodType(MethodT methodType) {
	this.methodType = methodType;
    }
    
    public void setClassType(ClassT classType) {
	this.classType = classType;
    }
    
    public MethodData deepCopy() {
	return new MethodData(methodType.copy(), classType.copy());
    }
}
