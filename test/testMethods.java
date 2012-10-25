//Tests for method overloading and casting

public class testMethods{
  
  public static void testMethod ( int[] x){
    System.out.println("testMethod param is type int[]");
  }
  public static void testMethod ( String[] s){
    System.out.println("testMethod param is type string[]");
  }
  
  public static String testMethod ( String str){
    return "testMethod param is type string";
  }
  
  public static void testMethod (Object obj){
    System.out.println("testMethod param is type object");
  }
  
  public static void testMethod(char c){
    System.out.println("testMethod param is type char");
  }
  
  public static void testMethod ( int i){
    System.out.println("testMethod param is type int");
  }
  
  
  
  public static void main (String[] params){
    
    
    int [] x = {1,2,3};
    String [] s = {"hello","world"};
    Object obj = new Object();
    char c = 'c';
    int i = 1;
    
    //Test 1
    System.out.println("Running Tests to check overloading of testMethod()..will print 5 differnt param types(");
    testMethod(x);
    testMethod(s);
    testMethod(obj);
    testMethod(c);
    testMethod(i);
    
    //Test 2
    
    System.out.println("Test 2:casting of (Object)String as param; If not casted correctly by translator, should result in an infinite loop");
    String test = "test";
    testMethod((Object)testMethod(test));
    
    //Test 3
    
    System.out.println("Test 3: check casting of (int)double as param to testMethod");
    testMethod((int)44.0);
    
    
  }
}



