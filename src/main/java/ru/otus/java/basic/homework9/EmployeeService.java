package ru.otus.java.basic.homework9;

import java.util.List;

public interface EmployeeService {
    List<String> nameEmployees(List<Employee> employees);

    List<Employee> employeesAgeGreaterThanOrEqual(List<Employee> employees, int age);

    boolean isAverageAgeGreaterThan(List<Employee> employees, double minAverageAge);

    Employee youngEmployee(List<Employee> employees);

}
