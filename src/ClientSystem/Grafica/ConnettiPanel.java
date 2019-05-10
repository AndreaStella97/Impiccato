package ClientSystem.Grafica;

import ClientSystem.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ConnettiPanel extends JPanel  {

    Client client;
    ArrayList<FrameObserver> observers = new ArrayList<>();

    public ConnettiPanel(Client client){
        this.client = client;
        initPanel();

    }

    private void initPanel(){
        JButton buttonConnetti = new JButton("Connetti");
        buttonConnetti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.connetti();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                updateObservers(2);
            }
        });
        add(buttonConnetti);
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
