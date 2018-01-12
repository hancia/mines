import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Game extends JFrame implements ActionListener {
    private JButton[][] Buttons;
    private Mines game;
    private Menu main_menu;
    private int size,mines_number;
    public Game(Menu menu,int x, int minesnumber){
        super("Saper");
        main_menu=menu;
        size=x;
        mines_number=minesnumber;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500,500);
        setLocation(600,300);
        setLayout(new GridLayout(size,size,0,0));
        Buttons = new JButton[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size;j++) {
                Buttons[i][j]=new JButton();
                Buttons[i][j].setSize(1000,1000);
                Buttons[i][j].addActionListener(this);
                add(Buttons[i][j]);
            }
        game = new Mines(size,mines_number);
    }
    private void ShowButtonField(JButton button,int i, int j){
        button.setEnabled(false);
        if(game.mines[i][j]!=0)
            button.setText(Integer.toString(game.mines[i][j]));
        else
            button.setText(" ");
    }
    private void ShowFields(int x, int y){
        if(game.mines[x][y]==0) {
            for(int i=x-1; i<=x+1; i++)
                if(i>=0&&i<size)
                    for(int j=y-1; j<=y+1; j++)
                        if(j>=0&&j<size)
                            if((i!=x||j!=y)&&game.mines[i][j]!=-1) {
                                if(Buttons[i][j].isEnabled()) {
                                    ShowButtonField(Buttons[i][j], i, j);
                                    ShowFields(i, j);
                                }
                            }
        }
    }
    private int EnabledButtons(){
        int temp=0;
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                if(Buttons[i][j].isEnabled()) temp++;
        return temp;
    }
    private void BackToMenu(){
        this.dispose();
        main_menu.setVisible(true);
    }

        @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++)
            if(source==Buttons[i][j]){
                ShowButtonField(Buttons[i][j],i,j);
                if(game.mines[i][j]==-1) {
                    for(int x=0; x<size; x++)
                        for(int y=0; y<size; y++)
                            ShowButtonField(Buttons[x][y],x,y);
                    JOptionPane.showMessageDialog(super.rootPane,"Przegrałeś :/");
                    BackToMenu();
                }
                else{
                    ShowFields(i,j);
                    if(EnabledButtons()==mines_number) {
                        JOptionPane.showMessageDialog(super.rootPane,"Znalazłeś wszystkie miny :D");
                        BackToMenu();
                    }
                }
            }
        }
    }
}
