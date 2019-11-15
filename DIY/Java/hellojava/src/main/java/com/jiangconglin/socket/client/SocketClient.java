package com.jiangconglin.socket.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient implements Runnable {
    private Socket client;

    private void init() throws IOException {
        client = new Socket("127.0.0.1", 10010);
    }

    private void startClient() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (; ; ) {
                String newLine = scanner.nextLine();
                if (null != newLine) {
                    writer.write(newLine);
                    writer.write("\n");
                    writer.flush();
//                    client.shutdownOutput();
                    break;
                }
            }
            String res = reader.readLine();
            if (null != res) {
                System.out.println(res);
            }

        }
    }

    @Override
    public void run() {
        try {
            init();
            startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
