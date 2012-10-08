import java.util.ArrayList;

package tree;

public class MethodDeclaration extends Declaration {
    List<Modifier> modifiers;
    Type returnType;
    List<Modifier> returnTypeModifiers;
    String name;
    List<FormalParameter> formalParameters;
    ThrowsClause throws;
    Block methodBlock;

    boolean _isVirtual;
}
