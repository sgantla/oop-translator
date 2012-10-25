
public class testExceptions{
  public static void main (String[] args){
    
    System.out.println("Test Case 1 : null pointer exception");
    String nullStr=null; 
    try{
      System.out.println(nullStr.toString());
    }catch (NullPointerException ex){
      System.out.println("NullPointerException thrown: String = null; cannot call toString()");
    }
    
    //Test Case 2 : Class cast exception 
   System.out.println("Test Case 2 : Class cast exception");
   Object o = 15; 
   System.out.println("Object o = 15 is of class "+o.getClass().getName());
   try{
     System.out.println((String)o);
   }catch (ClassCastException ex2){
     System.out.println(ex2);
   }
   
   //Test Case 3: General exception 
   int zero = 0;
   try{
     System.out.println(100/zero);
   }
   catch( Exception ex3){
     System.out.println(ex3);
   }
    
    
    
  }
}
