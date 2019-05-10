package ClientSystem;

import ClientSystem.Grafica.Frame;
import ServerSystem.Gioco;
import ServerSystem.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       // Gioco gioco = new Gioco();

        Server server = new Server();
        Client client = new Client();
        Client client2 = new Client();

        Frame ig = new Frame(client);
        Frame ig2 = new Frame(client2);
      
    }
}
