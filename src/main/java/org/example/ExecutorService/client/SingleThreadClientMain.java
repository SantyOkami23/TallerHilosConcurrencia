package org.example.ExecutorService.client;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SingleThreadClientMain {

    public static void main(String[] args) {
        String host = "localhost";
        // Se ejecuta un bucle para realizar solicitudes en diferentes puertos
        for (int port = 10000; port < 10010; port++) {
            // Se crea un objeto RequestResponse con el host y puerto actuales
            RequestResponse lookup = new RequestResponse(host, port);
            // Se establece una conexión Socket con el host y puerto especificados
            try (Socket sock = new Socket(lookup.host, lookup.port);
                 Scanner scanner = new Scanner(sock.getInputStream());) {
                // Se lee la respuesta del servidor utilizando un Scanner
                lookup.response = scanner.next();
                System.out.println(lookup.host + ":" + lookup.port + " " + lookup.response);
            } catch (NoSuchElementException | IOException ex) {
                System.out.println("Error talking to " + host + ":" + port);
            }
        }
    }
}