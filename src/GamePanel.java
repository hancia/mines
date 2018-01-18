import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener{

    public static final int HEIGHT = 100;
    public static final int WIDTH = 300;
    private JButton menuButton;
    private JButton exitButton;
    private Time time;
    private int size,mines_number;
    Game parent;
    Menu parent_menu;

    public GamePanel(Game game_parent, Menu p_menu) {
        parent_menu = p_menu;
        parent=game_parent;
        menuButton = new JButton("Menu");
        exitButton = new JButton("Wyjście");

        menuButton.addActionListener(this);
        exitButton.addActionListener(this);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        GridBagConstraints c = new GridBagConstraints();
        c.weightx=1;
        add(menuButton,c);
        add(exitButton,c);

        size=8;
        mines_number=10;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == exitButton)
            System.exit(0);
        if(source == menuButton){
            parent.dispose();
            parent_menu.setVisible(true);
        }
    }
}