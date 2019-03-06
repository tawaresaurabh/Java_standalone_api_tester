package com.demo.courses;

import com.demo.annotations.CallByFrameWork;

/**
 * Created by I317726 on 3/3/2019.
 */
public class JavaLambdaExpressionDemo extends AbstractCourse {

    @CallByFrameWork(callingSequence = 1)
    private void printSimpleListByLambdaExpression() {

        defaultIntegerValues.forEach(integer -> System.out.println("Printing no."+integer));

    }

    @CallByFrameWork(callingSequence = 2)

    private void printEvenNumberListByLambdaExpression() {

        defaultIntegerValues.forEach(integer -> {
            if(integer%2==0){
                System.out.println("Even no."+ integer);
            }
        });
    }


}
