package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class VTable {
    
    private List<MethodData> table = new ArrayList<MethodData>();
    
    public void set(int i, MethodData value) {
	table.set(i, value);
    }
    public MethodData get(int i) {
	return table.get(i);
    }
    public void add(MethodData method) {
	table.add(method);
    }
    public int indexOf(String methodName) {
	for (int i = 0; i < table.size(); i++) {
	
	    MethodData methodData = table.get(i);
	    if (methodData.getMethodName().equals(methodName)) {
		return i;
	    }
	}
	
	return -1;
    }
    
    public VTable deepCopy() {
	VTable newTable = new VTable();
	for (MethodData method : table) {
	    newTable.add(method.deepCopy());
	}
	
	return newTable;
    }
}