//test dependency resolution and class path,etc with foo.java and boo.java 
//foo.java, boo.java, testDependancy are in the same package currently(i.e. default package) 


public class testDependancy{
  public static void main(String[] args){
    foo fooOne = new foo();
    boo booOne = new boo();
    System.out.println("Calling foo.fooMethod1(5,5): "+fooOne.fooMethod1(5,5));
    System.out.println("Calling foo.fooMethod2(5): "+fooOne.fooMethod2(5));
    System.out.println("Calling boo.booMethod1(foo,5): "+booOne.booMethod1(fooOne,5));
    System.out.println("Calling boo.booMethod2(5): "+booOne.booMethod2(5));
    System.out.println("fooOne is of type "+fooOne.getClass().getName());
    System.out.println("booOne is of type "+booOne.getClass().getName());
  }
}