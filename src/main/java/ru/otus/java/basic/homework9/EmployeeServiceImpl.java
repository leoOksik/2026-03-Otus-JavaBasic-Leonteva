package ru.otus.java.basic.homework9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public List<String> nameEmployees(List<Employee> employees) {
        List<String> names = new ArrayList<>();

        for (Employee employee : employees) {
            names.add(employee.getName());
        }
        return names;
    }

    @Override
    public List<Employee> employeesAgeGreaterThanOrEqual(List<Employee> employees, int age) {

        if (employees == null || employees.isEmpty()) {
            return Collections.emptyList();
        }

        if (age <= 0) {
            throw new IllegalArgumentException("Age cannot be negative or zero");
        }
        List<Employee> list = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getAge() >= age) {
                list.add(employee);
            }
        }
        return list;
    }

    @Override
    public boolean isAverageAgeGreaterThan(List<Employee> employees, double minAverageAge) {
        if (employees == null || employees.isEmpty()) {
            return false;
        }

        if (minAverageAge <= 0.0) {
            throw new IllegalArgumentException("Min average age cannot be negative or zero");
        }

        double averageAge = employees.stream()
                .mapToInt(Employee::getAge)
                .average().orElse(0.0);

        return averageAge > minAverageAge;
    }

    @Override
    public Employee youngEmployee(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return null;
        }

        Employee youngest = employees.getFirst();

        for (Employee empl : employees) {
            if (empl.getAge() < youngest.getAge()) {
                youngest = empl;
            }
        }
        return youngest;
    }
}
