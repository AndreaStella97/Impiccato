package ClientSystem.Grafica;

import ClientSystem.Client;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private Client client;

    public Frame(Client client){
        this.client=client;
        setTitle("IMPICCATO");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        setSize(dim.width/4,dim.height/4);
        setLocation(dim.width/3, dim.height/3);
        Panel panel = new Panel(client);
        Container contentPane = getContentPane();
        contentPane.add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
