package oop.translator;

import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;

public class InheritanceData {
    private VTable virtualMethodTable = new VTable();
    private List<InstanceFieldStructData> instanceFieldStructs = new ArrayList<InstanceFieldStructData>();
    
    public VTable getVirtualMethodTable() {
	return virtualMethodTable;
    }
    public List<InstanceFieldStructData> getInstanceFieldStructs() {
	return instanceFieldStructs;
    }
    
    public void setVirtualMethodTable(VTable table) {
	this.virtualMethodTable = table;
    }
    public void setInstanceFieldStructs(List<InstanceFieldStructData> instanceFieldStructs) {
	this.instanceFieldStructs = instanceFieldStructs;
    }
    
    public InheritanceData deepCopy() {
	InheritanceData newData = new InheritanceData();
	
	VTable vtable = virtualMethodTable.deepCopy();
	List<InstanceFieldStructData> newFieldStructs = new ArrayList<InstanceFieldStructData>();
	for (InstanceFieldStructData structData : instanceFieldStructs) {
	    newFieldStructs.add(structData.copy());
	}
	
	newData.setVirtualMethodTable(vtable);
	newData.setInstanceFieldStructs(newFieldStructs);
	
	return newData;
    }
}