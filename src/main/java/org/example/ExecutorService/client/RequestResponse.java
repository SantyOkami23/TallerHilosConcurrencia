package org.example.ExecutorService.client;

import java.util.Objects;

public class RequestResponse {

    public String host; //request
    public int port; //request
    public String response; //response

    public RequestResponse(String host, int port) {
        this.host = host;
        this.port = port;
    }
    // Método equals para comparar dos objetos RequestResponse
    // Compara si el host y el puerto son iguales en ambos objetos

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RequestResponse) {
            RequestResponse lookup = (RequestResponse) obj;
            if (host.equals(lookup.host) && port == lookup.port) {
                return true;
            }
        }
        return false;
    }

    // Método hashCode para generar un código hash único para cada objeto RequestResponse
    // Utiliza el host y el puerto para generar el código hash
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.host);
        hash = 97 * hash + this.port;
        return hash;
    }
}