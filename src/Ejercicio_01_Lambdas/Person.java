package Ejercicio_01_Lambdas;

public class Person {
    private Integer age;
    private String name;
    private Double height;

    Person(Integer age, String name, Double height) {
        this.age = age;
        this.name = name;
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public Double getHeight() {
        return height;
    }
}
