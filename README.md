# Java8 Reference
This is my own reference to the new contructs and features in java 8

## Interfaces and Lambda Expressions
#### Default methods in interfaces

Java 8 has introduced default methods to interfaces. These methods are identical to concrete methods in classes. They are declared with the keyword default.

```java
interface INewInterface{
  void method1();
  
  String method2();
  
  default String defaultMethod(){
      return "Hello to java8 default methods";
  }
}
```

####Static methods in interfaces
Java 8 allows static methods in interfaces. These can be used as helper methods.
```java
interface INewInterface{
  static String helperMethod(){
      return "Hello to java8 static interface methods";
  }
}
```

####Functional interfaces
A funtional interface is an interface that contain **only one** abstract method. These are crucial for defining lamda expession.

```java
@FunctionalInterface
interface IFuntionalInterface<R,S,T>{
  R funtion(S s, T t); //any number of arguments and any return type
  default void method1(){
    ...
  }
  default void method2(){
    ...
  }
}  
```
Note that the ```@FunctionalInterface``` is for the compiler to ensure that the interface only declares one abstract method.  

####Lamda expressions
Consider the naive way of creating a thread. 

```java
new Thread(new Runnable() {
      @Override
      public void run() {
        //Do some stuff
        System.out.println("Hello");
      }
    }).start();
```
Runnable is a funtional interface. Thus in java 8 the above code is equivalent to using the following lamda expression.

```java
new Thread(()->{        
          //Do some stuff
          System.out.println("Hello");
        }).start
```
In general, given a funcional interface
```java 
interface IFuntionalInterface<R,S,T>{
  R funtion(S s, T t); //any number of arguments and any return type
}
```
The following ways for creating a concrete instance of the interface are equivalent

```java
public class Implementor<R,S,T> implements IFuntionalInterface<R,S,T>{
  @Override                                                            
  public R funtion(S s, T t){
    //do sum stuff example
    R r = new R(s, t);
    return r;
  }
}
Implementor i = new Implementor();
i.funtion(s1,t1);
```

```java 
IFuntionalInterface i = new Implementor(){
  @Override                                                            
  public R funtion(S s, T t){
    //do sum stuff example
    R r = new R(s, t);
    return r;
  }
};
i.funtion(s1,t1);
```
```java 
IFuntionalInterface i = (s,t) -> {   
    //do sum stuff example
    R r = new R(s, t);
    return r;
};
```
