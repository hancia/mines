import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Parameters extends JFrame implements ActionListener {
    private int x,minesnumber,game_time;
    private JComboBox levels;
    private JButton save;
    private ButtonPanel parent;
    private JCheckBox check,check_time;
    private JLabel text1;
    private JTextField text_size,text_mines,text_time;
    public Parameters(ButtonPanel p){
        super("Ustawienia");
        parent=p;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(300,300);
        setLocation(600,300);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        text1 = new JLabel("Wybierz poziom: ");
        add(text1);

        String[] lev;
        lev=new String[3];
        lev[0]="łatwy";
        lev[1]="średni";
        lev[2]="trudny";
        levels = new JComboBox(lev);
        levels.addActionListener(this);
        levels.setSelectedIndex(0);
        c.gridy=1;
        add(levels,c);

        check = new JCheckBox("Niestandardowa plansza");
        check.addActionListener(this);
        c.gridy=2;
        add(check,c);

        text_size= new JTextField("Rozmiar");
        text_mines = new JTextField("Miny");
        text_size.setEnabled(false);
        text_mines.setEnabled(false);
        c.gridy=3;
        add(text_size,c);
        add(text_mines,c);
        text_size.setPreferredSize(new Dimension(60,20));
        text_mines.setPreferredSize(new Dimension(60,20));

        check_time = new JCheckBox("Gra na czas");
        check_time.addActionListener(this);
        c.gridy=7;
        add(check_time,c);

        text_time = new JTextField("Czas");
        text_time.setEnabled(false);
        add(text_time,c);

        game_time=0;

        save=new JButton("Zapisz");
        save.addActionListener(this);
        c.gridy=8;
        add(save,c);
    }
    public int getsize(){
        return x;
    }
    public int getMines(){
        return minesnumber;
    }
    public int getTime(){
        return game_time;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==levels){
            String temp=(String)levels.getSelectedItem();
            switch(temp){
                case "łatwy": {
                    x=8; minesnumber=10;break;
                }
                case "średni":{
                    x=16;minesnumber=38;break;
                }
                case "trudny":{
                    x=16; minesnumber=51;break;
                }
            }
        }
        if(source==save){
            if(check.isSelected()){
                x=Integer.parseInt(text_size.getText());
                minesnumber=Integer.parseInt(text_mines.getText());

            }
            if(check_time.isSelected()){
                game_time=Integer.parseInt(text_time.getText());
                parent.settime(this);
            }
            parent.setparams(this);
            this.dispose();
        }
        if(source==check){
            if(!check.isSelected()){
                text_size.setEnabled(false);
                text_mines.setEnabled(false);
                text_size.setText("Rozmiar");
                text_mines.setText("Miny");
            }
            else{
                text_size.setEnabled(true);
                text_mines.setEnabled(true);
                text_size.setText("");
                text_mines.setText("");
            }
        }
        if(source==check_time){
            if(!check_time.isSelected()){
                text_time.setEnabled(false);
                text_time.setText("Czas");
            }
            else{
                text_time.setEnabled(true);
                text_time.setText("");
            }
        }
    }
}
