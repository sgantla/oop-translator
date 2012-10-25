//Tester for String implementation 
//Pretty much tests for all the string properties that we have in the vTabel layout for java.lang.String 


public class testStringBasic{ 
  
  public static void main (String[] args){
    String str = "Test string number 1"; 
    String str2 = new String ("Test string number 1");
    String strSub = "Test string";
    strSub = strSub + " number 1";
    //String strSub2 = str.substring(5); 
    
    //Test case 1 string concatenation and instantiation 
    System.out.println("Test Case 1...All strings printed should be the same");
    System.out.println(str);
    System.out.println(str2);
    System.out.println(strSub); 
    //System.out.println(strSub2); //substring method? do we support?
    
    
    //Test case 2 str as an object and calling obj.toSting()
    
    System.out.println("Test Case 2...Printed String should be the same as from test case 1");
    Object obj = str; 
    System.out.println(obj.toString());
    
    //Test Case 3
    
    String a= "koolaid!";
    String b= "koolaid";
    System.out.println("Test Case 3...");
    System.out.println(a.length());
    System.out.println(b.length());
    System.out.println(a.charAt(0));
    System.out.println(b.charAt(0));
    
    //Test Case 4
    System.out.println("Test Case 4...Append chars and spaces directly to a string;string builder test ");
    String test = "rubber duck";
    String test2 = "rubber" +" "+'d'+'u'+'c'+"k";
    String test3 = 'r'+'u'+"bber duck"; 
    String test4 = (char)82+"ubber duck";
    System.out.println("Original string: "+test);
    System.out.println("Original string + chars + string: "+test2);
    System.out.println("Chars + string: "+test3);
    System.out.println(test4);
    
    
    //Test Case 5
    System.out.println("Test Case 5 ....Char[] to a String");
    char stringData[] = {'k','o','o','l','a','i','d','!'};
    String str5a = "koolaid!";
    String str5b = new String(stringData);
    System.out.println("Original string: "+str5a);
    System.out.println("String from char[]: "+str5b);
    
    
    //Test Case 6
    System.out.println("Test Case 7 ... getClass() check on String and Object");
    String str7 = "Hello World";
    Object obj7 = new String("Hello World"); //obj7 is of class string
    Object obj7a = new Object();
    System.out.println(str7+ " is of type "+str7.getClass().getName());
    System.out.println(obj7+ " is of type "+obj7.getClass().getName());                   
    System.out.println(obj7a.toString()+ " is of type "+obj7a.getClass().getName());
    
    //Test Case 8
    //Hash code test; if two objects are equals hashCode() should be the same
    System.out.println("Test 8 Hash code test");
    if(obj7.hashCode()==str7.hashCode()){
      System.out.println("hashCode is the same for both objects");
    }
    else{
      System.out.println("hashCode is not the same for objects");
    }
    
  }
  
  
  
}

