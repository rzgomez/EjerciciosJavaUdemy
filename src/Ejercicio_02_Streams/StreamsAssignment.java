package Ejercicio_02_Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsAssignment {

    public static void main(String[] args) {
        System.out.println("Starting homework 2");
        resolveExercise1();
        resolveExercise2();
        resolveExercise3();
        resolveExercise4();
    }

    public static void resolveExercise1(){
        IntStream range = IntStream.range(0, 5);

        System.out.println("The range is:");
        range.forEach(System.out::println); //Terminal operation

        double average = IntStream.range(0, 5).average().orElse(0.0); //Terminal operation
        System.out.println("The average is:" + average);
        System.out.println();
    }

    public static void resolveExercise2(){
        List<Item> itemList = Arrays.asList(
                new Item(1, "Screw"),
                new Item(2,"Nail"),
                new Item(3,"Bolt"));


        itemList.stream()
                .sorted(Comparator.comparing(Item::getName))
                .map(Item::getName)
                .forEach(System.out::print);
        System.out.println("\n");
    }

    public static void resolveExercise3(){
        Stream<List<String>> streamOfLists = Stream.of(
                Arrays.asList("a", "b"),
                Arrays.asList("d", "c"),
                Arrays.asList("a", "c"));

        streamOfLists
                .filter( list -> list.contains("c"))
                //.peek(list -> System.out.println("\n" + list))
                .flatMap(List::stream)
                .forEach(str -> System.out.print(str + " "));
        System.out.println("\n");
    }

    public static void resolveExercise4(){
        System.out.println("Exercise 4.a");
        System.out.println("The sum is: " + IntStream.of(1, 2, 3).sum());
        System.out.println("The maximum value is: " + IntStream.of(1, 2, 3).max().orElse(0));

        System.out.println("Exercise 4.b");
        List<Person> personList = Arrays.asList(
                new Person("Alan", "Burke", 22),
                new Person("Zoe", "Peters", 20),
                new Person("Peter", "Castle", 29)
        );

        personList.stream()
                //.sorted(Comparator.comparing(Person::getAge))
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        System.out.println("Exercise 4.c");
        List<Integer> ls = Arrays.asList(10, 47, 33, 23);
        int max2 = ls.stream()
                // Optional<Integer> reduce(BinaryOperator<Integer> acc)
                // BinaryOperator<T> extends functional interface BiFunction<T,U,R>
                //    BiFunction's functional method is R apply(T t, U u)
//                .reduce((a, b)->a>b?a:b)
                .reduce(Integer::max)
                .get();
        System.out.println(max2);

        int max3 = ls.stream()
                // Integer reduce(Integer identity, BinaryOperator<Integer> acc)
                // The identity element is both the initial value of the reduction
                // and the default result if there are no elements in the stream.
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(max3);



    }



}
