package com.jiangconglin.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private ServerSocket serverSocket;

    private void init() throws IOException {
        serverSocket = new ServerSocket(10010);
    }

    private void startServer() throws IOException {
        Socket socket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        while (true) {
            String input = reader.readLine();
            System.out.println("Server accept: " + input);
            writer.write("get your message: " + input);
            writer.write("\n");
            writer.flush();
//            socket.shutdownOutput();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }

    @Override
    public void run() {
        try {
            init();
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
