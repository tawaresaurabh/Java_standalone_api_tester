package com.demo.courses;

import com.demo.annotations.CallByFrameWork;

/**
 * Created by I317726 on 3/3/2019.
 */
public class JavaExit extends AbstractCourse{

    @CallByFrameWork(callingSequence = 1)
    private void exitJava(){
        System.exit(0);
    }
}
