

public class testArray{
  public static void main (String[] args){
    
    int [] nums = new int[5];
    int [] nums2 = {1,2,3,4,5};
    
    //Test Case 1 
    
    if(nums!=nums2 && nums.length==nums2.length){
      System.out.println("Test 1 passed for simple instanstiation and equals check");
    }
    else{
      System.out.println("Failed equals or length check");
    }
    
    //Test Case 2 
    //Turn array of double to array of ints
    System.out.println("Test 2 converts double[]to int[]");
    double [] nums3 = {1.0,2.7,4,5.0}; //nums 3 size is smaller than nums
    int i =0;
    while(i<nums3.length){
      nums[i] = (int) nums3[i];
      System.out.println(nums3[i]+" changed to "+nums[i]);
      i++;
    }
    System.out.println("This should print 0...value = "+nums[4]);
    System.out.println(nums[1] +" is from "+ nums.getClass().getName());
    System.out.println(nums3[1] + " is from "+ nums3.getClass().getName());
    
    //Test Case 3 
    //Checking type and getClass for array of objects/strings
    System.out.println("Test 3 results...");
    String strArr[] = {"One","Two","Three","Four"};
    System.out.println("Class is:"+strArr.getClass().getName());
    System.out.println("Super class is: "+strArr.getClass().getSuperclass().getName());
    
    //Test Case 4 
    int [][]a = new int [2][2];
    int []b[]= new int [2][2];
    a[0][0]=15;
    a[1][1]=10;
    b[0][0]=15;
    b[1][1]=10;
    if(a[0][0]==b[0][0] && a[1][1]==b[1][1]){
      System.out.println("2-D Array should print 15 and 10");
      System.out.println("Output:"+a[0][0]+" and "+b[1][1]);
    }
                           
    
  }
}
