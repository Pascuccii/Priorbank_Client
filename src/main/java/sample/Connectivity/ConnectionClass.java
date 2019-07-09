package sample.Connectivity;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionClass {

    private static Connection connection;
    private boolean connected = false;

    public boolean isConnected() {
        return connected;
    }
    public ConnectionClass (String URL, String USER, String PASS) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            //            statement.execute("INSERT INTO users (name,password) VALUES ('first','first')");
//            statement.executeUpdate("UPDATE users SET users.name = 'admin' WHERE id = 4");

//            statement.addBatch("INSERT INTO users (name,password) VALUES ('batch1','pas')");
//            statement.addBatch("INSERT INTO users (name,password) VALUES ('batch2','pas')");
//            statement.addBatch("INSERT INTO users (name,password) VALUES ('batch3','pas')");
//            statement.executeBatch();
//            statement.clearBatch();
//            boolean status = statement.isClosed();
//            System.out.println(status);
            if(!connection.isClosed())
                connected = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
