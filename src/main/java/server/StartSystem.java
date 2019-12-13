package server;

import server.connect.ConnectClient;
import server.connect.Connection;
import server.connect.constant.MessageTypes;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * @author h
 */
public class StartSystem {
    static Integer port = 8800;
    static Connection connection = null;
    static Set<ConnectClient> clientSet = new HashSet<ConnectClient>();

    public static void main(String[] args) throws IOException {
        connection = new Connection(port);
        MessageTypes.init();

        while (true) {
            Socket tempSocket = connection.getServerSocket().accept();
            System.out.println(tempSocket.getInetAddress());

            ConnectClient connectClient = (ConnectClient) tempSocket;
            connectClient.setKeepAlive(true);
            clientSet.add(connectClient);
            connection.addClientConnection(connectClient);
        }
    }
}
