package Ejercicio_02_Streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsAssignment {

    public static void main(String[] args) {
        System.out.println("Starting homework 2");
        resolveExercise1();
        resolveExercise2();
        resolveExercise3();
        resolveExercise4();
        resolveExercise5();
        resolveExercise6();
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

    public static void resolveExercise5(){
        Optional<String> grade1 = getGrade(50);
        Optional<String> grade2 = getGrade(55);

        System.out.println();
        System.out.println("Exercise 5");
        System.out.println(grade1.orElse("UNKNOWN"));

        if(grade2.isPresent()){
            grade2.ifPresent(System.out::println);
        }else{
            System.out.println(grade2.orElse("EMPTY"));
        }

        System.out.println();
    }

    public static Optional<String> getGrade(int marks){
        Optional<String> grade = Optional.empty();
        if(marks > 50){
            grade = Optional.of("PASS");
        } else {
            grade.of("FAIL"); //Esto equivale a Optional.of("FAIL")
        }                            //Pero se ignora el resultado y no se asigna a la variable grade
        return grade;
    }

    public static void resolveExercise6(){
        List<Book> books = Arrays.asList(
                                    new Book("Thinking in Java", 30),
                                    new Book("Java in 24 hrs", 20),
                                    new Book("Java recipes", 10));

        //Esta opcion es correcta ya que en caso de no existir libros que cumplan el criterio devuelve 0.0
        Double averagePrice1 = books.stream()
                .filter(b -> b.getPrice() > 10)
                .collect(Collectors.averagingDouble(Book::getPrice));

        //Esta opcion es mejor porque esta preparada en caso de que no existan libros que cumplan el criterio
        Double averagePrice2 = books.stream()
                .filter(b -> b.getPrice() > 10)
                .mapToDouble(Book::getPrice)
                .average()
                .orElse(0.0);

        Double averagePrice3 = books.stream()
                .filter(b -> b.getPrice() > 90)
                .mapToDouble(Book::getPrice)
                .average()
                .orElse(0.0);

        System.out.println("Exercise 6");
        System.out.println("The average price >10 is: " + averagePrice1 );
        System.out.println("The average price >10 is: " + averagePrice2 );
        System.out.println("The average price >90 is: " + averagePrice3 );
        System.out.println();

    }

}
