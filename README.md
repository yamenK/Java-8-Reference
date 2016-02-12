# Java8 Reference
This is my own reference to the new constructs and features in java 8

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
A funtional interface is an interface that contain **only one** abstract method.

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
Consider the direct way to create a thread. 

```java
new Thread(new Runnable() {
      @Override
      public void run() {
        //Do some stuff
        System.out.println("Hello");
      }
    }).start();
```
Runnable is a functional interface. Thus in java 8 the above code is equivalent to using the following lamda expression.

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
```

```java 
IFuntionalInterface i = new IFuntionalInterface(){
  @Override                                                            
  public R funtion(S s, T t){
    //do sum stuff example
    R r = new R(s, t);
    return r;
  }
};
```
```java 
IFuntionalInterface i = (s,t) -> {   
    //do sum stuff example
    R r = new R(s, t);
    return r;
};
```

####Scopes in Lambda expressions
Similar to anonymous inner classes, class variables can be accessed and changed. Local variables need to be effectively final.

####Collections, Streams, and Filters

#####Iterating collections

Java8 has added a new method to the Iterable interface; ```forEach(Consumer<? super T> action)``` to simplify the job of iterating over a collection.

```java
myCollection.forEach(e ->System.out.println(e));
```
####Streams
```java.util.stream``` package in particluar the ```Stream``` interface adds functional-style operations.

####Filters
The direct way to loop over a collection and perform some actions on some elements
```java
for(E e: myCollection){
  if(meetsCondition1(e) && meetsCondition2(e)){
    doSomething(e);
  }
}
```
With the introduction of streams this can be reduced to a more functional style code.
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

**Function**: similar to a Consumer but return a non void type.
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
**Primitive Interface**: A set of primitive interfaces that mirror the above interfaces.These are provided to avoid performance degradation due to auto boxing/unboxing.
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

**Unary Operator**: A special case of ```Function``` interface returning the same type as the input
```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T,T> {
     @Override
     public T apply(T t);
}
```

####Lamda Operations

A Stream is a sequence of elements supporting a set of sequential and parallel operations. A stream is formed from a collection that represents the source of the stream. A set of intermediate operations(filter(), map(), peek()) is performed on a stream and ended by a terminal function(forEach(), anyMatch()). Note a stream can only be used once(i.e only one terminal operation can be performed on a stream)

#####Transforming a Stream
```map(Function<? super T,? extends R>) mapper``` performs an operation on a stream and extracts objects.

#####Peeking
```peek(Consumer<? super T> action)``` performs an action on the elements of the stream and returns the stream.

#####Searching
```findFirst()```returns the first element in the stream.

``` allMatch(Predicate<? super T> predicate) ```returns if all the elements in the stream match the predicate.

#####Optional Class
```Optional<T>``` A new class that has been introduced to facillate the usual way for guarding against NPE. This class is a wrapper for an object. It may contain a null value. Check javadoc for main methods [isPresent()](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#isPresent--),
[ifPresent(Consumer<? super T> consumer)](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#ifPresent-java.util.function.Consumer-), [orElse()](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElse-T-)

#####Stream Aggregation Methods
```count()``` return number of elements in stream

```max(Comparator<? super T> comparator)```Returns max element in stream

```min(Comparator<? super T> comparator)```Returns min element in stream


#####Sorting
```sorted()``` Sorts the stream

```sorted(Comaprator<? super T> comparator)``` Sorts the stream

```reversed()``` Reverses the stream

#####Collecting data from stream
```collect(Collector<? super T,A,R> collector)``` 

```java
stream().collect(Collectors.toList());
stream().collect(Collectors.toMap());
```
More methods [groupingBy(Function<? super T,? extends K>),  ](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-)
[joining()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#joining--),  [partitioningBy(Predicate<? super T> predicate)](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#partitioningBy-java.util.function.Predicate-)

###Method Reference
Are substitutes for lamda expressions that only call a existing method.

Consider the following way to print a list:
```java
list.foreach(i -> System.out.println(i));
```
Since the lamda expression only calls a predefined method, the above can be simplified using method references to the following:
```java
list.foreach(System.out::println)
```

Method refrences are of four types:

| Kind                                                    | Equivalent Lamda Version         | Method reference  |
| --------------------------------------------------------|:-------------|:-----------------|
| Reference to a static method                            | (param) -> ClassName.staticMethod(param) |ClassName::staticMethod |
| Reference to an instance method of a particular object  | (param) -> instanceObject.method(param)   |instanceObject::method|
| Reference to an instance method of an arbitrary object of a particular type	 | (p)-> p.methodInClassOfTypeP()|P::methodInClassOfTypeP() |
| Reference to a constructor	| (param) -> new P(param)| P::new


###Date and Time API
Java 8 introduced an improved date and time api in java.time. The new api solves concurency issues with the old java.util.Date by making all value classes immutable.

```LocalDate``` represents a date without a time zone. ex:2015-01-12

```LocalTime``` represents a time without a time zone. ex:03:22:55

```LocalDateTime``` a combination of LocalDate and LocalTime.

#####Time zones:

```ZoneId``` is an identifier for a region such as Asia/Tokyo.





