/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagementsystem;

/**
 *
 * @author maini_
 */



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    
    Withdrawl(){
        
        setFont(new Font("System", Font.BOLD, 15));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("WITHDRAWAL");
        int y = fm.stringWidth(" ");
        int z = getWidth() - (2*x);
        int w = z/y;
        String pad ="";
        //for (int i=0; i!=w; i++) pad +=" ";   
        pad = String.format("%"+w+"s", pad);
        setTitle(pad+"WITHDRAWAL");
        
        
        l1 = new JLabel("MAXIMUM ONE-TIME WITHDRAWAL");
        l1.setFont(new Font("System", Font.BOLD, 20));
        
        l2 = new JLabel("IS RS.5,000");
        l2.setFont(new Font("System", Font.BOLD, 20));
        
        l3 = new JLabel("ENTER YOUR AMOUNT");
        l3.setFont(new Font("System", Font.BOLD, 15));
        
        l4 = new JLabel("Enter Pin");
        l4.setFont(new Font("System", Font.BOLD, 10));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD,15));
        
        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 10));
        
        b1 = new JButton("WITHDRAW");
        b1.setFont(new Font("System", Font.BOLD, 7));
        b1.setBackground(Color.blue);
        b1.setForeground(Color.WHITE);
    
        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD,7));
        b2.setBackground(Color.blue);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("EXIT");
        b3.setFont(new Font("System", Font.BOLD,7));
        b3.setBackground(Color.blue);
        b3.setForeground(Color.WHITE);
        
        
        setLayout(null);
        
        l4.setBounds(10,10,80,30);
        add(l4);
        
        t2.setBounds(90,10,40,30);
        add(t2);
        
        l1.setBounds(110,100,800,60);
        add(l1);
        
        l2.setBounds(215,140,800,60);
        add(l2);
        
        l3.setBounds(175,200,500,60);
        add(l3);
        
        t1.setBounds(180,250,150,50);
        add(t1);
        
        b1.setBounds(150,320,100,50);
        add(b1);
        
        b2.setBounds(250,320,100,50);
        add(b2);
        
        b3.setBounds(200,380,100,50);
        add(b3);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        
        getContentPane().setBackground(Color.gray);
        
        setSize(500,500);
        setLocation(500,90);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
       
        try{        
           
            String a = t1.getText();
            String b = t2.getText();
            
            
            
            double d = Double.parseDouble(a);
            if(ae.getSource()==b1){
                if(d>5000)
                    {
                        JOptionPane.showMessageDialog(null, "You are crossing your one-time Withdrawal limit");
                    }
                else if(t1.getText().equals("")){
                    
                      JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                    
                   
                
                }else{
                    
                    conn c1 = new conn();
                    
                    
                    
                    ResultSet rs = c1.s.executeQuery(" select * from bank where pin = '"+b+"' order by sno desc limit 1 ");
                    
                    double balance = 0;
                    if(rs.next()){
                        String pin = rs.getString("pin");
                        String accno = rs.getString("accno");
                        
                        balance = rs.getDouble("balance");
                        double sno = rs.getDouble("sno");
                        sno++;
                        if(balance-d<0)
                        {
                             JOptionPane.showMessageDialog(null, "You account have insufficient balance for this transaction");
                        }
                        else
                        {
                            balance-=d;
                            String q1= "insert into bank values('"+pin+"',null,'"+d+"','"+balance+"','"+accno+"','"+sno+"')";
                             c1.s.executeUpdate(q1);
                             JOptionPane.showMessageDialog(null, "Rs. "+a+" Debited Successfully");
                            new Transaction().setVisible(true);
                            setVisible(false);
                        }
                        
                    
                       
                    }
                    
                    
                    
                    
                    
                    
                    
                }
                
            }else if(ae.getSource()==b2){
                
                new Transaction().setVisible(true);
                setVisible(false);
                
            }else if(ae.getSource()==b3){
                
                System.exit(0);
                
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
            
    }

    
    
    public static void main(String[] args){
        new Withdrawl().setVisible(true);
    }
}
