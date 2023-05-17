package org.example.EjercicioFindMaxTask;

import java.util.concurrent.ForkJoinPool;

public class Main {


    public static void main(String[] args) {
        int[] data = new int[1024 * 1024 * 128]; //512MB

        ForkJoinPool pool = new ForkJoinPool();
        // Se crea una instancia de RandomArrayAction para generar los valores aleatorios del arreglo
        
        RandomArrayAction action = new RandomArrayAction(data, 0, data.length-1, data.length/16);
        pool.invoke(action); // Se invoca la tarea para generar los valores aleatorios

        // Se crea una instancia de FindMaxTask para encontrar el valor máximo en el arreglo
        FindMaxTask task = new FindMaxTask(data, 0, data.length-1, data.length/16);
        Integer result = pool.invoke(task);
        System.out.println("Max value found:" + result);  // Se invoca la tarea para encontrar el máximo valor
        
    }
}
