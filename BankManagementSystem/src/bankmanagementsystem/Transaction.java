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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction extends JFrame implements ActionListener{

    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    
    Transaction(){
        
        setFont(new Font("System", Font.BOLD, 22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("TRANSACTION");
        int y = fm.stringWidth(" ");
        int z = getWidth() - (3*x);
        int w = z/y;
        String pad ="";
        //for (int i=0; i!=w; i++) pad +=" ";   
        pad = String.format("%"+w+"s", pad);
        setTitle(pad+"TRANSACTION");
    
        l1 = new JLabel("||YOUR BANK WELCOMES YOU||");
        l1.setFont(new Font("System", Font.BOLD, 40));
    
       
        b1 = new JButton("DEPOSIT");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.blue);
        b1.setForeground(Color.WHITE);
    
        b2 = new JButton("WITHDRAWL");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.blue);
        b2.setForeground(Color.WHITE);
        
        
        b4 = new JButton("PASS-BOOK");
        b4.setFont(new Font("System", Font.BOLD, 18));
        b4.setBackground(Color.blue);
        b4.setForeground(Color.WHITE);
        
        
        b6 = new JButton("BALANCE ENQUIRY");
        b6.setFont(new Font("System", Font.BOLD, 18));
        b6.setBackground(Color.blue);
        b6.setForeground(Color.WHITE);
        
        b7 = new JButton("EXIT");
        b7.setFont(new Font("System", Font.BOLD, 18));
        b7.setBackground(Color.blue);
        b7.setForeground(Color.WHITE);
        
        
        setLayout(null);
        
        l1.setBounds(100,100,700,40);
        add(l1);
        
        b1.setBounds(40,250,300,60);
        add(b1);
        
        b2.setBounds(440,250,300,60);
        add(b2);
        
        
        b4.setBounds(440,360,300,60);
        add(b4);
        
        
        b6.setBounds(40,360,300,60);
        add(b6);
        
        b7.setBounds(240,600,300,60);
        add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        
        getContentPane().setBackground(Color.GRAY);
        
        setSize(800,700);
        setLocation(400,70);
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==b1){ 
            
            new Deposit().setVisible(true);
            setVisible(false);
            
        }
        else if(ae.getSource()==b2){ 
            
            new Withdrawl().setVisible(true);
            setVisible(false);
            
        }
        else if(ae.getSource()==b4){ 
            
            new MiniStatement().setVisible(true);
            setVisible(false);
            
        }else if(ae.getSource()==b6){ 
            
            String pinn = JOptionPane.showInputDialog("Enter PIN"); 
            conn c1 = new conn();
                    
            try {
                
                ResultSet rs = c1.s.executeQuery(" SELECT balance FROM bank where pin ='" + pinn + "' ORDER BY sno DESC LIMIT 1");
                
                if(rs.next()){
                
                String balance = rs.getString("balance");
                
                JOptionPane.showMessageDialog(null,"Your Account Balance is "+balance);
                
                }
                
                  
                
            } catch (Exception e) {
             
                e.printStackTrace();
            
            }
            
  
                    
            
        }else if(ae.getSource()==b7){ 
            
            System.exit(0);
            
        }
        
            
        
        
    }
    
    public static void main(String[] args){
        new Transaction().setVisible(true);
    }
}
