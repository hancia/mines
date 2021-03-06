import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Game extends JFrame implements ActionListener{
    private JButton[][] Buttons;
    private Mines game;
    private Menu main_menu;
    private GamePanel gamepanel;
    private Time time;
    private int size,mines_number,game_time,z;
    private Component parentComponent;
    private ButtonPanel parent;
    private boolean run;
    public Game(Menu menu,int x, int minesnumber,int game_t,Time time_t,ButtonPanel parent_t){
        super("Saper");
        main_menu=menu;
        size=x;
        mines_number=minesnumber;
        game_time=game_t;
        time=time_t;
        parent=parent_t;
        z=0;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700,700);
        setResizable(false);
        setLocation(600-(size+200),300-(size+200));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.BOTH;
        c.gridy=0;
        c.weightx=0.5;
        c.weighty=0.5;
        Buttons = new JButton[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size;j++) {
                Buttons[i][j]=new JButton();
                Buttons[i][j].setPreferredSize(new Dimension(15,15));
                Buttons[i][j].addActionListener(this);
                add(Buttons[i][j],c);
                if(j==(size-1)) c.gridy++;
            }
        c.gridwidth=size;
        gamepanel = new GamePanel(this,main_menu);
        add(gamepanel,c);

        c.gridy++;
        c.gridx=size-1;
        c.weightx=0.5;
        c.weighty=0.5;
        c.gridwidth=size;
        add(time,c);

        game = new Mines(size,mines_number);

        parentComponent=super.rootPane;
        run=true;
    }
    private void ShowButtonField(JButton button,int i, int j){
        button.setEnabled(false);
        if(game.mines[i][j]==-1){
            try {
                Image img = ImageIO.read(getClass().getResource("mine.png"));
                img = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                button.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            if (game.mines[i][j] != 0)
                button.setText(Integer.toString(game.mines[i][j]));
            else
                button.setText(" ");
        }
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
    public void BackToMenu(){
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
                    parent.run=false;
                    JOptionPane.showMessageDialog(super.rootPane,"Przegrałeś :/");
                    BackToMenu();
                }
                else{
                    ShowFields(i,j);
                    if(EnabledButtons()==mines_number) {
                        parent.run=false;
                        JOptionPane.showMessageDialog(super.rootPane,"Znalazłeś wszystkie miny :D");
                        BackToMenu();
                    }
                }
            }
        }
    }
}
