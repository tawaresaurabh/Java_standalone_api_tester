package com.demo.courses;

import java.util.concurrent.*;

/**
 * Created by I317726 on 3/3/2019.
 */
public class JavaThreadExecutorDemo extends AbstractCourse {

    @Override
    public void process() {
        ExecutorService executor= null;
       // ExecutorService ex = Executors.newSingleThreadScheduledExecutor();
        try{
            executor = Executors.newSingleThreadExecutor();
            processExecutionByRunnable(executor);
            Future<Integer> sizeFuture =  processExecutionByCallable(executor);
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
        System.out.println("This is where the code ends");
    }



    private Future<Integer> processExecutionByCallable(ExecutorService executor) {
        return executor.submit(new ExecutionByCallable());
    }

    private void processExecutionByRunnable(ExecutorService executor) {
        executor.execute(new ExecutionByRunnable());
    }


    private void addValuesToList(String threadExecutionStyle){
        try {
            System.out.println("Processing using  - "+ threadExecutionStyle);
            for(int i=11;i<15;i++){
                defaultIntegerValues.add(i);
                System.out.println("Added "+i+" Into the list");
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
