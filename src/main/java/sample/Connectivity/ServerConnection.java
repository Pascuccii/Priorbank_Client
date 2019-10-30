package sample.Connectivity;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Controls.Client;
import sample.Controls.User;

public class ServerConnection implements TCPConnectionListener {


    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<Client> clientList = FXCollections.observableArrayList();
    private String mode = "User";
    private int cnt = 0;
    private boolean inProcess = false;
    private TCPConnection connection;

    public ServerConnection(String ip, int port) {
        try {
            connection = new TCPConnection(this, ip, port);
        } catch (Exception e) {
            System.out.println("Connection exception: " + e);
        }
    }

    public boolean reconnect(String ip, int port) {
        try {
            connection = new TCPConnection(this, ip, port);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMessage("Connection ready...");
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        if (value.equals("END"))
            inProcess = false;
        String[] vals = value.split(" ");
        if (cnt == 0) {
            if (value.matches("[0-9]+ USERS:")) {
                userList.clear();
                cnt = Integer.parseInt(vals[0]);
                mode = "User";
            } else if (value.matches("[0-9]+ CLIENTS:")) {
                clientList.clear();
                cnt = Integer.parseInt(vals[0]);
                mode = "Client";
            }
        } else {
            cnt--;
            if (mode.equals("User"))
                userList.add(new User(value));
            if (mode.equals("Client"))
                clientList.add(new Client(value));
        }
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMessage("Connection closed...");
        connection = null;
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMessage("Connection exception: " + e);
    }

    public void sendString(String msg) {
        if (exists()) {
            if (msg.equals("init"))
                inProcess = true;
            if (!msg.equals(""))
                connection.sendString(msg);
        }
    }

    private synchronized void printMessage(String msg) {
        System.out.println(msg);
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

    public ObservableList<Client> getClientList() {
        return clientList;
    }

    public void printUsersBuffer() {
        System.out.println("Buffered users:");
        for (User u : userList)
            System.out.println(u);
    }

    public void printClientsBuffer() {
        System.out.println("Buffered clients:");
        for (Client c : clientList)
            System.out.println(c);
    }

    public boolean isInProcess() {
        return inProcess;
    }


    public synchronized boolean exists() {
        return connection != null;
    }

    public synchronized boolean isClosed() {
        return connection.isClosed();
    }
}
