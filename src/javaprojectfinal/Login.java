package javaprojectfinal;

import javaprojectfinal.AdminLoginclass;

import javaprojectfinal.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javaprojectfinal.AdminLoginclass;

public class Login extends JFrame implements ActionListener{

	String str[]= {"w","e"};
	String pass2=null;
   JPanel panel;
   JLabel user_label, password_label, message;
   JTextField userName_text;
   JPasswordField password_text;
   JButton submit, cancel;

   Login() {

      // Username Label
      user_label = new JLabel();
      user_label.setText("User Name :");
      userName_text = new JTextField();

      // Password Label

      password_label = new JLabel();
      password_label.setText("Password :");
      password_text = new JPasswordField();

      // Submit

      submit = new JButton("SUBMIT");

      panel = new JPanel(new GridLayout(3, 1));
      panel.add(user_label);
      panel.add(userName_text);
      panel.add(password_label);
      panel.add(password_text);

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
   }

   public static void main(String[] args) {
      new Login();
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      String userName = userName_text.getText();
      String password = password_text.getText();
      
      if (userName.trim().equals("admin") && password.trim().equals("admin")) {
    	  
    	 // AdminLogin obj2=new AdminLogin();
    	  AdminLoginclass obj2=new AdminLoginclass();
    	  System.out.println("into getconnection");
    	 try {
			obj2.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      else {
      try {
    	  final String myUrl = "jdbc:sqlserver://127.0.0.1:49695;DatabaseName=javaproject";
          final String jdbcUsername = "sa";
          final String jdbcPassword = "admin";
    	  Connection connection = null;
      
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(myUrl, jdbcUsername, jdbcPassword);
			
			
		
	         PreparedStatement preparedStmt2;
	         final String get_pass = "select pass from employees where employee_id=?";
			str[0]=userName;
			try {
			preparedStmt2 = connection.prepareStatement(get_pass);
			preparedStmt2.setString(1,str[0]);
			 ResultSet rs = preparedStmt2.executeQuery();
			 
			  if(rs.next()){
				
		            pass2= rs.getString(1).trim();
		            System.out.println(pass2);
		            if(pass2.equalsIgnoreCase(password)) {
		            	 
		            	System.out.println("into");
		                CRM2 obj2=new CRM2();
		                obj2.getConnection();
		                System.out.println("exit");
		            }
		            
			  }
			 }
			 catch(SQLException e){
				 System.out.println("Invalid User");
			 }
		          
		            
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Invalid User");
      
      
		}
      
  
    	 
      }
   }
}