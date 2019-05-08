package ClientSystem.Grafica;

import ClientSystem.Client;
import ClientSystem.ClientObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Panel extends JPanel implements ClientObserver {
    private Client client;
    private JTextArea impiccato = new JTextArea(20,20);
    private JTextPane lettera = new JTextPane();

    public Panel(Client client){
        this.client = client;
        client.addObserver(this);
        initPanel();

    }
    public void initPanel(){
        impiccato.setBackground(Color.BLACK);
        impiccato.setForeground(Color.WHITE);

        JButton buttonInizia = new JButton("Inizia");
        JButton buttonConferma = new JButton("Conferma");

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals("Inizia")) {
                    try {
                        client.inizia();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        client.invia(lettera.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    lettera.setText("");
                }

            }
        };
        buttonInizia.addActionListener(buttonListener);
        buttonConferma.addActionListener(buttonListener);

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(lettera,BorderLayout.NORTH); p1.add(buttonConferma, BorderLayout.SOUTH);

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(buttonInizia, BorderLayout.NORTH); p2.add(p1,BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(p2,BorderLayout.EAST); add(impiccato, BorderLayout.WEST);

    }

    public void update(String string){
        impiccato.setText(string);
    }
}
