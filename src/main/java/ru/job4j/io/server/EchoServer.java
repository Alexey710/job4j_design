package ru.job4j.io.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("=")) {
                            String[]arr = str.split("=");
                            String[]subArr = arr[1].split(" ");
                            if (subArr[0].equals("Exit")) {
                                server.close();
                                return;
                            } else if (subArr[0].equals("Hello")) {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("\"Hello\"\r\n".getBytes());
                            } else {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("\"What\"\r\n".getBytes());
                            }
                        }
                    }
                }
            }
        }
    }
}