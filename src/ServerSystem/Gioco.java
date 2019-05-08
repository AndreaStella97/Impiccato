package ServerSystem;

import ClientSystem.Client;

import java.util.ArrayList;

public class Gioco {
    private String parolaSegreta;
    private char vettoreParolaSegreta[];
    private ArrayList<Character> tentativiErrati = new ArrayList<>();
    private boolean completo = false;
    private ArrayList<Character> tentativi = new ArrayList<>();

    public Gioco() {
        inizia();
    }

    public void inizia() {
        Dizionario diz = new Dizionario();
        tentativi.clear();
        tentativiErrati.clear();
        parolaSegreta = diz.prendiParola();
        vettoreParolaSegreta = parolaSegreta.toLowerCase().toCharArray();
    }

    public void creaTentativo(char lett) {
        for (char lettereInTentativi : tentativi) {
            if (lett == lettereInTentativi) return ;
        }
        tentativi.add(lett);
        verificaErrore(lett);
        verificaFine();

    }


    private void verificaErrore(char lett) {
        for (char lettera : vettoreParolaSegreta) {
            if (lett == lettera) return;
        }

        tentativiErrati.add(lett);
    }

    private void verificaFine() {
        if (tentativiErrati.size() >= 6) {
            inizia();
        }
        if (vinto(restituisciParola())) {
            inizia();
        }
    }

    private boolean vinto(String parola){

        for(int i = 0; i<parola.length(); i++)
            if (parola.charAt(i) == '_' ) {
                return false;
            }
        return true;

    }


    public String getImpiccatoStringa() {
        return restituisciImpiccato() + "\n" + restituisciParola() +"\n\n" + restituisciTentErrati();
    }

    private String restituisciParola() {
        String parola = "";
        boolean presente;

        for (int i = 0; i < vettoreParolaSegreta.length; i++) {
            presente = false;
            if ((i == 0) || (i == (vettoreParolaSegreta.length - 1))) parola += vettoreParolaSegreta[i] + " ";
            else {
                for (char lettera : tentativi) {
                    if (lettera == vettoreParolaSegreta[i]) {
                        parola += lettera + " ";
                        presente = true;
                    }
                }

                if (!presente){
                    parola += "_";
                }
                parola += " ";
                
            }
        }

        return parola;
    }

    private String restituisciImpiccato() {
        switch (tentativiErrati.size()) {
            case 0:
                return "+-----+\n" +
                        "  |   |\n" +
                        "     |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 1:
                return "+-----+\n" +
                        "  |   |\n" +
                        " O   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 2:
                return "+-----+\n" +
                        "  |   |\n" +
                        " O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 3:
                return "+-----+\n" +
                        "  |   |\n" +
                        " O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 4:
                return "+-----+\n" +
                        "  |   |\n" +
                        " O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 5:
                return "+-----+\n" +
                        "  |   |\n" +
                        " O   |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========";
            case 6:
                return "+-----+\n" +
                        "  |   |\n" +
                        " O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "=========";
            default:
                return "Errore.";
        }
    }

    public String restituisciTentErrati(){
        String parola="";
        for(char c : tentativiErrati){
             parola+=c+" ";
        }
        return parola;
    }


}
