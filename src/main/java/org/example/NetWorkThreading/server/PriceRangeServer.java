package org.example.NetWorkThreading.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PriceRangeServer implements Runnable {

    private String price;
    private ServerSocket ss;

 // Se crea un constructor que recibe un puerto y un rango de precios
    public PriceRangeServer(int port, int low, int high) throws IOException {
        ss = new ServerSocket(port);
        ss.setSoTimeout(250);
        double d = Math.random() * (high - low) + low;
        price = String.format("%.2f", d);
    }

    // Método para aceptar conexiones de socket y enviar el precio a los clientes.
    public void accept() throws IOException {
        System.out.println("Accepting connections on port " + ss.getLocalPort());
        while (!Thread.interrupted()) {
            // Se crea un socket y un BufferedWriter para enviar el precio al cliente
            try (Socket sock = ss.accept();
                 // Se crea un BufferedWriter para enviar el precio al cliente
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    return;
                }// Si el hilo es interrumpido, sale del método.
                bw.write(price);
            } catch (SocketTimeoutException ste) {

            }
        }
        System.out.println("Done accepting");
    }

    @Override
    // Se implementa el método run() de Runnable
    public void run() {
        try {
            accept();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}