package sokolchik.pavel.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Сокольчик on 06.09.2014.
 */
public class Client {
    private static boolean login(BufferedReader in, PrintWriter out, BufferedReader localIn) throws IOException {
        String result;
        System.out.println("Please, enter your login");
        out.println(localIn.readLine());
        System.out.println("Please, enter your password");
        out.println(localIn.readLine());
        System.out.println("Please, enter your nickname");
        out.println(localIn.readLine());

        result = in.readLine();
        System.out.println(result);
        if (result.equals("Incorrect"))
            return false;
        else
            return true;
    }

    public static void main(String[] args) throws IOException {

        String host = new String("localhost");

        Socket server = null;

        if (host == null) {
            System.out.println("Please use: client hostname");
            System.exit(-1);
        }

        System.out.println("Trying to connect to server " + host + " on port 4444");
        server = new Socket(host, 4444);
        System.out.println("Server found");

        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream(), true);
        BufferedReader localIn = new BufferedReader(new InputStreamReader(System.in));


        while (!login(in, out, localIn)) {
        }


        String fuser, fserver;

        System.out.println("Please type:");
        while ((fuser = localIn.readLine()) != null) {
            if (fuser.equalsIgnoreCase(".exit")) break;
            try {
                out.println(fuser);
                fserver = in.readLine();
                System.out.println(fserver);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Quitting");
        out.close();
        in.close();
        localIn.close();
        server.close();

    }


}
