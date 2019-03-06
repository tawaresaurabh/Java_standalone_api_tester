package com.demo;


import com.demo.constants.CourseConstant;
import com.demo.courses.*;
import com.demo.interfaces.CourseEnabled;
import com.demo.util.CourseUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class Main {

    private static LinkedHashMap<String ,Integer> CLASS_EXAMPLE_HOLDER = new LinkedHashMap<>();
    //For console, Index sensitive
    private static void populateClassExampleHolder(){

        CLASS_EXAMPLE_HOLDER.put(CourseConstant.COURSE_EXIT, 0);
        CLASS_EXAMPLE_HOLDER.put(CourseConstant.THREAD_EXECUTOR, 1);
        CLASS_EXAMPLE_HOLDER.put(CourseConstant.LAMBDA_EXPRESSION, 2);
        CLASS_EXAMPLE_HOLDER.put(CourseConstant.STREAM_API, 3);

    }

    //Register class here, Index sensitive
    private static final Class[] CLASSES = new Class[]{
            JavaExit.class,
            JavaThreadExecutorDemo.class,
            JavaLambdaExpressionDemo.class,
            JavaStreamApiDemo.class
    };




    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        while(true){
            String consoleValue = "";
            System.out.println("------Welcome to the course ---------");
            populateClassExampleHolder();
            printClassExampleHolder();
            System.out.println("\n\n-- Please Enter an Int Value corresponding to course-- \n\n");
            consoleValue = CourseUtil.readValueFromConsole();
            if(!StringUtils.isEmpty(consoleValue)){
                try{
                    int value = Integer.parseInt(consoleValue);
                    commenceLearning(value);
                }catch (NumberFormatException e){
                    throw new NumberFormatException("The value entered on console < " + consoleValue+ " >  is invalid.");
                }
            }
        }
    }

    private static void commenceLearning(int value) throws IllegalAccessException, InstantiationException {
        Class classInstance = CLASSES[value];
        Object callerObject = classInstance.newInstance();
        CourseEnabled courseEnabled = (CourseEnabled)callerObject;
        courseEnabled.setInstanceInfo(callerObject);
        courseEnabled.initProcess();

    }

    private static void printClassExampleHolder() {
        CLASS_EXAMPLE_HOLDER.forEach((key, value) -> System.out.println(value +" : "+"Explains-- "+key+ " -- in Java"));
    }










}
