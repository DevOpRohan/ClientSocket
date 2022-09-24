package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Socket;

public class Client
{
    private Socket socket;
    public StringBuilder output;
    public Client(String IP, int port, String Code)
    {
        output = new StringBuilder();
        try
        {
            socket = new Socket("localhost", 8888);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        sendMessage(Code);
        receiveMessage();
        close();
    }

    private void sendMessage(String message)
    {
        try
        {
            socket.getOutputStream().write(message.getBytes("UTF-8"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void receiveMessage()
    {
        try
        {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.getInputStream().read(buffer);
            output.append(new String(buffer, "UTF-8"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void close()
    {
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
