package com.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by I317726 on 3/3/2019.
 */
public class CourseUtil {

    public static String readValueFromConsole(){
        String consoleValue = null;
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            consoleValue = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return consoleValue;
    }




}
