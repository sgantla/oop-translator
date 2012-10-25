// This is only a simple test for loops 
//This test does not test for having large number of variables within the loop scopes
//This test should be a good starting point

public class testLoops{
  public static void main (String[] args){
    
    System.out.println("Test 1: simple for-loop");
    for(int i=0; i<5; i++){
      System.out.print(i);
    }
    
    System.out.println("\nTest 2: simple while-loop");
    int x = 0;
    while(x<5){
      System.out.print(x);
      x= x +1;
    }
    
    System.out.println("\nTest 3: Enhanced for-loop");
    int[] arr = {0,1,2,3,4};
    for ( int w : arr){
      System.out.print(w);
    }
    
    System.out.println("\nTest 4: Do-while check");
    int z = 0;
    do{
      System.out.print(z);
      z++;
    }while( z < 5 );
    
    
    System.out.println("\nTest 5: Break in a loop(Break at value = 3)");
    int [] numbers = {0, 1, 2, 3, 4, 5};
    for(int num : numbers ){
      if( num == 3){
        break;
      }
      System.out.print( num );
    }
    
    
    
    
    
  }
}