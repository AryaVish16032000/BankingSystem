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

public class MiniStatement extends JFrame implements ActionListener{
 
    JTable t1;
    JButton b1,b2;
    String x[] = {"Account number","Deposit","Withdraw","Balance"};
    String y[][] = new String[20][5];
    int i=0, j=0;
    
    MiniStatement(){
        super("PASS BOOK");
        setSize(600,550);
        setLocation(200,200);
     		 String pinn = JOptionPane.showInputDialog("Enter PIN");

        
        try{
            conn c1  = new conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '" + pinn + "' ");
          //  String s1 = "select * from bank";
          //  ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("accno");
                //y[i][j++]=rs.getString("date");
                y[i][j++]=rs.getString("deposit");
                y[i][j++]=rs.getString("withdraw");
                y[i][j++]=rs.getString("balance");
                i++;
                j=0;
            }
            t1 = new JTable(y,x);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    
        b2= new JButton("Back");
        add(b2,"South");
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        b2.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent ae){
        try{
            new Transaction().setVisible(true);
             setVisible(false);
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        new MiniStatement().setVisible(true);
    }
    
}

