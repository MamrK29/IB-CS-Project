/*==============================================================
 * 
 * Class:  addEmployee                     Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: Used to add employees to the program
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

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import java.awt.Font;


public class addEmployee extends JFrame {

	Connection connect = null;
	private JPanel contentPane;
	private JTextField textFieldNationality;
	private JTextField textFieldDesignation;
	private JTextField textFieldUserName;
	private JTextField textFieldSurname;
	private JTextField textFieldFirstName;
	private JTextField textFieldID;
	private JCheckBox chckbxYes;
	private JComboBox comboBoxDay;
	private JComboBox comboBoxMonth;
	private JComboBox comboBoxYear;

	private String accessRights = "No"; //The Default value for access rights
	
	
	
	
	String password;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					addEmployee frame = new addEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public addEmployee() {
		//Creating the gui elements in the frame
		super("Adding Employee");
		int increment = 0;
		int dayincrement = 0;
		int monthIncrement = 0;
		String[] day = new String[31];
		for(int days = 31; days>0; days--)
		{
			String dayz = Integer.toString(days);
			day[dayincrement] = dayz;
			dayincrement++;
		}
		String [] month = new String [12];
		for(int m =12; m>0; m-- )
		{	
			String months = Integer.toString(m);
			month[monthIncrement] = months;
			monthIncrement++;
		}
		String [] year = new String[100];
		for(int num=2020; num>1920; num--)
		{
			String x = Integer.toString(num);
			year[increment] = x;
			increment++;
		}
		
		connect = sqlConnection.databaseConnector();
		
		setBounds(100, 100, 502, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(77, 31, 131, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(77, 56, 131, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(77, 81, 131, 14);
		contentPane.add(lblSurname);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(77, 106, 131, 14);
		contentPane.add(lblUsername);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(77, 163, 131, 14);
		contentPane.add(lblDesignation);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(77, 220, 131, 14);
		contentPane.add(lblDob);
		
		JLabel lblAccessRights = new JLabel("Access Rights");
		lblAccessRights.setBounds(77, 195, 131, 14);
		contentPane.add(lblAccessRights);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setBounds(77, 245, 131, 14);
		contentPane.add(lblNationality);
		
		chckbxYes = new JCheckBox("Yes");
		chckbxYes.setToolTipText("Tick if the employee has access rights");
		chckbxYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accessRights = "Yes";
			}
		});
		chckbxYes.setBounds(214, 187, 97, 23);
		contentPane.add(chckbxYes);
		
		textFieldNationality = new JTextField();
		textFieldNationality.setBounds(218, 242, 86, 20);
		contentPane.add(textFieldNationality);
		textFieldNationality.setColumns(10);
		
		textFieldDesignation = new JTextField();
		textFieldDesignation.setToolTipText("enter designation");
		textFieldDesignation.setBounds(218, 160, 86, 20);
		contentPane.add(textFieldDesignation);
		textFieldDesignation.setColumns(10);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldUserName.setBounds(214, 106, 86, 20);
		contentPane.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setToolTipText("Enter Surname");
		textFieldSurname.setBounds(214, 81, 86, 20);
		contentPane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setToolTipText("Enter First Name");
		textFieldFirstName.setBounds(214, 53, 86, 20);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldID = new JTextField();
		textFieldID.setToolTipText("Enter Six Digits Employee ID");
		textFieldID.setBounds(214, 28, 86, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(77, 131, 131, 14);
		contentPane.add(lblPassword);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkDate();
				
			}
		});
		btnSave.setBounds(150, 273, 75, 23);
		contentPane.add(btnSave);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter password");
		passwordField.setBounds(214, 127, 93, 23);
		contentPane.add(passwordField);
		
		comboBoxDay = new JComboBox(day);
		comboBoxDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxDay.setEditable(true);
		comboBoxDay.setSelectedItem("DD");
		comboBoxDay.setToolTipText("Enter Day");
		comboBoxDay.setBounds(218, 217, 67, 20);
		contentPane.add(comboBoxDay);
		
		comboBoxMonth = new JComboBox(month);
		comboBoxMonth.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxMonth.setEditable(true);
		comboBoxMonth.setSelectedItem("MM");
		comboBoxMonth.setToolTipText("Enter Month");
		comboBoxMonth.setBounds(291, 216, 67, 20);
		contentPane.add(comboBoxMonth);
		
		comboBoxYear = new JComboBox(year);
		comboBoxYear.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxYear.setEditable(true);
		comboBoxYear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBoxYear.setSelectedItem("YYYY");
		comboBoxYear.setToolTipText("Enter Year");
		comboBoxYear.setBounds(368, 217, 67, 20);
		contentPane.add(comboBoxYear);
		
		createMenu();
		
	}
	
	public void createMenu()//create menu bar
	{
		JMenuBar menuBar;
		JMenu file, clients, inventory, purchaseOrders, employee;
		JMenuItem logOut, exit, home,  clientMenu, addClient, inventoryMenu, addInventory, employeeMenu, employeeTable, employeeSearch, addEmployee;
		
		menuBar = new JMenuBar();
		
		//create the Clients item for the menu bar
		file = new JMenu("File");
		file.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
			}
		});
		
		menuBar.add(file);
		
		
				
		//create the Clients item for the menu bar
		clients = new JMenu("Clients");
		clients.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
			}
		});
		
		menuBar.add(clients);
		
		employee = new JMenu("Employee");
		employee.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				
			}
		});
		
		menuBar.add(employee);
		
		inventory = new JMenu("Inventory");
		inventory.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				
			}
		});
		
		menuBar.add(inventory);
		
		purchaseOrders = new JMenu("Purchasing Orders");
		purchaseOrders.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				purchaseOrder order = new purchaseOrder();
				order.setVisible(true);
				dispose();
			}
		});
		
		menuBar.add(purchaseOrders);
		
		logOut = new JMenuItem("Logoff");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.main(null);
				dispose();
			}
		});
		file.add(logOut); 
		
		home = new JMenuItem("Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.main(null);
				dispose();
			}
		});
		file.add(home); 
		
		
		//creates the Exit menu item for the file sub menu
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(exit); 
		
		clientMenu = new JMenuItem("Client Menu");
		clientMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientMenu client = new clientMenu();
				client.setVisible(true);
				dispose();
			}
		});
		clients.add(clientMenu);
		
		addClient = new JMenuItem("Add Client");
		addClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClient newClient = new addClient(); 
				newClient.setVisible(true);
				dispose();
			}
		});
		clients.add(addClient);
		
		employeeMenu = new JMenuItem("Employee Menu");
		employeeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeMenu emp = new EmployeeMenu();
				emp.setVisible(true);
				dispose();
			}
		});
		employee.add(employeeMenu);
		
		addEmployee = new JMenuItem("Add Employee");
		addEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployee add = new addEmployee();
				add.setVisible(true);
				dispose();
			}
		});
		employee.add(addEmployee);
		
		employeeSearch = new JMenuItem("Search");
		employeeSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeSearch search = new employeeSearch();
				search.setVisible(true);
				dispose();
			}
		});
		employee.add(employeeSearch);
		
		employeeTable = new JMenuItem("View Details");
		employeeTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee emp = new employee(); // creates an object
				emp.setVisible(true); // makes the frame visible
				dispose();
			}
		});
		employee.add(employeeTable);
		
		
		
		inventoryMenu = new JMenuItem("Inventory Menu");
		inventoryMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryMenu inventory = new inventoryMenu();
				inventory.setVisible(true);
				dispose();
			}
		});
		inventory.add(inventoryMenu);
		
		addInventory = new JMenuItem("Add Inventory");
		addInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInventory invent;
				try {
					invent = new addInventory();
					invent.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		inventory.add(addInventory);
		
		this.setJMenuBar(menuBar);
		
	}

	public void checkDate()//used to see if the date is valid or not
	{
		String day = comboBoxDay.getSelectedItem().toString();
		String month = comboBoxMonth.getSelectedItem().toString();
		String year = comboBoxYear.getSelectedItem().toString();
		if(month.equals("4")||month.equals("6")||month.equals("9")||month.equals("11")&&day.equals("31"))
		{
			if(day.equals("31"))
			{
						JOptionPane.showMessageDialog(null, "This month cannot have 31 days.");
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								checkDate();
								
							}
						});
						btnSave.setBounds(150, 273, 75, 23);
						contentPane.add(btnSave);
			}
			}
		else if(month.equals("2"))
		{
			int years = Integer.parseInt(year);
			int days = Integer.parseInt(day);
			if(years % 4 == 0)
			{
				if(days>=30)
				{
					JOptionPane.showMessageDialog(null, "February cannot have more than 29 days in a leap year");
					JButton btnSave = new JButton("Save");
					btnSave.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							checkDate();
							
						}
					});
					btnSave.setBounds(150, 273, 75, 23);
					contentPane.add(btnSave);
				}
				
			}
			else
			{
				if(days>=29)
				{
					JOptionPane.showMessageDialog(null, "February cannot have more than 28 days.");
					JButton btnSave = new JButton("Save");
					btnSave.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							checkDate();
							
						}
					});
					btnSave.setBounds(150, 273, 75, 23);
					contentPane.add(btnSave);
				}
				
			}
		}
		else
		{
			adding();
			int check = JOptionPane.showConfirmDialog(null, "Do You Want to add another employee?", "Adding", JOptionPane.YES_NO_OPTION);
	        if (check == JOptionPane.YES_OPTION)
	        {
	        	addEmployee addi = new addEmployee();
				addi.setVisible(true);
				dispose();
	        }
	        else {
	           
	           
	        }
		}
		
		
	}
	public void adding() // method that is used to add to the SQL database
	{
		try{
			int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to add this record?",
					"Adding Employee", JOptionPane.YES_NO_OPTION);
	        if (check == JOptionPane.YES_OPTION) {
				String dob = comboBoxYear.getSelectedItem().toString() + "-" + comboBoxMonth.getSelectedItem().toString() +
						"-" + comboBoxDay.getSelectedItem().toString();
				String query = "insert into EmployeeData (ID, Name, Surname, Username, Password, Designation, DOB, Nationality, Rights)"
						+ " values (?, ?, ?, ?, ?, ?, ? , ?, ?)";
				PreparedStatement adding = connect.prepareStatement(query);
				adding.setString(1,textFieldID.getText());
				adding.setString(2,textFieldFirstName.getText());
				adding.setString(3,textFieldSurname.getText());
				adding.setString(4,textFieldUserName.getText());
				adding.setString(5,CipherText());
				adding.setString(6,textFieldDesignation.getText());
				adding.setString(7, dob);
				adding.setString(8,textFieldNationality.getText());
				String rights = accessRights;
				adding.setString(9,accessRights);
				
				adding.execute(); // executes the prepared statement
				adding.close(); //closes the connections
				
				}
		}
				catch (Exception e)
				{
					e.printStackTrace();
				}	
		
			textFieldID.setText("");
			textFieldFirstName.setText("");
			textFieldSurname.setText("");
			textFieldUserName.setText("");
			passwordField.setText("");
			textFieldDesignation.setText("");
			comboBoxDay.setSelectedItem("DD");
			comboBoxMonth.setSelectedItem("MM");
			comboBoxYear.setSelectedItem("YYYY");
			textFieldNationality.setText("");
			chckbxYes.setSelected(false);
			

	}
	
	public String encrypt() /*This method is used to encrypt the password being entered by the user.
	 The plaintext password is converted into a
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
	
	public String CipherText()//Used to store the ciphertext in the password field
	{
		String cipher = encrypt();
		return cipher;
	}
}
