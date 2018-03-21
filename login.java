/*==============================================================
 * 
 * Class:  login                     Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: logs into the program
 * 
 * Date of this revision: January 31, 2016       Teacher: Nabiha Khan
 * 
 * School: British International School of Jeddah, Jeddah, Saudi Arabia
 * 
 * Language: Java 1.8.0.25		               Target Operating System: Java Virtual Machine
 * 
 * System: Pentium N3530 Running Windows 10 x64 edition   IDE: Eclipse Luna Service Release 2 (4.4.2)
 * 
 ==============================================================*/
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class login 
{

	private JFrame frame;
	Connection connect = null; // connect is a global variable
	int chancesRemaining = 3;// flag used to see the amount of tries remaining
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					login window = new login();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private JTextField textField;
	private static JPasswordField passwordField;
	
	/**
	 * Create the application.
	 */
	public login() 
	{
		
		initialize();
		connect = sqlConnection.databaseConnector();// calls java Connection class to check connection
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(100, 77, 85, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(100, 117, 68, 14);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(195, 74, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 114, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				loggingIn();
			}
		});
		btnLogin.setBounds(167, 178, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblLogo = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/SaraLogo.png")).getImage();
		lblLogo.setIcon(new ImageIcon(image));
		lblLogo.setBounds(23, 11, 46, 64);
		frame.getContentPane().add(lblLogo);
		
		JLabel label = new JLabel("");
		Image image1 = new ImageIcon(this.getClass().getResource("/SaraLogo.png")).getImage();
		label.setIcon(new ImageIcon(image1));
		label.setBounds(378, 11, 46, 64);
		frame.getContentPane().add(label);
		
		
	}
	
	public void loggingIn()
	{
		try
		{
			
			String query = "select * from EmployeeData where Username=? and password =?";//Query for the database
			PreparedStatement pst = connect.prepareStatement(query); //pass the query to the prepared statement
			pst.setString(1, textField.getText()); //passing the username from the text box to the prepared statement
			pst.setString(2, encrypt()); //passing the password from the text box to the prepared statement
			
		
			ResultSet result= pst.executeQuery();//executes query//
			
			int count = 0; //once the query is passed 
			while(result.next())//Searches through all possible responses and compares given data to data on file.
			{
				count++; // for incrementing
			}
			if(count == 1) 
			{
				
					mainMenu main = new mainMenu();
					main.setVisible(true);
				frame.dispose();// closes login screen
				
			}
			else if (count > 1)
			{
				JOptionPane.showMessageDialog(null, "Duplicate Username and Password"); //To reduce data entry errors.//
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Username or Password are incorrect ... Try Again");
					
					chancesRemaining = chancesRemaining - 1;
					JOptionPane.showMessageDialog(null, "Warning, Number of Incorrect attempts remaining: " + chancesRemaining); //To reduce data entry errors.//
					
				if (chancesRemaining == 0)
				{
					JOptionPane.showMessageDialog(null, "You have entered an incorrect combination too many times");
					System.exit(0);
				}
				
			}
			result.close();//Closing the connection (sqlite only provide one connection at a time)
			pst.close();//Closing the connection (sqlite only provide one connection at a time)
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e); //dialog box that shows the error(s) that occurred.//
		}
	}	


	public static String encrypt() /*This method is used to encrypt the password being entered by the user. The plaintext password is converted into a
	ciphertext using a caesar cipher. The cipher is saved in the database*/
	{
		String plaintext = passwordField.getText();
		char[]cip = plaintext.toCharArray();
		final int shift = 3; // the shift value does not change therefore it can be declared as a final int variable.
		int num = cip.length;
		
		String ciphertext = "";
		
		for (int i = 0; i < num; i++)
		{
			char let = cip[i];
			
			let = (char) (let + shift);
			if (let > 'z')
			{
				let = (char) (let - 26);
			}
			
		cip[i] = let;
		ciphertext = ciphertext + cip[i];
		}
		return ciphertext;
		
	}
	
	public static String CipherText()
	{
		String cipher = encrypt();
		return cipher;
	}
	
}
