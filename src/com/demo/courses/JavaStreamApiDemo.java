package com.demo.courses;

import com.demo.annotations.CallByFrameWork;
import com.demo.bean.Employee;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


/**
 * Created by I317726 on 3/3/2019.
 */
public class JavaStreamApiDemo extends AbstractCourse {

    @CallByFrameWork(callingSequence = 1)
    private void filterEmployeesById() {
       
        Integer idTobeTraced = 3;
        Predicate<Employee> employeePredicate = employee -> employee.getId() == idTobeTraced;
        List<Employee> filteredList = employeeList.stream().filter(employeePredicate).collect(Collectors.toList());
        System.out.println("Filtered Employee is : - "+ filteredList.get(0).getName());
    }


    @CallByFrameWork(callingSequence = 2)

    private void countEmployeesForCriteria() {
       
        Predicate<Employee> employeePredicate = (employee -> (new BigDecimal(50000).compareTo(employee.getSalary())) ==0);
        Long empCount =  employeeList.stream().filter(employeePredicate).count();
        System.out.print("No.of Employee that satisfy the Predicate -  "+ empCount);
    }

    @CallByFrameWork(callingSequence = 3)

    private void hikeSalaryForAllEmployeesUsingNoPeek() {
       
        System.out.print("Hiked salary is - \n");
        employeeList.stream().forEach(e -> e.hikeSalary(new BigDecimal(1000)));
        employeeList.stream().forEach(e -> System.out.println(e.getSalary()));


    }

    @CallByFrameWork(callingSequence = 4)

    private void hikeSalaryForAllEmployeesUsingPeek() {
       

        employeeList.stream()
                .peek(e -> e.hikeSalary(new BigDecimal(1000)))
                .peek(e -> System.out.println(e.getSalary()))
                .collect(Collectors.toList());
    }

    @CallByFrameWork(callingSequence = 5)

    private void findMaxSalary() {
       



        Employee maxSalEmp = employeeList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Max salary recipient - "+ maxSalEmp.getName());
    }


    @CallByFrameWork(callingSequence = 6)

    private void findAverageSalary() {
        Double avgSalary  = employeeList.stream().collect(Collectors.averagingDouble(emp -> emp.getSalary().doubleValue()));
        System.out.println("Average salary is - "+ avgSalary);
    }


    @CallByFrameWork(callingSequence = 7)

    private void addAllSalaries() {
        Double totalSalaries = employeeList.stream().collect(Collectors.summingDouble(emp -> emp.getSalary().doubleValue()));
        System.out.println("Total salary is - "+ totalSalaries);
    }


    @CallByFrameWork(callingSequence = 8)

    private void addAllSalariesWithCriteria() {
        Predicate<Employee>  employeePredicate = employee -> !"fanny".equalsIgnoreCase(employee.getName());
        Double totalSalariesWithCriteria = employeeList.stream()
                                                        .filter(employeePredicate)
                                                         .collect(Collectors.summingDouble(e -> e.getSalary().doubleValue()));



        System.out.println("Total salary with criteria - "+ totalSalariesWithCriteria);
    }




    @CallByFrameWork(callingSequence = 10)
    private void extractAllSalariesAndPrint() {
       
        //Extract property of an object using map and collect into a List
        List<BigDecimal> salaries = employeeList.stream()
                                    .map(Employee::getSalary)
                                    .collect(Collectors.toList());

        salaries.forEach(System.out::println);


    }



    @CallByFrameWork(callingSequence = 11)

    private void getAllSalaryStats() {

       
        List<Double> salaries = employeeList.stream()
                                            .map(e -> e.getSalary().doubleValue())
                                            .collect(Collectors.toList());

        DoubleSummaryStatistics doubleSummaryStatistics = salaries.stream().collect(Collectors.summarizingDouble(Double::doubleValue));

        System.out.println("Average Salary by stats" + doubleSummaryStatistics.getAverage());
        System.out.println("Max Salary by stats" + doubleSummaryStatistics.getMax());
        System.out.println("Min Salary by stats" + doubleSummaryStatistics.getMin());
        System.out.println("Count Salary by stats" + doubleSummaryStatistics.getCount());

    }



    @CallByFrameWork(callingSequence = 12)

    private void groupEmployeeByDepartment() {

       
        Map<String , List<Employee>> groupEmployeeByDept = employeeList.stream().collect(groupingBy(Employee::getDepartment));

       groupEmployeeByDept.forEach((s, employees) -> System.out.println(s + ": " + employees.size()));


    }



    @CallByFrameWork(callingSequence = 13)

    private void countEmployeeByDepartment() {

       
        Map<String,Long> countEmployeeByDept = employeeList.stream().collect(groupingBy(Employee::getDepartment, counting()));

        System.out.println(countEmployeeByDept);
        //  groupEmployeeByDept.forEach((s, employees) -> System.out.println(s + ": " + employees.size()));


    }



}
