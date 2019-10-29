package sample.Connectivity;


import sample.Controls.User;

import java.io.IOException;

public class ServerConnection implements TCPConnectionListener {

    private TCPConnection connection;

    public ServerConnection(String ip, int port) {
        try {
            connection = new TCPConnection(this, ip, port);
        } catch (IOException e) {
            System.out.println("Connection exception: " + e);
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMessage("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        User b;
        if (value.equals("1"))
            //b = (User) tcpConnection.receiveUser();
            System.out.println(tcpConnection.receiveUser());
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMessage("Connection closed...");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMessage("Connection exception: " + e);
    }

    public void sendString(String msg) {
        if (!msg.equals(""))
            connection.sendString(msg);
    }

    public void sendUser(User user) {
        sendString("1");
        connection.sendUser(user);
    }

    private synchronized void printMessage(String msg) {
        System.out.println(msg);
    }

}
