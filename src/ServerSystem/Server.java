package ServerSystem;

import java.io.*;
import java.net.*;
import java.util.ArrayList;



public class Server extends Thread{
    private Gioco gioco;
    private ServerSocket server;
    private Socket client=null;

    public Server(Gioco gioco) throws IOException {
        this.gioco = gioco;
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

    class Connect {
        private Socket client = null;
        BufferedReader in = null;
        PrintStream out = null;
        String clientMessage;


        public Connect(Socket clientSocket) throws IOException {
            client = clientSocket;
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);
            leggiScrivi();
        }

        public void leggiScrivi() {
            do {
                try {
                    clientMessage = in.readLine();
                    if (clientMessage.equals("Inizia")) {
                        gioco.inizia();
                        out.println(gioco.getImpiccatoStringa());
                    } else if (clientMessage.equals("")) {
                        out.println(gioco.getImpiccatoStringa());
                    } else {
                        gioco.creaTentativo(clientMessage.charAt(0));
                        out.println(gioco.getImpiccatoStringa());
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



