import java.util.ArrayList;

package tree;

public class FieldDeclaration extends Declaration {
    List<Modifier> modifiers;
    Type type;
    String name;
    Expression assignment;
}

