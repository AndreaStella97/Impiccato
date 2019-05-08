package ServerSystem;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread{
    private Gioco gioco;
    private ServerSocket server;

    public Server(Gioco gioco) throws IOException {
        this.gioco = gioco;
        server = new  ServerSocket(8888);
        System.out.println("Il Server è in attesa sulla porta 8888");
        this.start();
    }

    public void run() {
        while (true) {
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


        public Connect(Socket clientSocket){
            client = clientSocket;
                try {
                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    out = new PrintStream(client.getOutputStream(), true);
                    clientMessage = in.readLine();

                } catch (Exception e1) {
                    try {
                        client.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                }
                this.start();
        }

        public void run(){
                try {
                    if(clientMessage.equals("Inizia") ){
                            gioco.inizia();
                            out.println(gioco.getImpiccatoStringa());
                        } else if(clientMessage.equals("")){
                        out.println(gioco.getImpiccatoStringa());

                     }
                        else {
                            gioco.creaTentativo(clientMessage.charAt(0));
                            out.println(gioco.getImpiccatoStringa());
                        }

                    in.close();
                    out.close();
                    client.close();

                } catch (Exception e) {}
        }
    }

}
