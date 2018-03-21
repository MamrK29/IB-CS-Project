/*==============================================================
 * 
 * Class:  clientMenu                     Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: main client screen, used to edit and delete and add client records
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
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;


public class clientMenu extends JFrame {
	
	static Connection connect = null; // connect is a global variable
	private JPanel contentPane;
	private static JTable table;
	private JTextField txtClientId;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox comboBoxType;
	private JComboBox comboBoxRebate;
	String[] rebate = {"No", "Yes"};
	String[] options = {"AYUB1", "AYUB2", "Hafiz"};
	private JLabel label;
	private JLabel label_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientMenu frame = new clientMenu();
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
	public clientMenu() {
		setBounds(100, 100, 766, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {// gets selected row from table
				try{
					int row = table.getSelectedRow();
					String data = (table.getModel().getValueAt(row, 0).toString());
					
					String sql = "select * from Client where CID='"+data+"'";
					PreparedStatement pst = connect.prepareStatement(sql);		
					pst.execute();
					ResultSet rs = pst.executeQuery();
					
					if(rs.next())
					{
						String add = rs.getString("CID");
						txtClientId.setText(add);
						
						String add2 = rs.getString("Name");
						textField.setText(add2);

						String add3 = rs.getString("Contact");
						textField_1.setText(add3);
						
						String add4 = rs.getString("Telephone");
						textField_2.setText(add4);
						
						String add5 = rs.getString("Type");
						comboBoxType.setSelectedItem(add5);
						
						String add6 = rs.getString("Balance");
						textField_3.setText(add6);
						
						String add7 = rs.getString("Rebate");
						comboBoxRebate.setSelectedItem(add7);		
					}
				}
				catch(SQLException e1)
				{
					
				}
			}
		});
		scrollPane.setBounds(10, 45, 518, 440);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {//gets selected row from table
				try{
					int row = table.getSelectedRow();
					String data = (table.getModel().getValueAt(row, 0).toString());
					
					String sql = "select * from Client where CID='"+data+"'";
					PreparedStatement pst = connect.prepareStatement(sql);		
					pst.execute();
					ResultSet rs = pst.executeQuery();
					
					if(rs.next())
					{
						String add = rs.getString("CID");
						txtClientId.setText(add);
						
						String add2 = rs.getString("Name");
						textField.setText(add2);

						String add3 = rs.getString("Contact");
						textField_1.setText(add3);
						
						String add4 = rs.getString("Telephone");
						textField_2.setText(add4);
						
						String add5 = rs.getString("Type");
						comboBoxType.setSelectedItem(add5);
						
						String add6 = rs.getString("Balance");
						textField_3.setText(add6);
						
						String add7 = rs.getString("Rebate");
						comboBoxRebate.setSelectedItem(add7);		
					}
				}
				catch(SQLException e1)
				{
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("Add Client");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClient newClient = new addClient();//creates new object of newClient 
				newClient.setVisible(true);
				dispose();
			}
		});
		btnAdd.setBounds(602, 48, 112, 23);
		contentPane.add(btnAdd);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {//print method
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header = new MessageFormat("Clients");//creates document header
				MessageFormat footer = new MessageFormat("Page{0, number, integer}");//creates document footer
				try{
					table.print(JTable.PrintMode.NORMAL, header, footer);//uses print() method from print library
				}
				catch(java.awt.print.PrinterException e1)
				{
					System.err.format("Cannot Print", e1.getMessage());
				}
			}
				
			});
		btnPrint.setBounds(602, 150, 89, 23);
		contentPane.add(btnPrint);
		
		JButton button = new JButton("Edit Client Data");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();//calls update method
			}
		});
		button.setBounds(602, 82, 112, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Delete Client");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//deletes an record
				try {
							
					String sql = "DELETE FROM Client WHERE CID=?";
					PreparedStatement pst = connect.prepareStatement(sql);
					pst.setString(1, textField.getText());
					pst.executeUpdate();
					openClientTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(602, 116, 112, 23);
		contentPane.add(button_1);
		
		txtClientId = new JTextField();
		txtClientId.setText("Client ID");
		txtClientId.setBounds(563, 210, 128, 23);
		contentPane.add(txtClientId);
		txtClientId.setColumns(10);
		
		textField = new JTextField();
		textField.setText("Name");
		textField.setColumns(10);
		textField.setBounds(563, 244, 128, 23);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("Contact");
		textField_1.setColumns(10);
		textField_1.setBounds(563, 278, 128, 23);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("Telephone");
		textField_2.setColumns(10);
		textField_2.setBounds(563, 312, 128, 23);
		contentPane.add(textField_2);
		
		comboBoxType = new JComboBox(options);
		comboBoxType.setEditable(true);
		comboBoxType.setSelectedItem("");
		comboBoxType.setBounds(563, 346, 128, 23);
		contentPane.add(comboBoxType);
		
		textField_3 = new JTextField();
		textField_3.setText("Balance");
		textField_3.setColumns(10);
		textField_3.setBounds(563, 380, 128, 23);
		contentPane.add(textField_3);
		
		comboBoxRebate = new JComboBox(rebate);
		comboBoxRebate.setEditable(true);
		comboBoxRebate.setSelectedItem("");
		comboBoxRebate.setBounds(563, 414, 128, 23);
		contentPane.add(comboBoxRebate);
		
		label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			int count = 0;//sorting
			public void mousePressed(MouseEvent arg0) 
			{ 
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM Client ORDER BY CID DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM Client ORDER BY CID ASC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
						count = 0;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		label.setBounds(10, 25, 71, 23);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			int count = 0;
			public void mousePressed(MouseEvent arg0) 
			{ //sorting
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM Client ORDER BY Balance DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM Client ORDER BY Balance ASC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
						count = 0;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		label_1.setBounds(378, 25, 71, 23);
		contentPane.add(label_1);
		
		
		connect = sqlConnection.databaseConnector();
		openClientTable();
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

	public void update() //This method is used to save the new client information
	{
		try
		{
			String value = txtClientId.getText();
			String type = comboBoxType.getSelectedItem().toString(); // the value from the comboBox is converted to a string using the .toString() and is stored in a String variable.
			String name = textField.getText();
			String contact = textField_1.getText();
			String telephone = textField_2.getText();
			String balance = textField_3.getText();
			String discount = comboBoxRebate.getSelectedItem().toString();
			String query = "update Client set Name='"+name+"', Contact ='"+contact+"', Telephone ='"+telephone+"', Type ='"+type+"', Balance = '"
					+balance+"', Rebate = '" +discount+"'" + " WHERE CID=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, txtClientId.getText());
			pst.execute(); // executes the prepared statement
			pst.close(); //closes the connections
			openClientTable();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
	public static void openClientTable() //Automatically opens the client table, used for refreshing as well
	{
		try
		{
		String clientQuery = "select * from Client";
		PreparedStatement clientstatement = connect.prepareStatement(clientQuery);
		ResultSet Clientresult= clientstatement.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(Clientresult));// class from r2sxml.jar
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}
}
