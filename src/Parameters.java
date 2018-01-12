import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Parameters extends JFrame implements ActionListener {
    private int x,minesnumber;
    private JComboBox levels;
    private JButton save;
    private ButtonPanel parent;
    public Parameters(ButtonPanel p){
        super("Ustawienia");
        parent=p;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(200,200);
        setLocation(600,300);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] lev;
        lev=new String[3];
        lev[0]="łatwy";
        lev[1]="średni";
        lev[2]="trudny";
        levels = new JComboBox(lev);
        levels.addActionListener(this);
        levels.setSelectedIndex(0);
        add(levels);
        save=new JButton("Zapisz");
        save.addActionListener(this);
        add(save);
    }
    public int getsize(){
        return x;
    }
    public int getMines(){
        return minesnumber;
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
            parent.setparams(this);
        }
    }
}
