package oop.translator;

/* This iterator is used to iterate through the C++ PodTree structure.
 *
 */
import oop.preprocessor.*;
import oop.translator.*;
import oop.translatorTree.*;
import oop.tree.interfaces.*;

import xtc.tree.*;
import xtc.type.*;
import xtc.util.*;

import java.util.*;
import java.io.*;
public class PodIterator
{

    public CompilationUnitPod rootPod;
    public CompilationUnitPod currentPod;

    public PodIterator(CompilationUnitPod rootPod)
    {
        this.rootPod = rootPod;
        currentPod = rootPod;
    }

    // breadth first
    public CompilationUnitPod next()
    {
	return null;
    }

    public boolean hasNext()
    {
	return false;
    }
}

