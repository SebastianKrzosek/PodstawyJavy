package sebek.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private final int serverPort;
    private ArrayList<ServerUser> userList = new ArrayList<>();

    Server(int serverPort) {
        this.serverPort = serverPort;
    }

    ArrayList<ServerUser> getUserList(){
        return userList;
    }

    @Override
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("accepted connection from " + clientSocket);
                ServerUser user = new ServerUser(this, clientSocket);
                userList.add(user);
                user.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void removeUser(ServerUser serverUser) {
        userList.remove(serverUser);
    }
}
