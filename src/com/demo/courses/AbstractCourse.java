package com.demo.courses;

import com.demo.annotations.CallByFrameWork;
import com.demo.bean.Employee;
import com.demo.interfaces.CourseEnabled;
import sun.reflect.misc.MethodUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by I317726 on 3/3/2019.
 */


public class AbstractCourse implements CourseEnabled {
    
    protected List<String> defaultStringValues = new ArrayList<>();
    protected List<Integer> defaultIntegerValues = new ArrayList<>();
    protected Map<Object,Object> holderMap = new HashMap<>();
    protected List<Employee> employeeList = new ArrayList<>();
    Object classInstance;


    @Override
    public void processClassIntro() {
        System.out.print("Processing started for learning class - "+classInstance.getClass() + "\n");
    }

    @Override
    public void initProcess() {
        populateStringValues();
        populateIntegerValues();
        populateEmployeeList();
        processClassIntro();
        process();

    }

    @Override
    public void process() {

        Method[] methods =  classInstance.getClass().getDeclaredMethods();
        Predicate<Method> methodPredicate = m -> m.getAnnotation(CallByFrameWork.class)!=null;
        Stream<Method> allMethods = Stream.of(methods);
        allMethods
                .filter(methodPredicate)
                .sorted(Comparator.comparing(method2 -> method2.getAnnotation(CallByFrameWork.class).callingSequence()))
                .peek(method1 -> method1.setAccessible(true))
                .peek(method3 -> System.out.println("\n\n"+ method3.getName()))
                .peek(method -> {
            try {
                method.invoke(classInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }).collect(Collectors.toList());


    }

    @Override
    public void setInstanceInfo(Object o) {
        classInstance = o;
    }

    private void populateIntegerValues() {
        for(int i = 1 ; i<=10;i++){
            defaultIntegerValues.add(i);
        }
    }

    private void populateEmployeeList(){
        Employee employee1= new Employee(1,"Magic", "Nana",new BigDecimal(10000));
        Employee employee2= new Employee(2,"Tank","John",new BigDecimal(30000));
        Employee employee3= new Employee(3,"Fighter","Saber",new BigDecimal(40000));
        Employee employee4= new Employee(4,"Fighter","Fanny",new BigDecimal(50000));
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);
    }


    private void populateStringValues() {
        defaultStringValues.add("one");
        defaultStringValues.add("two");
        defaultStringValues.add("three");
        defaultStringValues.add("four");
        defaultStringValues.add("five");
        defaultStringValues.add("six");
        defaultStringValues.add("seven");
        defaultStringValues.add("eight");
        defaultStringValues.add("nine");
        defaultStringValues.add("ten");
    }



}
