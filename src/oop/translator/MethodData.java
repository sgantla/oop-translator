package oop.translator;
import java.util.*;

class MethodData {
    private String methodName;
    private Type returnType;
    private List<Type> parameterTypes;
    private ClassReference class;
    
    public boolean equals(MethodData otherData) {
	return methodName.equals(otherData.methodName); // will have to evaluate param types for method overloading, later
    }
}