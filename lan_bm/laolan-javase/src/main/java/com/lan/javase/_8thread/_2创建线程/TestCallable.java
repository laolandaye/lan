package com.lan.javase._8thread._2创建线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

    public static void main(String[] args){
        MyCallable myCallable = new MyCallable();
        myCallable.setName("老王");
        FutureTask<String> futureTask = new FutureTask<String>(myCallable);
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class MyCallable implements Callable {

    @Override
    public Object call() throws Exception {
        return "hello world:" + this.getName();
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}