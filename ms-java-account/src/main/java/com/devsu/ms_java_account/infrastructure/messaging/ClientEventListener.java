package com.devsu.ms_java_account.infrastructure.messaging;

import org.springframework.stereotype.Component;

@Component
public class ClientEventListener {
    public void onClientEventReceived(String eventJson) {
        // Future implementation for asynchronous communication with client-service
        System.out.println("Received client event: " + eventJson);
    }
}