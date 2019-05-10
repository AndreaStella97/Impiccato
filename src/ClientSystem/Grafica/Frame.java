package ClientSystem.Grafica;

import ClientSystem.Client;

import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame implements FrameObserver{

    private Client client;
    ConnettiPanel panel1;
    IniziaPanel panel2;
    ImpiccatoPanel panel3;

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
        panel2 = new IniziaPanel(client);
        panel2.addObserver(this);
        panel3 = new ImpiccatoPanel(client);
        panel3.addObserver(this);

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

            case 3:
                getContentPane().removeAll();
                getContentPane().add(panel3);
                repaint();
                revalidate();
                break;

        }

    }
}
