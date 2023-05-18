package org.example.Sincronized;
public class PurchasingAgent {
// Se crea un constructor que recibe un objeto RequestResponse
    public PurchasingAgent() {
        System.out.println("Creating a purchasing agent");
    }
// Se implementa el método call() de Callable
    public void purchase() {
        Thread t = Thread.currentThread();
        System.out.println("Thread:" + t.getName() + "," + t.getId());

        Store store = Store.getInstance();
        synchronized (store) {
            // Se crea un objeto Store y se sincroniza el acceso a él
            if (store.getShirtCount() > 0 && store.authorizeCreditCard("1234", 15.00)) {
               // Se comprueba si hay camisetas disponibles y si la tarjeta de crédito es válida.
                Shirt shirt = store.takeShirt();
                System.out.println("The shirt is ours!");
                System.out.println(shirt);
            } else {
                System.out.println("No shirt for you");
            }
        }
    }
}
