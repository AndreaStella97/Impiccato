package ServerSystem;


import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Dizionario {
    private ArrayList<String> parole = new ArrayList<>();

    public Dizionario() throws IOException {
        this.aggiungiParole();
    }

    private void aggiungiParole() throws IOException {
        BufferedReader buf = new BufferedReader(new FileReader(new File("src/ServerSystem/Parole.txt")));
        while(buf.ready()){
            parole.add(buf.readLine());
        }
    }

    public String prendiParola() {
        int dimensioni = this.parole.size();
        Random casuale = new Random();
        int indiceCasuale = casuale.nextInt(dimensioni);
        String parola = (String)this.parole.get(indiceCasuale);
        return parola;
    }
}

