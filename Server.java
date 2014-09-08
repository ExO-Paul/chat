package sokolchik.pavel.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Сокольчик on 06.09.2014.
 */
public class Server {

    public static ArrayList<User> users;

    public static void help ()
    {
        System.out.println("This is a console chat based on Java, it has following commands:\n.exit - quits existing instance of program;" +
                "\n.help - shows this help message;");

    }

    public static boolean login(User incoming, PrintWriter out) {
        boolean authSuccess = false;
        for (User user : users) {
            if (user.login.equals(incoming.login) && user.password.equals(incoming.password)) {
                authSuccess = true;
                user.nickname = incoming.nickname;
            } else
                out.println("Incorrect");
        }
        return authSuccess;
    }

    public static void main(String[] args) throws IOException {


        users.add(new User("paulie91", "111", "ExO"));

        PrintWriter out = null;
        BufferedReader in = null;

        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(4444);
        } catch (Exception ex) {
            System.out.println("Sorry, cannot create service on port 4444");
            System.exit(-1);
        }

        try {
            System.out.println("Awaiting for a client to connect");
            client = server.accept();
            System.out.println("Client connected");
        } catch (Exception ex) {
            System.out.println("Cannot find clients");
            System.exit(-1);
        }

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

        String input;

        User incoming = new User();
        do {
            incoming.login = in.readLine();
            incoming.password = in.readLine();
            incoming.nickname = in.readLine();
            System.out.println(incoming.login + " " + incoming.password + " " + incoming.nickname);

        }
        while (!login(incoming, out));

        out.println("Login successful");

        System.out.println("Awaiting messages");

        while ((input = in.readLine()) != null) {
            if (input.equals(".help")) help();
            if (input.equals(".exit")) break;
            out.println(users.get(0).nickname + ": " + input);
            System.out.println(input);
        }
        System.out.println("Quitting");
        out.close();
        in.close();
        client.close();
        server.close();
    }
}



