package Ejercicio_01_Lambdas;

import java.util.*;
import java.util.function.*;

public class LambdasAssigment {
    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    public static void staticMR(){
        List<Integer> numberList = Arrays.asList(1,2,7,4,5);
        System.out.println("Lista sin ordenar: " + numberList);

        Consumer<List<Integer>> sortList = list -> Collections.sort(list);
        sortList.accept(numberList);
        System.out.println("Lista ordenada: " + numberList);

        numberList = Arrays.asList(1,2,7,4,5);
        System.out.println("Lista sin ordenar para MR: " + numberList);

        Consumer<List<Integer>> sortListMR = Collections::sort;
        sortListMR.accept(numberList);
        System.out.println("Lista ordenada por MR: " + numberList);

        System.out.println();
    }

    public static void boundMR(){
        String name = "Mr. Joe Bloggs";
        Predicate<String> nameStartWith = word -> name.startsWith(word);

        System.out.println(nameStartWith.test("Mr."));
        System.out.println(nameStartWith.test("Ms."));

        Predicate<String> nameStartWithMR = name::startsWith;
        System.out.println(nameStartWithMR.test("Mr."));
        System.out.println(nameStartWithMR.test("Ms."));

        System.out.println();
    }

    public static void unboundMR(){
        Predicate<String> isEmpty = text -> text.isEmpty();
        System.out.println(isEmpty.test(""));
        System.out.println(isEmpty.test("xyz"));

        Predicate<String> isEmptyMR = String::isEmpty;
        System.out.println(isEmptyMR.test(""));
        System.out.println(isEmptyMR.test("xyz"));

        System.out.println();

        BiPredicate<String, String> textStartWith = (texto1, texto2) -> texto1.startsWith(texto2);
        System.out.println(textStartWith.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(textStartWith.test("Mr. Joe Bloggs", "Ms."));

        BiPredicate<String, String> textStartWithMR = String::startsWith;
        System.out.println(textStartWithMR.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(textStartWithMR.test("Mr. Joe Bloggs", "Ms."));

        System.out.println();
    }

    public static void constructorMR(){
        Supplier<List<String>> newListSupplier = () -> new ArrayList<>();
        List<String> list = newListSupplier.get();
        list.add("Lambda");
        System.out.println(list);

        Supplier<List<String>> newListSupplierMR = ArrayList::new;
        List<String> listMR = newListSupplierMR.get();
        listMR.add("Method Reference");
        System.out.println(listMR);

        System.out.println();

        Function<Integer,List<String>> newListFunction = size -> new ArrayList<>(size);
        List<String> list2 = newListFunction.apply(10);
        list2.add("Lambda");
        System.out.println(list2);
        System.out.println("La capacidad de la lista es: " + list2.size());

        Function<Integer,List<String>> newListFunctionMR = ArrayList::new;
        List<String> list2MR = newListFunctionMR.apply(10);
        list2MR.add("Method Reference");
        System.out.println(list2MR);
        System.out.println("La capacidad de la lista es: " + list2MR.size());
    }
}
