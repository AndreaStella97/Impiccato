package ServerSystem;

import java.io.*;
import java.net.*;


public class Server extends Thread{
    private ServerSocket server;
    
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
        Gioco gioco;
        private Socket client = null;
        BufferedReader in = null;
        PrintStream out = null;
        String clientMessage="";


        public Connect(Socket clientSocket) throws IOException {
            client = clientSocket;
            gioco = new Gioco();
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);
            this.start();
        }

        public void run() {
            while (!clientMessage.equals("chiudi")){
                try {
                    clientMessage = in.readLine();
                    if (clientMessage.equals("inizia")) {
                        gioco.inizia();
                        out.print(gioco.getImpiccatoStringa()+"\n");
                    } else if (clientMessage.length()==1 && Character.isLetter(clientMessage.charAt(0))) {
                        gioco.creaTentativo(clientMessage.charAt(0));
                        out.print(gioco.getImpiccatoStringa()+"\n");
                    } else {
                        out.print(gioco.getImpiccatoStringa()+"\nLETTERA NON VALIDA!"+"\n");
                    }
                } catch (Exception e) { }
            }

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



