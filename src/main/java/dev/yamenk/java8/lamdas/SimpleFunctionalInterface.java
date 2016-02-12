package dev.yamenk.java8.lamdas;

@FunctionalInterface
public interface SimpleFunctionalInterface {
	void method(String s);

	static void staticMethod() {
		System.out.println("Interface static method");
	}

	default void defaultMethod() {
		System.out.println("Interface default method");
	}

	default void secondDefaultMethod() {
		System.out.println("Interface second default method");
	}
}
