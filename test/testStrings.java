//Tester for String implementation 
//Pretty much tests for all the string properties that we have in the vTabel layout for java.lang.String 


public class testStrings{ 
  
  public static void main (String[] args){
    String str = "Test string number 1"; 
    String str2 = new String ("Test string number 1");
    String strSub = "Test string";
    strSub = strSub + " number 1";  
    //String strSub2 = str.substring(5); 
    
    //Test case 1
    
    System.out.println("++++++++Test Case 1++++++++");
    if(str.equals(str2)&&str.equals(strSub)){
      System.out.println("Test 1 Passed: String comparison,concat"); 
    }
    else{
      System.out.println("Test 1 Failed...something went wrong with string comparison or string concat");
    }
    
    //Test case 2
    
    System.out.println("++++++++Test Case 2++++++++");
    Object obj = str; 
    if(obj.toString().equals(str)){
      System.out.println("Test 2 Passed for Obj toString()");
    }
    else{
      System.out.println("Test 2 Failed: something went wrong with Object toString test");
    } 
    
    
    //Test Case 3
    
    String a= "koolaid!";
    String b= "koolaid"; //no '!' in this one 
    System.out.println("++++++++Test Case 3++++++++");
    if((a.length()!=b.length())&&(a.charAt(0)==b.charAt(0))){
      System.out.println("Test 3 Passed for string length and charAt implementation");
    }
    else{
      System.out.println("Test 3 Failed for string length and charAt implementation");
    }
    
    //Test Case 4
    //Concat chars and spaces directly to a string...ie string builder test 
    System.out.println("++++++++Test Case 4++++++++");
    String test = "rubber duck";
    String test2 = "rubber" + " "+'d'+'u'+'c'+"k";
    String test3 = 'r'+'u'+"bber duck";  //this should not be the same as "rubber duck" when appended
    //System.out.println(test3);
    //System.out.println(test2);
    if(test.equals(test2)&&!(test.equals(test3))){
      System.out.println("Test 4 Passed for Java string builder concatenation of chars to a string");
    }
    else{
      System.out.println("Test 4 Failed for Java string builder concatenation of chars to a string");
    }
    
    //Test Case 5
    //Char[] to string and testing helper method translation
    
    System.out.println("++++++++Test Case 5++++++++");
    //char[] stringData = {'k','o','o','l','a','i','d','!'};
    char stringData[] = {'k','o','o','l','a','i','d','!'};
    String strTest2 = "koolaid!";
    testCharConversion(stringData, strTest2);
    
    
    //Test Case 6
    System.out.println("++++++++Test Case 6++++++++");
    String str6 = "Hello World";
    Object obj6 = new String("Hello World"); //obj7 is of class string
    Object obj6a = new Object();
    if(str6.getClass() == obj6.getClass() && obj6.getClass()!=obj6a.getClass()){
      System.out.println("Test 6 Passed for getClass() and object/string cast");
    }
    else{
      System.out.println("Test 6 Failed for getClass() and object/string cast");
    }
    
    //Test Case 7
    //Hash code test; if two objects are equals hashCode() should be the same
    System.out.println("++++++++Test Case 7++++++++");
    if(obj6.hashCode()==str6.hashCode()){
      System.out.println("Test 7 passed for hashCode()");
    }
    else{
      System.out.println("Test 7 failed for hashCode()");
    }
       
  }
  
  //helper method for Test 5
  public static void testCharConversion(char [] cArray, String str){
    String strFromChar = new String (cArray);
    if(str.equals(strFromChar)){
      System.out.println("Test 5 String constructor and char[] to string conversion  passed");
    }
    else{
      System.out.println("Test 5 failed: String constructor and char to string conversion");
    }
  }
  
  
}

