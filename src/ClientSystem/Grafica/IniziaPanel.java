package ClientSystem.Grafica;

import ClientSystem.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class IniziaPanel extends JPanel  {
    private Client client;
    private ArrayList<FrameObserver> observers = new ArrayList<>();
    public IniziaPanel(Client client){
        this.client = client;
        JButton buttonInizia = new JButton("Inizia");
        buttonInizia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.inizia();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                updateObservers(3);
            }
        });
        add(buttonInizia);

    }

    public void addObserver(FrameObserver ob){
        observers.add(ob);
    }

    public void updateObservers(int numPanel) {
        for (FrameObserver ob : observers) {
            ob.update(numPanel);
        }
    }
}
