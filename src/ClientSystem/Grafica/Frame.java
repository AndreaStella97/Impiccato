package ClientSystem.Grafica;

import ClientSystem.Client;

import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame implements FrameObserver{

    private Client client;
    ConnettiPanel panel1;
    ImpiccatoPanel panel2;

    public Frame(Client client){
        this.client=client;
        setTitle("IMPICCATO");
        setResizable(false);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        setSize(dim.width/4,dim.height/4);
        setLocation(dim.width/3, dim.height/3);

        panel1 = new ConnettiPanel(client);
        panel1.addObserver(this);
        panel2 = new ImpiccatoPanel(client);
        panel2.addObserver(this);

        getContentPane().add(panel1);
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void update(int numPanel) {
        switch (numPanel){
            case 1:
                getContentPane().removeAll();
                getContentPane().add(panel1);
                repaint();
                revalidate();
                break;

            case 2:
                getContentPane().removeAll();
                getContentPane().add(panel2);
                repaint();
                revalidate();
                break;
        }

    }
}
