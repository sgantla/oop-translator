import java.util.ArrayList;

package tree;

/** Declaration representing a class, the header of a class.
 *
 * Ex: public class Circle { }
 *
 * modifiers = { public };
 * name = "Circle";
 * classBody = null;
 */

public class ClassDeclaration extends Declaration {

    List<Modifier> modifiers;
    String name;
    ClassBody classBody;

    VTable _vTable; // female
    ClassDeclaration _inheritsFrom;
}
