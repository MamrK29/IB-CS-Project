/*==============================================================
 * 
 * Class: addclient                            Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: Used to create the add clients to the program. 
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

import javax.swing.JFrame;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;


public class addClient extends JFrame {
	private JTextField textFieldTelephone;
	private JTextField textFieldContact;
	private JTextField textFieldName;
	private JTextField textFieldCID;
	Connection connect = null;
	private JTextField textFieldBal;
	private String tick = "No"; // used to checkbox answer to the SQL Database
	private JComboBox comboBox;
	private JCheckBox chckbxYes;
	
	String[] options = {"AYUB1", "AYUB2", "Hafiz"};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					addClient frame = new addClient(); //Creates the new frame
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
	public addClient() {// Creating the GUI Components//
		setBounds(100, 100, 495, 335);
		getContentPane().setLayout(null);
		
		JLabel lblCustomerIdcid = new JLabel("Customer ID (CID)");
		lblCustomerIdcid.setBounds(55, 21, 102, 14);
		getContentPane().add(lblCustomerIdcid);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(55, 46, 88, 14);
		getContentPane().add(lblCustomerName);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(55, 71, 88, 14);
		getContentPane().add(lblContact);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(55, 96, 88, 14);
		getContentPane().add(lblTelephone);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(55, 121, 88, 14);
		getContentPane().add(lblType);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(55, 146, 88, 14);
		getContentPane().add(lblBalance);
		
		JLabel lblRebate = new JLabel("Rebate");
		lblRebate.setBounds(55, 171, 109, 14);
		getContentPane().add(lblRebate);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setBounds(194, 93, 173, 20);
		getContentPane().add(textFieldTelephone);
		textFieldTelephone.setColumns(10);
		
		textFieldContact = new JTextField();
		textFieldContact.setBounds(194, 68, 173, 20);
		getContentPane().add(textFieldContact);
		textFieldContact.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(194, 43, 173, 20);
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldCID = new JTextField();
		textFieldCID.setBounds(194, 18, 173, 20);
		getContentPane().add(textFieldCID);
		textFieldCID.setColumns(10);
		

		textFieldBal = new JTextField();
		textFieldBal.setBounds(194, 143, 173, 20);
		getContentPane().add(textFieldBal);
		textFieldBal.setColumns(10);
		
		comboBox = new JComboBox(options);
		comboBox.setEditable(true);
		comboBox.setSelectedItem("");
		comboBox.setBounds(194, 118, 173, 20);
		getContentPane().add(comboBox);
		
		chckbxYes = new JCheckBox("Yes");
		chckbxYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tick = "Yes";
			}
		});
		chckbxYes.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxYes.setBounds(194, 167, 66, 23);
		getContentPane().add(chckbxYes);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				save(); //calls the save method
				clientMenu.openClientTable();
				int check = JOptionPane.showConfirmDialog(null, "Do You Want to add another client?", "Adding", JOptionPane.YES_NO_OPTION);
		        if (check == JOptionPane.YES_OPTION) {
		        	resetData();//Sets all data inputs fields to empty so that the user can add another client.
		        }
		        
			}
		});
		
		btnSave.setBounds(216, 247, 89, 23);
		getContentPane().add(btnSave);
		
		
		

		createMenu();
		connect = sqlConnection.databaseConnector(); // establishes the connection with the database using the .databaseConnector() method from the sqlConnection class.
	}
	
	public void createMenu()//creates menu bar
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
	
	public void save() //This method is used to save the new client information
	{
		try
		{
			int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to save this record?",
					"Adding Client", JOptionPane.YES_NO_OPTION);
	        if (check == JOptionPane.YES_OPTION) {
		String query = "insert into Client (CID, Name, Contact, Telephone, Type, Balance, Rebate) values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = connect.prepareStatement(query);
		pst.setString(1, textFieldCID.getText());
		pst.setString(2, textFieldName.getText());
		pst.setString(3, textFieldContact.getText());
		pst.setString(4, textFieldTelephone.getText());
		String value = comboBox.getSelectedItem().toString(); // the value from the comboBox is converted to a string using the .toString() and is stored in a String variable.
		pst.setString(5, value); //
		pst.setString(6, textFieldBal.getText());
		pst.setString(7, tick);
		pst.execute(); // executes the prepared statement
		pst.close(); //closes the connections
		
		}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void resetData(){
		textFieldCID.setText("");
		textFieldName.setText("");
		textFieldContact.setText("");
		textFieldTelephone.setText("");
		textFieldBal.setText("");
		comboBox.setSelectedItem("");
		chckbxYes.setSelected(false);
	}//This method sets all the gui input elements to blank so that the user can enter new data
}
