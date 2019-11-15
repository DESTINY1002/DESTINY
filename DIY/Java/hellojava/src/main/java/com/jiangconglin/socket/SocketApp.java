package com.jiangconglin.socket;

import com.jiangconglin.socket.client.SocketClient;
import com.jiangconglin.socket.server.SocketServer;

public class SocketApp {
    public static void main(String[] args) {

        Thread server = new Thread(new SocketServer());
        server.start();
        Thread client = new Thread(new SocketClient());
        client.start();
        try {
            server.join();
            client.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
