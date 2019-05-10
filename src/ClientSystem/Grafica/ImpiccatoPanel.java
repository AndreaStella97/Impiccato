package ClientSystem.Grafica;

import ClientSystem.Client;
import ClientSystem.ClientObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ImpiccatoPanel extends JPanel implements ClientObserver{
    ArrayList<FrameObserver> observers = new ArrayList<>();
    private Client client;
    private JTextArea impiccato = new JTextArea(20,19);
    private JTextPane lettera = new JTextPane();

    public ImpiccatoPanel(Client client){
        this.client = client;
        client.addObserver(this);
        initPanel();
    }

    public void initPanel(){
        impiccato.setBackground(Color.BLACK);
        impiccato.setForeground(Color.WHITE);

        JButton buttonInizia = new JButton("Inizia");
        JButton buttonConferma = new JButton("Conferma");
        JButton buttonFine = new JButton("Disconnetti");

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                try {
                    if (button.getText().equals("Inizia")) {
                        client.inizia();

                    } else if (button.getText().equals("Conferma")) {
                        client.invia(lettera.getText());
                        lettera.setText("");
                    } else  {
                        client.disconnetti();
                        updateObservers(1);


                    }
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
        buttonInizia.addActionListener(buttonListener);
        buttonConferma.addActionListener(buttonListener);
        buttonFine.addActionListener(buttonListener);

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(lettera,BorderLayout.NORTH); p1.add(buttonConferma, BorderLayout.SOUTH);

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(buttonInizia, BorderLayout.NORTH); p2.add(p1,BorderLayout.SOUTH);

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(buttonFine, BorderLayout.NORTH);  

        JPanel p4 = new JPanel();
        p4.setLayout(new BorderLayout());
        p4.add(p3, BorderLayout.NORTH);  p4.add(p2, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(p4,BorderLayout.EAST); add(impiccato, BorderLayout.WEST);

    }

    public void update(String string){
        impiccato.setText(string);
    }


    public void addObserver(FrameObserver ob){
        observers.add(ob);
    }

    public void updateObservers(int numPanel){
        for(FrameObserver ob : observers){
            ob.update(numPanel);
        }
    }
}
