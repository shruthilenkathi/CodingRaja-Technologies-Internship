package bankmanagementsystem;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton back;
String pinnumber;
    BalanceEnquiry(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons-20240520T111110Z-001/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        back=new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        Conn c = new Conn();
        double balance = 0;
            try {
                ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pinnumber='" + pinnumber + "'");
                
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Double.parseDouble(rs.getString("amount"));
                    } else {
                        balance -= Double.parseDouble(rs.getString("amount"));
                    }
                }
            }catch(Exception e){
                    System.out.println(e);
                }
                JLabel text=new JLabel("Your Current acciunt balance is Rs"+balance);
                text.setForeground(Color.WHITE);
                text.setBounds(170,300,400,30);
                image.add(text);


        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }
    public static void main(String args[]){
        new BalanceEnquiry("");
    }
}
