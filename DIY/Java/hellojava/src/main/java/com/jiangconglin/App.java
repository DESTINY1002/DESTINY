package com.jiangconglin;

import com.jiangconglin.socket.client.SocketClient;
import com.jiangconglin.socket.server.SocketServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
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
//        System.out.println(123);

    }
}
