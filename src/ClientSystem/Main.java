package ClientSystem;

import ClientSystem.Grafica.Frame;
import ServerSystem.Gioco;
import ServerSystem.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Gioco gioco = new Gioco();
        Server server = new Server(gioco);

        Client client = new Client();
       
        Frame ig = new Frame(client);
      
    }
}
