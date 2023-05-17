package org.example.NetWorkThreading.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class NetworkClientCallable implements Callable<RequestResponse> {

    private RequestResponse lookup;
// Se crea un constructor que recibe un objeto RequestResponse
    public NetworkClientCallable(RequestResponse lookup) {
        this.lookup = lookup;
    }
// Se implementa el método call() de Callable
    @Override
    public RequestResponse call() throws IOException {
        // Se establece una conexión Socket con el host y puerto especificados
        try (Socket sock = new Socket(lookup.host, lookup.port);
             // Se lee la respuesta del servidor utilizando un Scanner
             Scanner scanner = new Scanner(sock.getInputStream())) {
            // Se lee la respuesta del servidor utilizando un Scanner
            lookup.response = scanner.next();
            return lookup;
        }
    }
}
