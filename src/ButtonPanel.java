import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener{

    public static final int HEIGHT = 100;
    public static final int WIDTH = 300;
    private JButton gameButton;
    private JButton parametersButton;
    private JButton exitButton;
    private int size,mines_number,game_time,z;
    private Time time;
    private Game game;
    boolean run;
    private Component parentComponent;
    private Parameters parameters_window;
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
        run=true;
        time = new Time();
        z=0;
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
            new Thread(new Runnable(){
                public void run(){
                    run=true;
                    z=0;
                    Thread thisThread = java.lang.Thread.currentThread();
                    while(run) {
                        int finalZ = z;
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                time.setText(Integer.toString(finalZ));
                            }
                        });
                        try {
                            java.lang.Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                        z++;
                        if(game_time>0) {
                            if (z == game_time+1) {
                                JOptionPane.showMessageDialog(parentComponent, "Koniec czasu :/");
                                run = false;
                                game.BackToMenu();
                                thisThread.interrupt();
                            }
                        }
                        if(thisThread.isInterrupted()) return;
                    }
                }
            }).start();

            game = new Game(menu_parent,size,mines_number,game_time,time,this);
            menu_parent.setVisible(false);
        }

        else if(source == parametersButton) {
            parameters_window = new Parameters(this);
        }
        else if(source == exitButton)
            System.exit(0);
    }
}