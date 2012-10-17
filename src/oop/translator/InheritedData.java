package oop.translator;

import java.util.*;

class InheritedData {
    private List<MethodData> virtualMethodTable;
    private List<MethodData> staticMethodTable;
    private List<ConstructorData> constructorTable;
    private List<InitializationField> initializationList;
    private List<FieldDeclarationTranslator> fieldDeclarations;
}