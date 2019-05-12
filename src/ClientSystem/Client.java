package ClientSystem;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    ArrayList<ClientObserver> observers = new ArrayList<>();
    BufferedReader in = null;
    PrintStream out = null;
    Socket socket = null;


    public void addObserver(ClientObserver ob){
        observers.add(ob);
    }

    public void updateObserver(String stringa){
        for(ClientObserver ob : observers){
            ob.update(stringa);
        }
    }

    public void connetti() throws IOException {
        socket = new Socket("localhost", 8888);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream(), true);
    }

    public void disconnetti() throws IOException {
        out.println("chiudi");
        in.close();
        out.close();
        socket.close();
    }

    public String leggiMessaggio() throws IOException {
        String message="";
        while (in.ready()) {
            message += in.readLine() + "\n";
        }
        return message;
    }

    public void invia(String carattere) throws IOException{
        out.println(carattere);
        while(!in.ready()){

       }
       updateObserver(leggiMessaggio());
    }

    public void inizia() throws IOException {
        out.println("Inizia");
        while(!in.ready()){

        }
        updateObserver(leggiMessaggio());
    }
}
