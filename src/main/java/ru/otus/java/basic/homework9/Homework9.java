package ru.otus.java.basic.homework9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Homework9 {
    public static void main(String[] args) {

        System.out.println("\nMethod 1 - ArrayList<Integer> numbersSequence(int min, int max)");
        try {
            System.out.println(ListService.numbersSequence(-1, 5));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(ListService.numbersSequence(0, 5));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(ListService.numbersSequence(5, 1));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\nMethod 2 - int sumNumMoreThanFive(List<Integer> list)");
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        IntStream.range(0, 5).forEach(i -> list.add(random.nextInt(1, 20)));
        System.out.printf("Список: %s; sum = %d%n", list, ListService.sumNumMoreThanFive(list));


        System.out.println("\nMethod 3 - List<Integer> rewriteNumbers(int num, List<Integer> list)");
        List<Integer> list2 = new ArrayList<>() {
            {
                IntStream.range(0, 5).forEach(this::add);
            }
        };
        System.out.printf("List before rewrite: %s%n", list2);
        System.out.printf("List after rewrite: %s%n", ListService.rewriteNumbers(7, list2));


        System.out.println("\nMethod 4 - List<Integer> increaseEl(int num, List<Integer> list)");
        List<Integer> list3 = new ArrayList<>() {
            {
                IntStream.range(0, 7).forEach(this::add);
            }
        };
        System.out.printf("List before increaseEl: %s%n", list3);
        System.out.printf("List after increaseEl: %s%n", ListService.increaseEl(3, list3));

        System.out.println("\nEmployee");
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employees = List.of(
                new Employee("Alena", 25),
                new Employee("Inna", 35),
                new Employee("Olga", 23),
                new Employee("Oleg", 46),
                new Employee("Boris", 37)
        );

        System.out.println(employeeService.nameEmployees(employees));
        System.out.println(employeeService.employeesAgeGreaterThanOrEqual(employees, 35));
        System.out.println(employeeService.isAverageAgeGreaterThan(employees, 40.5));
        System.out.println(employeeService.youngEmployee(employees));

    }
}
