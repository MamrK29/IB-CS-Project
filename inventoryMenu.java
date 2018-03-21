/*==============================================================
 * 
 * Class:  inventoryMenu                     Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: main inventory screen, used to edit and delete inventory records
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class inventoryMenu extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JButton btnAddItem;
	private JButton btnEditQuantity;
	static Connection connect = null;
	private JTextField txtId;
	private JTextField txtRetailPrice;
	static Statement stmt = null;
	private JTextField txtDescription;
	private JTextField txtType;
	private JTextField txtDealerPrice;
	private JTextField txtCashPrice;
	private JTextField txtLargeVolumePrice;
	private JTextField txtLastUnitCost;
	private JLabel lblDesc;
	private JLabel lblLUC;
	private JLabel lblType;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblDP;
	private JLabel lblCP;
	private JLabel lblLVP;
	private JLabel lblRP;
	
	final String DB_URL = "jdbc:mysql://sql6.freemysqlhosting.net/sql6104388";
	final String DB_USER = "sql6104388";
	final String DB_PASS = "pD1NPpHLSt";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					inventoryMenu frame = new inventoryMenu();
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
	public inventoryMenu() {
		super("Inventory");
		connect = sqlConnection.databaseConnector(); // establishes the connection with the database using the .databaseConnector() method from the sqlConnection class.
		
		setBounds(100, 100, 833, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Panel panel = new Panel();
		panel.setBounds(22, 149, 149, 295);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtId = new JTextField();
		txtId.setText("ID");
		txtId.setBounds(10, 11, 86, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		txtRetailPrice = new JTextField();
		txtRetailPrice.setText("Retail Price");
		txtRetailPrice.setColumns(10);
		txtRetailPrice.setBounds(10, 133, 86, 20);
		panel.add(txtRetailPrice);
		
		txtDescription = new JTextField();
		txtDescription.setText("Description");
		txtDescription.setBounds(10, 42, 86, 20);
		panel.add(txtDescription);
		txtDescription.setColumns(10);
		
		txtType = new JTextField();
		txtType.setText("Type");
		txtType.setColumns(10);
		txtType.setBounds(10, 71, 86, 20);
		panel.add(txtType);
		
		txtDealerPrice = new JTextField();
		txtDealerPrice.setText("Dealer Price");
		txtDealerPrice.setColumns(10);
		txtDealerPrice.setBounds(10, 164, 86, 20);
		panel.add(txtDealerPrice);
		
		txtCashPrice = new JTextField();
		txtCashPrice.setText("Cash Price");
		txtCashPrice.setColumns(10);
		txtCashPrice.setBounds(10, 195, 86, 20);
		panel.add(txtCashPrice);
		
		txtLargeVolumePrice = new JTextField();
		txtLargeVolumePrice.setText("Large Volume Price");
		txtLargeVolumePrice.setColumns(10);
		txtLargeVolumePrice.setBounds(10, 226, 129, 20);
		panel.add(txtLargeVolumePrice);
		
		txtLastUnitCost = new JTextField();
		txtLastUnitCost.setText("Last Unit Cost");
		txtLastUnitCost.setColumns(10);
		txtLastUnitCost.setBounds(10, 102, 86, 20);
		panel.add(txtLastUnitCost);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//gets selected row from table
				try{
					int row = table.getSelectedRow();
					String data = (table.getModel().getValueAt(row, 0).toString());
					
					String sql = "select * from inventory where ID='"+data+"'";
					PreparedStatement pst = connect.prepareStatement(sql);		
					pst.execute();
					ResultSet rs = pst.executeQuery();
					
					if(rs.next())
					{
						String add = rs.getString("ID");
						txtId.setText(add);
						
						String add2 = rs.getString("Description");
						txtDescription.setText(add2);
						
						String add3 = rs.getString("Type");
						txtType.setText(add3);
						
						String add4 = rs.getString("LUC");
						txtLastUnitCost.setText(add4);
						
						String add5 = rs.getString("RP");
						txtRetailPrice.setText(add5);
						
						String add6 = rs.getString("DP");
						txtDealerPrice.setText(add6);
						
						String add7 = rs.getString("CP");
						txtCashPrice.setText(add7);
						
						String add8 = rs.getString("LVP");
						txtLargeVolumePrice.setText(add8);
						
						
					}
				}
				catch(SQLException e1)
				{
					
				}
		
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//gets selected row from table
				try{
					int row = table.getSelectedRow();
					String data = (table.getModel().getValueAt(row, 0).toString());
					
					String sql = "select * from inventory where ID='"+data+"'";
					PreparedStatement pst = connect.prepareStatement(sql);		
					pst.execute();
					ResultSet rs = pst.executeQuery();
					
					if(rs.next())
					{
						String add = rs.getString("ID");
						txtId.setText(add);
						
						String add2 = rs.getString("Description");
						txtDescription.setText(add2);
						
						String add3 = rs.getString("Type");
						txtType.setText(add3);
						
						String add4 = rs.getString("LUC");
						txtLastUnitCost.setText(add4);
						
						String add5 = rs.getString("RP");
						txtRetailPrice.setText(add5);
						
						String add6 = rs.getString("DP");
						txtDealerPrice.setText(add6);
						
						String add7 = rs.getString("CP");
						txtCashPrice.setText(add7);
						
						String add8 = rs.getString("LVP");
						txtLargeVolumePrice.setText(add8);
						
						
					}
				}
				catch(SQLException e1)
				{
					
				}
			}
		});
		scrollPane.setBounds(200, 29, 607, 415);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
	
		
		btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addInventory invent;
				try {
					invent = new addInventory();//create an object of addinventory
					invent.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAddItem.setBounds(22, 30, 124, 23);
		contentPane.add(btnAddItem);
		
		btnEditQuantity = new JButton("Update");
		btnEditQuantity.addActionListener(new ActionListener() {//editing the records
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String itemID = txtId.getText();
					String descrip = txtDescription.getText();
					String type = txtType.getText();
					String lastUnit = txtLastUnitCost.getText();
					String retail = txtRetailPrice.getText();
					String cash = txtCashPrice.getText();
					String volume = txtLargeVolumePrice.getText();
					String deal = txtDealerPrice.getText();
					int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to update this record?",
							"Updating Inventory", JOptionPane.YES_NO_OPTION);
			        if (check == JOptionPane.YES_OPTION) {
					String sql = "update inventory set Description='"+descrip+"', Type= '"+type+"', LUC = '"+lastUnit+"'"
							+ ", RP='"+retail+"', DP= '"+deal+"', CP= '"+cash+"', LVP = '"+volume+"'WHERE ID=?";
					PreparedStatement pst = connect.prepareStatement(sql);	
					pst.setString(1, itemID);
					pst.execute();
					openInventoryTable();
				} 
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditQuantity.setBounds(22, 64, 124, 23);
		contentPane.add(btnEditQuantity);
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {//deleting the records
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete this record?",
							"Deleting Inventory", JOptionPane.YES_NO_OPTION);
			        if (check == JOptionPane.YES_OPTION) {
			        	String sql = "DELETE FROM inventory WHERE ID=?";
						PreparedStatement pst = connect.prepareStatement(sql);
						pst.setString(1, txtId.getText());
						pst.executeUpdate();
	                  	openInventoryTable();
			        }
			        	
				}
				catch(SQLException e1){
					e1.printStackTrace();
				}
				
			
		    
				
			}
		});
		btnDelete.setBounds(22, 98, 124, 23);
		contentPane.add(btnDelete);
		
		JLabel lblID = new JLabel("");
		lblID.addMouseListener(new MouseAdapter() {//sorting
			int count = 0;
			@Override
			public void mousePressed(MouseEvent arg0) 
			{ 
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM inventory ORDER BY ID DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM inventory ORDER BY ID ASC";
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
		lblID.setBounds(200, 11, 74, 14);
		contentPane.add(lblID);
		
		lblDesc = new JLabel("");
		lblDesc.addMouseListener(new MouseAdapter() {//sorting
			int count = 0;
			@Override
			public void mousePressed(MouseEvent arg0) 
			{ 
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM inventory ORDER BY Description DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM inventory ORDER BY Description  ASC";
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
		lblDesc.setBounds(283, 11, 46, 14);
		contentPane.add(lblDesc);
		
		lblType = new JLabel("");
		lblType.addMouseListener(new MouseAdapter() {
			int count = 0;//sorting
			@Override
			public void mousePressed(MouseEvent arg0) 
			{ 
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM inventory ORDER BY Type DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM inventory ORDER BY Type ASC";
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
		lblType.setBounds(361, 11, 46, 14);
		contentPane.add(lblType);
		
		lblLUC = new JLabel("");
		lblLUC.addMouseListener(new MouseAdapter() {
			int count = 0;//sorting
			@Override
			public void mousePressed(MouseEvent arg0) 
			{ 
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM inventory ORDER BY LUC DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM inventory ORDER BY LUC ASC";
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
		lblLUC.setBounds(436, 11, 46, 14);
		contentPane.add(lblLUC);
		
		lblRP = new JLabel("");
		lblRP.addMouseListener(new MouseAdapter() {
			int count = 0;
			@Override
			public void mousePressed(MouseEvent arg0) //sorting
			{ 
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM inventory ORDER BY RP DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM inventory ORDER BY RP ASC";
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
		lblRP.setBounds(511, 11, 46, 14);
		contentPane.add(lblRP);
		
		lblDP = new JLabel("");
		lblDP.addMouseListener(new MouseAdapter() {
			int count = 0;
			@Override
			public void mousePressed(MouseEvent arg0) //sorting
			{ 
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM inventory ORDER BY DP DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM inventory ORDER BY DP ASC";
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
		lblDP.setBounds(587, 11, 46, 14);
		contentPane.add(lblDP);
		
		lblCP = new JLabel("");
		lblCP.addMouseListener(new MouseAdapter() {
			int count = 0;
			@Override
			public void mousePressed(MouseEvent arg0) 
			{ //sorting
				try {
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM inventory ORDER BY CP DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM inventory ORDER BY CP ASC";
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
		lblCP.setBounds(665, 11, 46, 14);
		contentPane.add(lblCP);
		
		lblLVP = new JLabel("");
		lblLVP.addMouseListener(new MouseAdapter() {
			int count = 0;
			@Override
			public void mousePressed(MouseEvent arg0) 
			{ 
				try {//sorting
						count++;
					if(count == 1)
					{
						String sql = "SELECT * FROM inventory ORDER BY LVP DESC";
						Statement stmt = connect.createStatement();
					    ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
					}
					if(count == 2)
					{
						String sql = "SELECT * FROM inventory ORDER BY LVP ASC";
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
		lblLVP.setBounds(742, 11, 46, 14);
		contentPane.add(lblLVP);
		
		openInventoryTable();
		createMenu();
	}
	
	public void createMenu()//creates menubar
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
	
	public static void openInventoryTable()
	{
		try
		{
		Statement stmt = connect.createStatement();
	    ResultSet rs = stmt.executeQuery( "SELECT * FROM inventory" );
		table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}
}
