package javaprojectfinal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class CRM2 extends JFrame implements ActionListener{
	  JPanel panel;
	   JLabel product_label, quantity_label, message;
	   JTextField product, quantityofproduct;
	   
	   JButton submit, cancel;
	public int diff;
	
	   public Connection connection = null;
	     public ResultSet rs = null ;
	     public String quantity;
	     ResultSet customer_rs1 = null ;
	     String str[]= {"w","q"};
	    
	      
        
   
            
            public String myUrl = "jdbc:sqlserver://127.0.0.1:49695;DatabaseName=javaproject";
            public String jdbcUsername = "sa";
            public String jdbcPassword = "admin";
           


        		
        		public Connection getConnection() {
        			Connection connection = null;
        			try {
        				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        				connection = DriverManager.getConnection(myUrl, jdbcUsername, jdbcPassword);
        
        				 
        				product_label = new JLabel();
        			      product_label.setText("Enter Product");
        			      product = new JTextField();

        			      // Password Label

        			      quantity_label = new JLabel();
        			      quantity_label.setText("Enter Quanitity ");
        			      quantityofproduct = new JTextField();

        			      // Submit

        			      submit = new JButton("SUBMIT");

        			      panel = new JPanel(new GridLayout(3, 1));
        			      panel.add(product_label);
        			      panel.add(product);
        			      panel.add(quantity_label);
        			      panel.add(quantityofproduct);

        			      message = new JLabel();
        			      panel.add(message);
        			      panel.add(submit);

        			      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        			      // Adding the listeners to components..
        			      
        			      submit.addActionListener(this);
        			      add(panel, BorderLayout.CENTER);
        			      setTitle("Retail Management System");
        			      setSize(450,350);
        			      setVisible(true);
        			      message = new JLabel();
        			      
        				
        				
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} catch (Exception e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        			System.out.println("working");
        			
        			return connection;
        		}

        		  @Override
			      public void actionPerformed(ActionEvent ae) {
        			  try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						connection = DriverManager.getConnection(myUrl, jdbcUsername, jdbcPassword);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
      				
			         String product_info = product.getText();
			         String quantity_info = quantityofproduct.getText();
			         PreparedStatement preparedStmt2;
			         final String get_quantity = "select product_quantity from warehouse where product_id=?";
			         str[0]= product_info;
			         try {
						preparedStmt2 = connection.prepareStatement(get_quantity);
						  preparedStmt2.setString(1,product_info);
						  ResultSet rs = preparedStmt2.executeQuery();
						  System.out.println("update executed");
						  if(rs.next()){
							  System.out.println("into executed");
					            quantity= rs.getString(1);
					            
					          System.out.println(quantity);
					            //  rs.first();
					            }
					            
				            preparedStmt2.close();
						  
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
		                
						
				
			         
			          final String Update_quantity = "update warehouse set product_quantity=? where product_id=?";
			          PreparedStatement preparedStmt;
					try {
						
						diff=Integer.parseInt(quantity.trim())-Integer.parseInt(quantity_info);
						
						String diff2=Integer.toString(diff);
						str[0]= product_info;
		                str[1]= diff2;
						preparedStmt = connection.prepareStatement(Update_quantity);
						 
			                
						
						  preparedStmt.setString(1,str[1]);
				          preparedStmt.setString(2, str[0]);
				          preparedStmt.executeUpdate();
				            preparedStmt.close();
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			          
			       	  
			       	  
			        
			      }
				
    
}
