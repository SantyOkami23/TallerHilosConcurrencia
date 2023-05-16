package org.example.EjercicioFindMaxTask;

import java.util.concurrent.ForkJoinPool;

public class Main {


    public static void main(String[] args) {
        int[] data = new int[1024 * 1024 * 128]; //512MB

        ForkJoinPool pool = new ForkJoinPool();
        
        RandomArrayAction action = new RandomArrayAction(data, 0, data.length-1, data.length/16);
        pool.invoke(action);
        
        FindMaxTask task = new FindMaxTask(data, 0, data.length-1, data.length/16);
        Integer result = pool.invoke(task);
        System.out.println("Max value found:" + result);
        
    }
}
