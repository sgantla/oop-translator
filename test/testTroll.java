//Three test cases 
//Tests inheritance, upcastings, overrriding, empty constructors(inherent super() call)

public class testTroll{
  public static void main(String[] args){
    parentTroll parent = new parentTroll();
    childTroll child = new childTroll();
    
    //Test Case 1 
    
    System.out.println("Test 1: Simple test for method overriding");
    System.out.println("Calling overwrriten troll method from child");
    child.troll();
    System.out.println("Calling the original troll method from parent");
    parent.troll();
    
    
    //Test Case 2 
    System.out.println("Test 2: Checking getClass on parent and child");
    System.out.println("Child is of class : "+child.getClass().getName());
    System.out.println("Child's superclass is : "+child.getClass().getSuperclass().getName());
    System.out.println("Parent's superclass is : "+parent.getClass().getSuperclass().getName());
    
    //Test Case 3 
    //Casting checks 
    System.out.println("Test 3: Casting checks");
    childTroll child2 = new childTroll();
    parentTroll parent3 = child2;
    
    System.out.println("Child2 is of type "+child2.getClass());
    child2.troll();
    System.out.println("Downcasted parent3 is of type"+parent3.getClass());
    System.out.print("Calling parent3.troll()....");
    parent3.troll();
    
    //Test Case 4
    //Check if childTroll class has the overriden toString() method
    System.out.println("Test 4: toString() should be ovverriden in the child class only");
    System.out.println(parent.toString());
    System.out.println(child2.toString());
    
  }
}


class parentTroll{
  parentTroll(){
  }
  public void troll(){
    System.out.println("This is the parent troll method where the object trolls softly");
  }
}

class childTroll extends parentTroll{
  childTroll(){
  }
  public void troll(){
    System.out.println("This is the child troll method where the the object trolls hard");
  }
  
  public String toString(){
    return "Object toString() successfully ovveridden in childTroll";
  }
}

