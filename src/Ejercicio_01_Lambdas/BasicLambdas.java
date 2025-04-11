package Ejercicio_01_Lambdas;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Printable<T> {
    void print(T t);
}

interface Retrievable<T> {
    T retrieve();
}

interface Evaluate<T> {
    boolean isNegative(T t);
}

interface Functionable<T,R> {
    R apply(T t);
}


public class BasicLambdas {
    public static void main(String[] args) {
        consumer();
        supplier();
        predicate();
        function();

        List<Person> listPeople = getPeople();
        sortAge(listPeople);
        sortName(listPeople);
        sortHeight(listPeople);
    }

    public static void consumer(){
        Printable<String> printableL = s -> System.out.println(s); //lambda
        printableL.print("Printable lambda");

        Consumer<String> consumerL = s -> System.out.println(s); //lamda
        consumerL.accept("Consumer lambda");

        Consumer<String> consumerMR = System.out::println; //method reference
        consumerMR.accept("Consumer method reference");

        System.out.println();
    }

    public static void supplier(){
        Retrievable<Integer> retrievableL = () ->  77;
        System.out.println("Retrievable:" + retrievableL.retrieve());

        Supplier<Integer> supplierL = () -> 77;
        System.out.println("Supplier:" + supplierL.get());

        System.out.println();
    }

    public static void predicate(){
        Evaluate<Integer> evaluateL = integer -> integer < 0;
        System.out.println("Is negative:" + evaluateL.isNegative(-1)); //true
        System.out.println("Is negative:" + evaluateL.isNegative(1));; //false

        Predicate<Integer> predicateL = integer -> integer < 0;
        System.out.println("Is negative:" + predicateL.test(-1)); //true
        System.out.println("Is negative:" + predicateL.test(1)); //false

        int number = 4;
        System.out.println("Is " + number + " even? " + check(number, n -> n % 2 == 0));
        number = 7;
        System.out.println("Is " + number + " even? " + check(number, n -> n % 2 == 0));

        String txt = "Mr. Jose Bloggs";
        System.out.println( txt + " begins with 'Mr.' ? " + check(txt, str -> str.startsWith("Mr.")));
        txt = "Ms. Ann Bloggs";
        System.out.println( txt + " begins with 'Mr.' ? " + check(txt, str -> str.startsWith("Mr.")));

        Person p1 = new Person("Mike",33,  1.8);
        Person p2 = new Person("Ann",13,  1.4);

        System.out.println( "Is " + p1.getName() + " an adult? " + check(p1.getAge(), i -> i >= 18));
        System.out.println( "Is " + p2.getName() + " an adult? " + check(p2.getAge(), i -> i >= 18));

        System.out.println();
    }

    public static <T> boolean check(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }
    public static void function(){
        Functionable<Integer,String> functionableL = number -> "Number is: " + number;
        System.out.println("Functionable: " + functionableL.apply(25));

        Function<Integer, String> functionL = number -> "Number is: " + number;
        System.out.println("Function: " + functionL.apply(25));
        System.out.println();
    }

    private static void sortAge(List<Person> personList){
//        personList.sort(Comparator.comparing(person -> person.getAge())); //lambda
        personList.sort(Comparator.comparing(Person::getAge)); //method reference

//        personList.forEach(person -> System.out.println(person)); //lambda
        personList.forEach(System.out::println);
        System.out.println();
    }

    private static void sortName(List<Person> personList){
//        personList.sort(Comparator.comparing(p -> p.getName())); //lambda
        personList.sort(Comparator.comparing(Person::getName)); //method reference

//        personList.forEach(p -> System.out.println(p)); //method reference
        personList.forEach(System.out::println);
        System.out.println();
    }

    private static void sortHeight(List<Person> personList){
//        personList.sort(Comparator.comparing(p -> p.getHeight())); //lambda
        personList.sort(Comparator.comparing(Person::getHeight)); //method reference

//        personList.forEach(p -> System.out.println(p)); //lambda
        personList.forEach(System.out::println); //method reference
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));
        return result;
    }
}
