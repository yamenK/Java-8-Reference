package dev.yamenk.java8.lamdas;

public class LamdaScopesTest {

	private String s = "string field";
	private static String r = "static field";

	public static void main(String[] args) {

		LamdaScopesTest lamdaScopesTest = new LamdaScopesTest();
		lamdaScopesTest.test();
	}

	private void test() {
		String localVar = "Local variable";
		SimpleFunctionalInterface functionalInterface = (s) -> {
			System.out.println("Lamdas have access to fields. Here's a proof: " + s + "  " + r);
			System.out.println("Lamdas have access to local variables. Here's a proof: " + localVar);
		};

		functionalInterface.method(s);

		// localVar = ""; //Causes a compilation error (Local variable localVar
		// defined in an enclosing scope must be final or effectively final)
	}
}
