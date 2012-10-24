package oop.tree.statements;

import oop.tree.*;
import oop.tree.interfaces.*;

import java.util.List;

public class FormalParameter extends Node {

   List<Modifier> modifiers;
   Type type;
   String name;

   public FormalParameter(List<Modifier> modifiers, Type type, String name) {
       this.modifiers = modifiers;
       this.type = type;
       this.name = name;
   }
}
