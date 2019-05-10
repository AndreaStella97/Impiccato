package ServerSystem;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Server extends Thread{
    private ServerSocket server;
    private Socket client=null;
    private HashMap<Socket, Gioco> giochi = new HashMap<>();
    public Server() throws IOException {
        server = new  ServerSocket(8888);
        System.out.println("Il Server è in attesa sulla porta 8888");
        this.start();
    }

    public void run() {
        while(true){
            try {
                System.out.println("In attesa di Connessione");
                Socket client = server.accept();
                System.out.println("Connessione accettata da: " + client.getInetAddress());
                Connect c = new Connect(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
          }
        }

    class Connect extends Thread{
        private Socket client = null;
        BufferedReader in = null;
        PrintStream out = null;
        String clientMessage;


        public Connect(Socket clientSocket) throws IOException {
            client = clientSocket;
            giochi.put(client, new Gioco());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);
            this.start();
        }

        public void run() {
            do {
                try {
                    clientMessage = in.readLine();
                    if (clientMessage.equals("Inizia")) {
                        giochi.get(client).inizia();
                        out.println(giochi.get(client).getImpiccatoStringa());
                    } else if (clientMessage.equals("")) {
                        out.println(giochi.get(client).getImpiccatoStringa());
                    } else {
                        giochi.get(client).creaTentativo(clientMessage.charAt(0));
                        out.println(giochi.get(client).getImpiccatoStringa());
                    }
                } catch (Exception e) { }
            } while (!clientMessage.equals("chiudi"));

            try {
                in.close();
                out.close();
                client.close();
                System.out.println("Disconnesso dal client: " + client.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
          }
    }
}



