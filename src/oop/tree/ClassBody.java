package oop.tree;

import xtc.tree.Node;
import java.util.ArrayList;
import java.util.List;

/** A list of declarations, representing the body of a class.
 *
 * Ex: 
 *  public class Circle {
 *      
 *      private int radius;
 *
 *      public Circle(int r) {
 *          radius = r;
 *      }
 *  }
 *
 * _vptr = ???;
 * declarations = { private int radius,     a FieldDeclaration
 *                  public Circle}          a MethodDeclaration
 */

public class ClassBody extends CNode {

    // Pointer _vptr;
    public List<Declaration> declarations;
    
}
