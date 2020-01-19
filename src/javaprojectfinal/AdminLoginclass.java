package javaprojectfinal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




	public class AdminLoginclass extends JFrame implements ActionListener {
		public AdminLoginclass() {
		}

		String str[]= {"1","2","3","4","5","6"};
		String str2[]= {"1"};
		private JPanel contentPane;
		private JPanel panel;
		private JTextField emp_id;
		private JTextField f_name;
		private JTextField l_name;
		private JTextField phone;
		private JTextField email;
		private JTextField product_id;
		private JTextField quantity;
		private JTextField pass;
		private JTextField total;
		  public Connection connection = null;
		  public int diff;
		  public String quantity_info2;
	

		public String myUrl = "jdbc:sqlserver://127.0.0.1:49695;DatabaseName=javaproject";
		public String jdbcUsername = "sa";
		public String jdbcPassword = "admin";
		public Connection getConnection() throws ClassNotFoundException, SQLException {
		 
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 connection = DriverManager.getConnection(myUrl, jdbcUsername, jdbcPassword);
		
		try
		{
			 
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 connection = DriverManager.getConnection(myUrl, jdbcUsername, jdbcPassword);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 702, 457);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.windowBorder);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel panel_1 = new JPanel();
			panel_1.setBackground(SystemColor.controlHighlight);
			panel_1.setBounds(349, 11, 315, 389);
			contentPane.add(panel_1);
			panel_1.setLayout(null);

			JLabel lblNewLabel_6 = new JLabel("Product ID");
			lblNewLabel_6.setBounds(30, 76, 65, 14);
			panel_1.add(lblNewLabel_6);

			JLabel lblNewLabel_7 = new JLabel("Billing");
			lblNewLabel_7.setBounds(110, 11, 195, 51);
			panel_1.add(lblNewLabel_7);

			JLabel lblNewLabel_8 = new JLabel("Quantity");
			lblNewLabel_8.setBounds(30, 138, 46, 14);
			panel_1.add(lblNewLabel_8);
			

			product_id = new JTextField();
			product_id.setBounds(150, 73, 86, 20);
			panel_1.add(product_id);
			product_id.setColumns(10);

			quantity = new JTextField();
			quantity.setBounds(150, 135, 86, 20);
			panel_1.add(quantity);
			quantity.setColumns(10);

			total = new JTextField();
			total.setBounds(105, 250, 102, 41);
			panel_1.add(total);
			total.setColumns(10);

			JButton btnSubmit = new JButton("Submit");
			btnSubmit.setBounds(105, 184, 102, 41);
			panel_1.add(btnSubmit);
			btnSubmit.addActionListener(new ActionListener() {
		
			
			
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
 				
		         String product_info = product_id.getText();
		         String quantity_info = quantity.getText();
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
				            quantity_info2= rs.getString(1);
				            
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
					
					diff=Integer.parseInt(quantity_info2.trim())-Integer.parseInt(quantity_info);
					
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
			
			});		
			
			
			
			 
			
			
			
			
			
			
			
			

			panel = new JPanel();
			panel.setBackground(SystemColor.activeCaption);
			panel.setBounds(10, 11, 329, 389);
			contentPane.add(panel);
			panel.setLayout(null);

			JLabel lblNewLabel = new JLabel("Employee ID");
			lblNewLabel.setBounds(10, 52, 71, 14);
			panel.add(lblNewLabel);

			emp_id = new JTextField();
			emp_id.setBounds(148, 49, 86, 20);
			panel.add(emp_id);
			emp_id.setColumns(10);
			
			JLabel passlbl = new JLabel("password");
			passlbl.setBounds(10, 321, 71, 14);
			panel.add(passlbl);

			pass = new JPasswordField();
			pass.setBounds(148, 311, 86, 20);
			panel.add(pass);
			
			
			JLabel lblNewLabel_1 = new JLabel("First Name");
			lblNewLabel_1.setBounds(10, 105, 71, 14);
			panel.add(lblNewLabel_1);

			f_name = new JTextField();
			f_name.setBounds(148, 102, 86, 20);
			panel.add(f_name);
			f_name.setColumns(10);

			JLabel lblNewLabel_2 = new JLabel("HRMS");
			lblNewLabel_2.setBounds(126, 11, 119, 41);
			panel.add(lblNewLabel_2);

			JLabel lblNewLabel_3 = new JLabel("Last Name");
			lblNewLabel_3.setBounds(10, 157, 71, 14);
			panel.add(lblNewLabel_3);

			l_name = new JTextField();
			l_name.setBounds(148, 157, 86, 20);
			panel.add(l_name);
			l_name.setColumns(10);

			JLabel lblNewLabel_4 = new JLabel("Phone");
			lblNewLabel_4.setBounds(10, 217, 46, 14);
			panel.add(lblNewLabel_4);

			phone = new JTextField();
			phone.setBounds(148, 203, 86, 20);
			panel.add(phone);
			phone.setColumns(10);

			JLabel lblNewLabel_5 = new JLabel("Email ID");
			lblNewLabel_5.setBounds(10, 269, 46, 14);
			panel.add(lblNewLabel_5);

			email = new JTextField();
			email.setBounds(148, 266, 86, 20);
			panel.add(email);
			email.setColumns(10);

			JButton btnNewButton = new JButton("Add Employee");
			btnNewButton.setBounds(149, 367, 89, 23);
			panel.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("into button");
					PreparedStatement preparedStmt2;
//							String product=product_id.getText();
//							String quantity1=quantity.getText();
//							String emp_id1= emp_id.getText();
//							String f_name1=f_name.getText();
//							String l_name1=l_name.getText();
//							String phone1=phone.getText();
//							String email1=email.getText();
					final String insert_employees = "insert into employees values "+ "(?,?,?,?,?,?)";
					
					
					str[0]= emp_id.getText().trim();
					
					str[1]= pass.getText().trim();
					
					str[2]= f_name.getText().trim();
					
					str[3]= l_name.getText().trim();
					
					str[4]= phone.getText().trim();
					
					str[5]= email.getText().trim();
					
				
					   try {
							preparedStmt2 = connection.prepareStatement(insert_employees);
							  
							  
							  preparedStmt2.setString(1,str[0]);
							  preparedStmt2.setString(2,str[1]);
							  preparedStmt2.setString(3,str[2]);
							  preparedStmt2.setString(4,str[3]);
							  preparedStmt2.setString(5,str[4]);
							  preparedStmt2.setString(6,str[5]);
							  
							  
							 preparedStmt2.executeUpdate();
					            preparedStmt2.close();
							  
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					
				}
			});			
			
			
			  JButton btnRemoveEmployee = new JButton("Remove Employee");
			  btnRemoveEmployee.setBounds(149, 335, 89, 23); 
			  panel.add(btnRemoveEmployee);
			  btnRemoveEmployee.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("into button");
						PreparedStatement preparedStmt2;
//								String product=product_id.getText();
//								String quantity1=quantity.getText();
//								String emp_id1= emp_id.getText();
//								String f_name1=f_name.getText();
//								String l_name1=l_name.getText();
//								String phone1=phone.getText();
//								String email1=email.getText();
						final String delete_employees = "delete from employees where employee_id=?";
						
						
						str[0]= emp_id.getText().trim();
						
					
						   try {
								preparedStmt2 = connection.prepareStatement(delete_employees);
								  
								  
								  preparedStmt2.setString(1,str[0]);
								  
								  
								  
								 preparedStmt2.executeUpdate();
						            preparedStmt2.close();
								  
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						
					}
				});
				
			  
			 			  
			  
			  
			  
			  
			 
			
			 setVisible(true);
		      

		}
		catch(SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		

		}
		
			
		return connection;
		}
	
		
		

	@Override
    public void actionPerformed(ActionEvent ae) {
		  try {
			  
			
			  
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	}
	


