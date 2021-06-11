package sebek.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private OutputStream serverOut;
    private BufferedReader buffIn;

    private Client(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;

    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 9999);
        String body;
        Scanner scan = new Scanner(System.in);
        if (!client.connect()) {
            System.err.println("Nie udało się połączyć!");
        } else {
            if (client.login()) {
                while (true) {
                    body = scan.nextLine();
                    if("exit".equalsIgnoreCase(body))
                    {
                        client.logout();
                        break;
                    }else{
                        client.send(body);
                    }
                }
            }
        }
    }

    private void send(String body) throws IOException {
        String text = "send " + body + "\n";
        serverOut.write(text.getBytes());
    }

    private void logout() throws IOException {
        serverOut.write("exit\n".getBytes());
        System.exit(0);
    }

    private boolean login() throws IOException {
        Scanner scan = new Scanner(System.in);
        String login;
        String comm;

        do {
            System.out.println("podaj: <nick>");
            login = scan.nextLine();
            comm = "login " + login + "\n";
            serverOut.write(comm.getBytes());
            String resp = buffIn.readLine();
            System.out.println(resp + "\n");
            if ("Zalogowano...".equalsIgnoreCase(resp)) {
                startReadMess();
                return true;
            }
        } while (true);
    }

    private void startReadMess() {
        Thread tr = new Thread(this::readMess);
        tr.start();
    }

    private void readMess() {
        try {
            String line;
            while ((line = buffIn.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            this.serverOut = socket.getOutputStream();
            InputStream serverIn = socket.getInputStream();
            this.buffIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
