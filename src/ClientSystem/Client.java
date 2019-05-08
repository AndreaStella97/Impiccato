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

    public String leggiMessaggio() throws IOException {
        String message="";
        String line ="";
        while((line = in.readLine())!= null){
            message += line +"\n";
        }
        return message;
    }

    public void invia(String carattere) throws IOException {
        connetti();
        out.println(carattere);
        updateObserver(leggiMessaggio());
    }

    public void inizia() throws IOException {
        connetti();
        out.println("Inizia");
        updateObserver(leggiMessaggio());
    }
}
