package dev.yamenk.java8.lamdas;

import java.util.Random;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class BuiltinFunctionalInterfacesTest {

	public static void main(String[] args) {
		System.out.println("--------------------Predicate---------------------------------\n");
		String prefix = "http://";
		Predicate<String> startsWithTester = s -> s.startsWith(prefix);

		System.out.println("ABCDE " + startsWithTester.test("ABCDE"));
		System.out.println("http://www.google.com/ " + startsWithTester.test("http://www.google.com/"));

		System.out.println("\n--------------------Consumer---------------------------------\n");

		Consumer<String> vegiConsumer = (food) -> {
			boolean contains = food.contains("meat");
			if (contains) {
				System.out.println(food + " is Yukkky");
			} else {
				System.out.println(food + " is Yummi");
			}
		};

		vegiConsumer.accept("meat");
		vegiConsumer.accept("apples");

		System.out.println("\n--------------------Function---------------------------------\n");
		Function<String, Long> f = Long::parseLong;

		System.out.println("\"1234\" :" + f.apply("1234"));

		System.out.println("\n--------------------Supplier---------------------------------\n");
		Random rand = new Random(50);
		Supplier<Integer> keyGenerator = () -> rand.nextInt(100);
		System.out.println(keyGenerator.get() + " " + keyGenerator.get() + " " + keyGenerator.get());

		System.out.println("\n--------------------DoubleFunction---------------------------------\n");

		DoubleFunction<String> doubleFunction = String::valueOf;
		System.out.println("Double funtion of primitive double [2000]: " + doubleFunction.apply(2000));

		System.out.println("\n--------------------BiPredicate---------------------------------\n");
		BiPredicate<String, String> equalString = (s1, s2) -> s1.equals(s2);
		System.out.println("Hello equals NONO " + equalString.test("Hello", "NONO"));

		System.out.println("\n--------------------UnaryOperator---------------------------------\n");
		UnaryOperator<String> stringtrimmer = s -> s.trim();
		String unTrimmedString = "   A BB CC  ";
		System.out.println(unTrimmedString + " trimmed=" + stringtrimmer.apply(unTrimmedString));
	}

}
