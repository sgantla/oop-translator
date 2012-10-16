package oop.translator;

/* This iterator is used to iterate through the C++ PodTree structure.
 *
 */

public class PodIterator
{

    public CompilationUnitPod rootPod;
    public CompilationUnitPod currentPod;

    public PodIterator(CompilationUnitPod rootPod)
    {
        this.rootPod = rootPod;
    }

    // breadth first
    public CompilationUnitPod next()
    {

    }

    public boolean hasNext()
    {

    }
}

