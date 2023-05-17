package org.example.ExecutorService.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PriceRangeServer implements Runnable {

    private String price;
    private ServerSocket ss;

    public PriceRangeServer(int port, int low, int high) throws IOException {
        ss = new ServerSocket(port);
        ss.setSoTimeout(250); // Establece el tiempo de espera del socket a 250 milisegundos.
        double d = Math.random() * (high - low) + low; // Genera un número aleatorio dentro del rango.
        price = String.format("%.2f", d);
    }

    // Método para aceptar conexiones de socket y enviar el precio a los clientes.
    // Lanza una excepción de IOException si hay algún error al aceptar la conexión.
    public void accept() throws IOException {
        System.out.println("Accepting connections on port " + ss.getLocalPort());
        while (!Thread.interrupted()) {
            try (Socket sock = ss.accept();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))) {
                try {
                    Thread.sleep(2000);   // Pausa de 2 segundos para simular un procesamiento antes de enviar el precio.
                } catch (InterruptedException ex) {// Si el hilo es interrumpido, sale del método.
                    return;
                }
                bw.write(price);
            } catch (SocketTimeoutException ste) {
                // Se produce una excepción de tiempo de espera cada 0.25 segundos para verificar si el hilo ha sido interrumpido
            }
        }
        System.out.println("Done accepting");
    }

    @Override
    public void run() {
        try {
            accept();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}