package org.ayush.ms.main.applications;

import org.ayush.ms.Employee;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class MsApplication {

    public static void main(String[] args) {
        List<Employee> employeeList = List.of(
                new Employee("A", 10),
                new Employee("B", 12),
                new Employee("C", 20),
                new Employee("D", 100),
                new Employee("E", 20)
        );
        findSecond(employeeList).forEach(
                employee -> System.out.println(employee + " ")
        );
    }

    private static List<Employee> findSecond(List<Employee> employeeList) {
        List<Integer> distinctSalaries = employeeList.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
        if (distinctSalaries.size() < 2) {
            return List.of();
        }
        int secondHighestSalary = distinctSalaries.get(1);
        return employeeList.stream()
                .filter(employee -> employee.getSalary() == secondHighestSalary)
                .toList();
    }

}
