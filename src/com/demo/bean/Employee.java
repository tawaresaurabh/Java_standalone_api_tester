package com.demo.bean;

import java.math.BigDecimal;

/**
 * Created by I317726 on 3/3/2019.
 */
public class Employee {

    private Integer id;
    private String department;
    private String name;
    private BigDecimal salary;

    public Employee(Integer id, String department, String name, BigDecimal salary) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void hikeSalary(BigDecimal hike){
        setSalary(getSalary().add(hike));
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
