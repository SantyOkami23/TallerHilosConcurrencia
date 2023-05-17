package org.example.NetWorkThreading.client;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class NetworkClientMain {
// Se crea un método main() que realiza solicitudes en diferentes puertos
    public static void main(String[] args) {
        String host = "localhost";
        // Se ejecuta un bucle para realizar solicitudes en diferentes puertos
        for (int port = 10000; port < 10010; port++) {
            // Se crea un objeto RequestResponse con el host y puerto actuales
            RequestResponse lookup = new RequestResponse(host, port);
            try (Socket sock = new Socket(lookup.host, lookup.port);
                 // Se lee la respuesta del servidor utilizando un Scanner
                    Scanner scanner = new Scanner(sock.getInputStream());) {
                lookup.response = scanner.next();
                // Se muestra en la consola el host, puerto y respuesta recibida
                System.out.println(lookup.host + ":" + lookup.port + " " + lookup.response);
            } catch (NoSuchElementException | IOException ex) {
                System.out.println("Error talking to " + host + ":" + port);
            }
        }
    }
}