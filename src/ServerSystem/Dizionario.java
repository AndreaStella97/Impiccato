package ServerSystem;


import java.util.ArrayList;
import java.util.Random;

public class Dizionario {
    private ArrayList<String> parole = new ArrayList<>();

    public Dizionario() {
        this.aggiungiParole();
    }

    private void aggiungiParole() {
        this.parole.add("Sternocleidomastoideo");
        this.parole.add("Jojoba");
        this.parole.add("Entomofago");
        this.parole.add("Abs");
        this.parole.add("Catecolamina");
        this.parole.add("Subecumenico");
        this.parole.add("Usufruttuario");
        this.parole.add("Catafratto");
        this.parole.add("Sibaritico");
        this.parole.add("Fruttivendolo");
        this.parole.add("Oligominerale");
        this.parole.add("Astigmatismo");
        this.parole.add("Parallelepipedo");
        this.parole.add("Oligominerale");
        this.parole.add("Balaustra");
        this.parole.add("Lalofobia");
        this.parole.add("Docimastica");
        this.parole.add("Consentaneo");
    }

    public String prendiParola() {
        int dimensioni = this.parole.size();
        Random casuale = new Random();
        int indiceCasuale = casuale.nextInt(dimensioni);
        String parola = (String)this.parole.get(indiceCasuale);
        return parola;
    }
}

