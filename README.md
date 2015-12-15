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
        }).start();
```
In general, given a funcional interface
```java 
interface IFuntionalInterface<R,S,T>{
  R funtion(S s, T t); //any number of arguments and any return type
}
```
The following ways for creating an instance of a concrete class that implements the interface are equivalent

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

####Collections, Streams, and Filters

#####Iterating collections

Java8 has added a new method to the Iterable interface; ```forEach(Consumer<? super T> action)``` to simplify the job of iterating over a collection.

```java
myCollection.forEach(e ->System.out.println(e));
```
####Streams
```java.util.stream``` package in particluar the ```Stream``` interface adds funtional-style operations.

####Filters
The direct way to loop over a collection and perform some actions on some elements
```java
for(E e: myCollection){
  if(meetsCondition1(e) && meetsCondition2(e)){
    doSomething(e);
  }
}
```
With the introduction of streams this can be reduced to a more funtional style code.
```java
myColletion.stream()
          .filter(e -> meetsCondition1(e))
          .filter(e-> meetsCondition2(e)) //Or can be combined with above
          .forEach(e -> doSomething(e));
```

Note: Operations on streams are lazy. They are only executed when a **Terminal operation** such as the ```forEach```is called. Non-terminal operations are refered to as intermediate operations.


####Lambda Built-in Functional Interfaces
**Predicate**: an expression that returns a boolean.
```java
@FunctionalInterface
public interface Predicate<T> {
   public boolean test(T t);
}

Stream<T> filter(Predicate<? super T> predicate)
```
**Consumer**: an expression that performs an action on a passed argument and has a void return type
```java
@FunctionalInterface
public interface Consumer<T> {
     public void accept(T t);
}
```

**Funtion**: similar to a a Consumer but return a non void type.
```java
@FunctionalInterface
public interface Function<T,R> {
   public R apply(T t);
 }
```

**Supplier**: returns an object without being supplied an argument
```java
@FunctionalInterface
public interface Supplier<T> {
   public T get();
}
```
**Primitive Interface**: A set of primitive interfaces that mirror the above interfaces.These are provided to avoid performance degradation to auto boxing/unboxing.
example:
```java
@FunctionalInterface
public interface DoubleFunction<R> {
   public R apply(double value); //This methods accepts a primitive double as input
}
```
**Binary Types**: Binary version of the standard interfaces.
```java
@FunctionalInterface
public interface BiPredicate<T, U> {
   public boolean test(T t, U u);
}
```

**Unary Operator**: A special case of ```Funtion``` interface returning the same type as the input
```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T,T> {
     @Override
     public T apply(T t);
}
```

####Lamda Operations

A Stream is an sequence of elements supporting a set of sequential and parallel operations. A stream is formed from a collection that represents the source of the stream. A set of intermediate operations(filter(), map(), peek()) is performed on a stream and ended by a terminal function(forEach(), anyMatch()). Note a stream can only be used once(i.e only one terminal operation can be performed on a stream)

#####Transforming a Stream
```Function<? super T,? extends R> mapper``` performs an operation on a stream and extracts objects.

#####Peeking
```peek(Consumer<? super T> action)``` performs an action on the stream and return the elements.

#####Searching
```findFirst(), allMatch()``` ...

#####Optional Class
```Optional<T>``` A new class that has been introduced to facillate the usual way for guarding against NPE. This class is a wrapper for an object. It may contain a null value. Check javadoc for main methods [isPresent()](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#isPresent--), [ifPresent()](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#ifPresent-java.util.function.Consumer-), [orElse()](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElse-T-)

#####Stream Aggregation Methods
```count()``` return number of elements in stream
```max(Comparator<? super T> comparator)```



