package org.example.NetWorkThreading.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkServerMain {
// Se crea un ExecutorService con un conjunto de hilos de ejecución en caché.
    public static void main(String[] args) {
        ExecutorService exSrv = Executors.newCachedThreadPool();
        // Crea una lista para almacenar los objetos Runnable (PriceRangeServer) que se crearán.
        List<Runnable> runners = new ArrayList<>();
        // Crea varios objetos PriceRangeServer en diferentes puertos y los agrega a la lista de runners.
        for (int port = 10000; port < 10010; port++) {
            Runnable r;
            try {
                // Se crea un objeto PriceRangeServer con el puerto actual.
                r = new PriceRangeServer(port, 20, 110);
                runners.add(r);
            } catch (IOException ex) {
                System.out.println("Port " + port + " already in use");
            }
        }
        // Ejecuta cada objeto Runnable en el ExecutorService.
        for (Runnable r : runners) {
            exSrv.execute(r);
        }
// Pausa de 500 milisegundos antes de continuar.
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        System.out.println("Press enter to quit...");
        try {
            System.in.read();
        } catch (IOException ex) {
        }
        System.out.println("Quiting...");
        exSrv.shutdownNow();

    }
}