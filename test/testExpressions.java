
//Contains 6 test cases 
//Tests simple arithmetic expressions, method invocations, methods as arguments
//Also test return typecasting (if we pass test case 5 we are good to go!)

public class testExpressions{
  public static void main (String[] args){
    
    int x = 3; 
    int y; 
    y = 3; 
    int z = x + y; 
    
    //Test case 1; simple expressions 
    if(z==(x+y)){
      System.out.println("Test 1: "+z);
    }
    
    //Test case 1a: 
    int case1a = x * y + z * z + (x*y/z);
    int mod = case1a % case1a;
    System.out.println("Mult, add, divide op test result : "+case1a);
    System.out.println("Mod check result "+mod);
  
    
    //Test case 2; 
    
    int a = 10, b;
    double c, d = .10;
    System.out.println("Printing Test 2 results....");
    System.out.println(a+(b=4));
    System.out.println(d);
    
    //Test case 3;
    System.out.println("Test 3 results...");
    System.out.println((double)((a*d)*a));
    Double testDbl = calculate(a,d);
    System.out.println(testDbl.toString());
    System.out.println(testDbl.getClass().getName());
  
    //Test case 4; 
    System.out.println("Test 4 results...");
    System.out.println(getMax(14,18));
    
    //Test Case 5;
    System.out.println("Test 5 results...");
    System.out.println(getMax(18,calculate(a,d).intValue()));
    System.out.println(getMax(18,(int)(calculate((int)10.0,d).doubleValue())));
    
    //Test Case 6; 
    String s = "hello";
    Double dblObj = new Double(1.0);
    Double dblObj2 = new Double(1);
    System.out.println(dblObj.toString());
    System.out.println(dblObj2.toString());
    if(dblObj.hashCode()==dblObj2.hashCode()){
      System.out.println("True: Passed Test Case 6 hashCode() check");
    }
                       
  }
  
  //Helper methods 
  
  public static Double calculate(int x, double y){
    return (x*y)*x;
  }
  
  public static int getMax(int x, int y){
    if ((x-y)>0){
      return x;
    }
    else{
      return y;
    }
  }
  
  
}