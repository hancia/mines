import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame{
    public Menu(){
        super("Saper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocation(600,300);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonPanel = new ButtonPanel(this);
        add(buttonPanel);
        pack();
    }
}
