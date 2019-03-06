package com.demo.courses;

import com.demo.annotations.CallByFrameWork;

import java.util.concurrent.*;

/**
 * Created by I317726 on 3/3/2019.
 */
public class JavaThreadExecutorDemo extends AbstractCourse {

    @CallByFrameWork(callingSequence = 2)
    private void processExecutionByCallable() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try{
            Future<Integer> sizeFuture =  processExecutionByCallableFuture(executor);
            System.out.println("Size of the List is --> "+ sizeFuture.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            executor.shutdown();

        }

    }


    private Future<Integer> processExecutionByCallableFuture(ExecutorService executor) {
        return executor.submit(new ExecutionByCallable());
    }

    @CallByFrameWork(callingSequence = 1)
    private void processExecutionByRunnable() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try{
            executor.execute(new ExecutionByRunnable());
        }finally {
            executor.shutdown();
        }

    }


    private void addValuesToList(String threadExecutionStyle){
        try {
            System.out.println("Processing using  - "+ threadExecutionStyle);
            for(int i=11;i<15;i++){
                defaultIntegerValues.add(i);
                System.out.println("Added "+i+" Into the list using -"+threadExecutionStyle);
                Thread.sleep(3000);
            }
            System.out.println("Processing completed using - "+ threadExecutionStyle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private class ExecutionByRunnable implements Runnable{
        @Override
        public void run() {
            addValuesToList("Runnable");

        }
    }


    private class ExecutionByCallable implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            addValuesToList("Callable");
            return defaultIntegerValues.size();
        }
    }

}
