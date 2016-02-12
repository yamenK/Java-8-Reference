package dev.yamenk.java8.lamdas;

public class LamdaSimpleTest {

	public static void main(String[] args) {
		// --------------------Anonymous inner class---------------------------
		SimpleFunctionalInterface anonymousImplementor = new SimpleFunctionalInterface() {

			@Override
			public void method(String param) {
				System.out.println("Hello to functional interfaces from: " + param);
			}
		};
		anonymousImplementor.method("anonymous inner class");

		
		// ------------------Class implementing interface------------------
		SimpleFunctionalInterface classImplementor = new Implementor();
		classImplementor.method("implementor class");

		
		// ------------------------Lamda-----------------------------
		SimpleFunctionalInterface lamdaImplementor = (s) -> {System.out.println("Hello to functional interfaces from: " + s);};
		lamdaImplementor = (s) -> System.out.println("Hello to functional interfaces from: " + s);//can ignore curly brackets in case of one line implementation
		lamdaImplementor.method("Lamda");
	}

	public static class Implementor implements SimpleFunctionalInterface {

		public Implementor() {
		}

		@Override
		public void method(String param) {
			System.out.println("Hello to functional interfaces from: " + param);
		}

	}

}
