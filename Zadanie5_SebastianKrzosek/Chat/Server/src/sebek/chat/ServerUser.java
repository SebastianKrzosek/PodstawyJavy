package sebek.chat;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerUser extends Thread {

    private final Socket clientSocket;
    private final Server server;
    private String login = null;
    private OutputStream outputStream;

    ServerUser(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    private String getLogin() {
        return login;
    }

    @Override
    public void run() {
        try {
            ClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ClientSocket() throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];
                if ("exit".equalsIgnoreCase(cmd)) {
                    Logout();
                    break;
                } else if ("login".equalsIgnoreCase(cmd)) {
                    if (checkLogin(tokens[1])) {
                        Login(outputStream, tokens);
                    }
                } else if("send".equalsIgnoreCase(cmd)) {
                    sendMsg(tokens);
                }
                else {
                    String mess = "unknown: " + line + "\n";
                    outputStream.write(mess.getBytes());
                }
            }
        }

        clientSocket.close();
    }

    private void sendMsg(String[] tokens) throws IOException {
        String text= "";
        for (int i = 1; i < tokens.length ; i++) text = text + " " + tokens[i];
        List<ServerUser> userList = server.getUserList();
        for (ServerUser user : userList) {
            if (!login.equals(user.getLogin()))
                user.send(login+"\t"+text+"\n");
        }
    }

    private void Logout() throws IOException {
        server.removeUser(this);
        List<ServerUser> userList = server.getUserList();
        String onMess = login + "\tis offline!\n";
        for (ServerUser user : userList) {
            if (!login.equals(user.getLogin())) {
                user.send(onMess);
            }
        }
        clientSocket.close();
    }

    private void Login(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 2) {
            String login = tokens[1];

            if (checkLogin(login)) {
                String mess = "Zalogowano...\n";
                outputStream.write(mess.getBytes());
                this.login = login;
                System.out.println(login + " dolaczyl do chatu");

                List<ServerUser> userList = server.getUserList();
                for (ServerUser user : userList) {//kto jest online
                    if (user.getLogin() != null) {
                        if (!login.equals(user.getLogin())) {
                            String mess2 = user.getLogin() + "\tis online!\n";
                            send(mess2);
                        }
                    }
                }

                String onMess = login + "\tis online!\n";
                for (ServerUser user : userList) {
                    if (!login.equals(user.getLogin())) {
                        user.send(onMess);
                    }
                }
            }
        }
    }

    private boolean checkLogin(String login) throws IOException {
        List<ServerUser> userList = server.getUserList();
        for (ServerUser user : userList) {
            if (login.equals(user.getLogin())) {
                outputStream.write("Login jest zajety!\n".getBytes());
                return false;
            }
        }
        return true;
    }

    private void send(String onMess) throws IOException {
        if (login != null) {
            outputStream.write(onMess.getBytes());
        }
    }
}
