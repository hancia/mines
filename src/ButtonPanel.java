import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener{

    public static final int HEIGHT = 100;
    public static final int WIDTH = 300;
    private JButton gameButton;
    private JButton parametersButton;
    private JButton exitButton;
    private int size,mines_number,game_time;
    private Game game;
    Menu menu_parent;
    public ButtonPanel(Menu parent) {
        menu_parent=parent;
        gameButton = new JButton("Nowa gra");
        parametersButton = new JButton("Parametry");
        exitButton = new JButton("Wyjście");

        gameButton.addActionListener(this);
        parametersButton.addActionListener(this);
        exitButton.addActionListener(this);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(gameButton);
        add(parametersButton);
        add(exitButton);

        size=8;
        mines_number=10;
    }
    public void setparams(Parameters parameters_window){
        size=parameters_window.getsize();
        mines_number=parameters_window.getMines();

    }
    public void settime(Parameters parameters_window){
        game_time=parameters_window.getTime();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == gameButton) {
            game = new Game(menu_parent,size,mines_number,game_time);
            menu_parent.setVisible(false);
        }

        else if(source == parametersButton) {
            Parameters parameters_window = new Parameters(this);
        }
        else if(source == exitButton)
            System.exit(0);
    }
}