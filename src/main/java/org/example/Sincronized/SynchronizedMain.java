package org.example.Sincronized;


public class SynchronizedMain {
// Se crea un constructor que recibe un objeto RequestResponse
    public static void main(String[] args) {
        Store store = Store.getInstance();
        store.addShirt(new Shirt("1", "Polo", "Rainbow", "Large"));
        // Se crea un objeto Store y se sincroniza el acceso a él
        PracticeThread p1 = new PracticeThread();
        PracticeThread p2 = new PracticeThread();
        // Se crean dos hilos de ejecución
        p1.start();
        p2.start();
    }
}