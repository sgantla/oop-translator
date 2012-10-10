package oop.tree;

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

public class ClassBody extends Node {

   // Pointer _vptr;
    List<Declaration> declarations;
}