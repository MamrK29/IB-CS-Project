/*==============================================================
 * 
 * Class:  purchaseOrder                     Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: Used to manipulate purchase order records
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
import java.awt.print.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class purchaseOrder extends JFrame {

	private JPanel contentPane;
	static Connection connect = null; //global variable
	private static JTable table;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnNewButton;
	private JTextField txtPurchaseOrderNumber;
	private JTextField txtAmount;
	private JTextField txtStatus;
	private JComboBox comboBox;
	private JComboBox comboBox2;
	
	String[] options = {"MSC", "Al Ghamdi", "California Suppliers"};//values for comboBox
	String[] Status = {"Open", "Closed"};//values for comboBox2
	private JLabel lblVendor;
	private JLabel lblNum;
	private JLabel labelAmt;
	private JLabel labelStatus;
	private JButton btnPrint;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchaseOrder frame = new purchaseOrder();
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
	public purchaseOrder() {
		super("Purchase Order");
		connect = sqlConnection.databaseConnector();
		setBounds(100, 100, 833, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {//Getting the data from the table when a row is clicked
				try{
					int row = table.getSelectedRow();
					String data = (table.getModel().getValueAt(row, 1).toString());
					
					String sql = "select * from purchase_Order where PO_Number='"+data+"'";
					PreparedStatement pst = connect.prepareStatement(sql);		
					pst.execute();
					ResultSet rs = pst.executeQuery();
					
					if(rs.next())
					{
						String add = rs.getString("Vendor");
						comboBox.setSelectedItem(add);
						
						String add2 = rs.getString("PO_Number");
						txtPurchaseOrderNumber.setText(add2);
						
						String add3 = rs.getString("Amount");
						txtAmount.setText(add3);
						
						String add4 = rs.getString("LUC");
						comboBox2.setSelectedItem(add4);			
					}
				}
				catch(SQLException e1)
				{
					
				}
			}
		});
		scrollPane.setBounds(200, 34, 607, 410);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {//Getting the data from the table when a row is clicked
				try{
					int row = table.getSelectedRow();
					String data = (table.getModel().getValueAt(row, 1).toString());
					
					String sql = "select * from purchase_Order where PO_Number='"+data+"'";
					PreparedStatement pst = connect.prepareStatement(sql);		
					pst.execute();
					ResultSet rs = pst.executeQuery();
					
					if(rs.next())
					{
						String add = rs.getString("Vendor");
						comboBox.setSelectedItem(add);
						
						String add2 = rs.getString("PO_Number");
						txtPurchaseOrderNumber.setText(add2);
						
						String add3 = rs.getString("Amount");
						txtAmount.setText(add3);
						
						String add4 = rs.getString("LUC");
						comboBox2.setSelectedItem(add4);			
					}
				}
				catch(SQLException e1)
				{
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();//calls the save method
			}
		});
		btnAdd.setBounds(30, 25, 89, 23);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();//calls the update method
			}
		});
		
		btnUpdate.setBounds(30, 60, 89, 23);
		contentPane.add(btnUpdate);
		
		btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//deletes the record
				try {
					int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete this record?",
							"Deleting Purchase Order", JOptionPane.YES_NO_OPTION);//message to confirm action
			        if (check == JOptionPane.YES_OPTION) {
					String sql = "DELETE FROM purchase_Order WHERE PO_Number=?";
					PreparedStatement pst = connect.prepareStatement(sql);
					pst.setString(1, txtPurchaseOrderNumber.getText());
					pst.executeUpdate();
                  	openPOTable();
			        } 
				}
			        catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(30, 94, 89, 23);
		contentPane.add(btnNewButton);
		
		comboBox = new JComboBox(options);
		comboBox.setToolTipText("Please enter the Vendor Name");
		comboBox.setSelectedItem("");
		comboBox.setBounds(10, 202, 139, 23);
		contentPane.add(comboBox);
		
		txtPurchaseOrderNumber = new JTextField();
		txtPurchaseOrderNumber.setToolTipText("Please enter the Purchase Order Number");
		txtPurchaseOrderNumber.setText("Purchase Order Number");
		txtPurchaseOrderNumber.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				txtPurchaseOrderNumber.setText("");
			}
		});
		txtPurchaseOrderNumber.setBounds(10, 236, 139, 23);
		contentPane.add(txtPurchaseOrderNumber);
		txtPurchaseOrderNumber.setColumns(10);
		
		txtAmount = new JTextField();
		txtAmount.setToolTipText("Please Enter the Amount");
		txtAmount.setText("Amount");
		txtAmount.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				txtAmount.setText("");
			}
		});
		txtAmount.setBounds(10, 270, 139, 23);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		comboBox2 = new JComboBox(Status);
		comboBox2.setToolTipText("Please select the status of the Purchase Order");
		comboBox2.setEditable(true);
		comboBox2.setBounds(10, 304, 139, 23);
		contentPane.add(comboBox2);
		
		lblVendor = new JLabel("");
		lblVendor.addMouseListener(new MouseAdapter() {
			int count = 0;
			public void mousePressed(MouseEvent arg0) {
				try {
					count++;
				if(count == 1)
				{
					String sql = "SELECT * FROM purchase_Order ORDER BY Vendor DESC";//sorts vendors into descending order
					Statement stmt = connect.createStatement();
				    ResultSet rs = stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
				}
				if(count == 2)
				{
					String sql = "SELECT * FROM purchase_Order ORDER BY Vendor ASC";//sorts vendors into ascending order
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
		lblVendor.setBounds(200, 11, 149, 23);
		contentPane.add(lblVendor);
		
		lblNum = new JLabel("");
		lblNum.addMouseListener(new MouseAdapter() {
			int count = 0;
			public void mousePressed(MouseEvent arg0) {
				try {
					count++;
				if(count == 1)
				{
					String sql = "SELECT * FROM purchase_Order ORDER BY PO_Number DESC";//sorts order number into descending order
					Statement stmt = connect.createStatement();
				    ResultSet rs = stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
				}
				if(count == 2)
				{
					String sql = "SELECT * FROM purchase_Order ORDER BY PO_Number ASC";//sorts order number into ascending order
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
		lblNum.setBounds(352, 11, 149, 23);
		contentPane.add(lblNum);
		
		labelAmt = new JLabel("");
		labelAmt.addMouseListener(new MouseAdapter() {
			int count = 0;
			public void mousePressed(MouseEvent arg0) {
				try {
					count++;
				if(count == 1)
				{
					String sql = "SELECT * FROM purchase_Order ORDER BY Amount DESC";// sorts data by descending order of amount
					Statement stmt = connect.createStatement();
				    ResultSet rs = stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
				}
				if(count == 2)
				{
					String sql = "SELECT * FROM purchase_Order ORDER BY Amount ASC";//data is sorted into ascending order by amount
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
		labelAmt.setBounds(499, 11, 154, 23);
		contentPane.add(labelAmt);
		
		labelStatus = new JLabel("");
		labelStatus.addMouseListener(new MouseAdapter() {
			int count = 0;
			public void mousePressed(MouseEvent arg0) {
				try {
					count++;
				if(count == 1)
				{
					String sql = "SELECT * FROM purchase_Order ORDER BY Status DESC";//descending order by status
					Statement stmt = connect.createStatement();
				    ResultSet rs = stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
				}
				if(count == 2)
				{
					String sql = "SELECT * FROM purchase_Order ORDER BY Status ASC";//ascending order by status
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
		labelStatus.setBounds(655, 11, 152, 23);
		contentPane.add(labelStatus);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//print function
				MessageFormat header = new MessageFormat("Purchase Orders");//creates the header
				MessageFormat footer = new MessageFormat("Page{0, number, integer}");//create the footer
				try{
					table.print(JTable.PrintMode.NORMAL, header, footer);/*calls the print() method from the
					print library*/
				}
				catch(java.awt.print.PrinterException e1)
				{
					System.err.format("Cannot Print", e1.getMessage());
				}
			}
		});
		btnPrint.setBounds(30, 124, 89, 23);
		contentPane.add(btnPrint);
		
		openPOTable();
		createMenu();
		
	}
	
	public void createMenu()//creates the menu
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
		String query = "insert into purchase_Order (Vendor, PO_Number, Amount, Status) values (?, ?, ?, ?)";
		PreparedStatement pst = connect.prepareStatement(query);
		String value = comboBox.getSelectedItem().toString(); // the value from the comboBox is converted to a string using the .toString() and is stored in a String variable.
		pst.setString(1, value);
		pst.setString(2, txtPurchaseOrderNumber.getText());
		pst.setString(3, txtAmount.getText());
		String stat = comboBox2.getSelectedItem().toString();
		pst.setString(4, stat);
		pst.execute(); // executes the prepared statement
		pst.close(); //closes the connections
		openPOTable();
		resetData();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void update() //This method is used to save the new client information
	{
		try
		{int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to update this record?",
				"Update Purchase Order", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
			String value = comboBox.getSelectedItem().toString(); // the value from the comboBox is converted to a string using the .toString() and is stored in a String variable.
			String number = txtPurchaseOrderNumber.getText();
			String amount = txtAmount.getText();
			String value2 = comboBox2.getSelectedItem().toString();
			String query = "update purchase_Order set Vendor='"+value+"', PO_Number ='"+number+"', Amount ='"+amount+"', Status ='"+value2+"'"
					+ " WHERE PO_Number=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, txtPurchaseOrderNumber.getText());
			pst.execute(); // executes the prepared statement
			pst.close(); //closes the connections
			openPOTable();
			resetData();
		}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void resetData(){
		txtPurchaseOrderNumber.setText("Purchase Order Number");
		txtAmount.setText("Amount");
		comboBox.setSelectedItem("");
		comboBox2.setSelectedItem(Status[0]);
	}//This method sets all the gui input elements to blank so that the user can enter new data

	public static void openPOTable()
	{
		try
		{
		Statement stmt = connect.createStatement();
	    ResultSet rs = stmt.executeQuery( "SELECT * FROM purchase_Order" );
		table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}
	
}

