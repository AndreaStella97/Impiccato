package ClientSystem;

import ClientSystem.Grafica.Frame;
import ServerSystem.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Server server = new Server();
        Client client = new Client();
        Frame ig = new Frame(client);
      
      
    }
}
