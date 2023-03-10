package org.example.Sincronized;

public class PracticeThread extends Thread {
    
    @Override
    public void run() {
        PurchasingAgent agent = new PurchasingAgent();
        agent.purchase();
    }
    
}