package org.example.Sincronized;

public class PracticeThread extends Thread {
    // Se crea un constructor que recibe un objeto RequestResponse
    @Override
    public void run() {
        PurchasingAgent agent = new PurchasingAgent();
        agent.purchase();
    }
    
}