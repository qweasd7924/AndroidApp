package com.example.sqlitedb1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Павел on 24.07.2016.
 */
public class Connector implements Runnable{
    private String message ;

    public void communicate(String message) {
        this.message = message;
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {

        int serverPort = 6666;
        String address = "192.168.0.174";
        Log.d("mLog", "Try to start port 6666");

        try (Socket socket = new Socket(address, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

//            String line = keyboard.readLine();
            Log.d("mLog", "Port started");

            String line = message;
            out.writeUTF(line);
            out.flush();
            line = in.readUTF();
            Log.d("mLog", "Response: " + line);
//            System.out.println("Response: " + line);
        } catch (Exception e) {
            Log.d("mLog", e.getMessage());
            e.printStackTrace();
        }
    }
}
