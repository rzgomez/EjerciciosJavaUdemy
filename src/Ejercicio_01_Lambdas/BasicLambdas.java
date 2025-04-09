package Ejercicio_01_Lambdas;


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

        Person p1 = new Person(33, "Mike", 1.8);
        Person p2 = new Person(13, "Ann", 1.4);

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
    }

    private void sortName(List<Person> personList){}

    private void sortAge(List<Person> personList){}

    private void sortHeight(List<Person> personList){}

    private List<Person> getPeople(){
    return null;}
}
