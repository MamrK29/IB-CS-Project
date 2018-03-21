/*==============================================================
 * 
 * Class:  addInventory                     Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: Used to add inventory to the program
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class addInventory extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterItemID;
	private JTextField txtEnterItemDescription;
	private JTextField textFieldItemType;
	private JTextField textFieldLUC;
	private JTextField textFieldRetailPrice;
	private JTextField txtEnterDealerPrice;
	private JTextField txtEnterCashPrice;
	private JTextField txtEnterLargeVolume;
	static Connection connect = null;
	static Statement stmt = null;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					addInventory frame = new addInventory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public addInventory() throws SQLException {
		super("Adding Inventory");
		
		setBounds(100, 100, 579, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEnterItemID = new JTextField();
		txtEnterItemID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				txtEnterItemID.setText("");
				txtEnterItemID.setForeground(Color.BLACK);
			}
		});
		txtEnterItemID.setForeground(Color.LIGHT_GRAY);
		txtEnterItemID.setText("Enter the Item ID");
		txtEnterItemID.setBounds(178, 32, 148, 25);
		contentPane.add(txtEnterItemID);
		txtEnterItemID.setColumns(10);
		
		txtEnterItemDescription = new JTextField();
		txtEnterItemDescription.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				txtEnterItemDescription.setText("");
				txtEnterItemDescription.setForeground(Color.BLACK);
			}
		});
		txtEnterItemDescription.setForeground(Color.LIGHT_GRAY);
		txtEnterItemDescription.setText("Enter Item Description");
		txtEnterItemDescription.setBounds(178, 65, 148, 25);
		contentPane.add(txtEnterItemDescription);
		txtEnterItemDescription.setColumns(10);
		
		textFieldItemType = new JTextField();
		textFieldItemType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				textFieldItemType.setText("");
				textFieldItemType.setForeground(Color.BLACK);
			}
		});
		textFieldItemType.setForeground(Color.LIGHT_GRAY);
		textFieldItemType.setText("Enter Item Type");
		textFieldItemType.setBounds(178, 101, 148, 25);
		contentPane.add(textFieldItemType);
		textFieldItemType.setColumns(10);
		
		textFieldLUC = new JTextField();
		textFieldLUC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				textFieldLUC.setText("");
				textFieldLUC.setForeground(Color.BLACK);
			}
		});
		textFieldLUC.setForeground(Color.LIGHT_GRAY);
		textFieldLUC.setText("Enter Last Unit Cost");
		textFieldLUC.setBounds(178, 132, 148, 25);
		contentPane.add(textFieldLUC);
		textFieldLUC.setColumns(10);
		
		textFieldRetailPrice = new JTextField();
		textFieldRetailPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				textFieldRetailPrice.setText("");
				textFieldRetailPrice.setForeground(Color.BLACK);
			}
		});
		textFieldRetailPrice.setForeground(Color.LIGHT_GRAY);
		textFieldRetailPrice.setText("Enter Retail Price");
		textFieldRetailPrice.setBounds(178, 163, 148, 25);
		contentPane.add(textFieldRetailPrice);
		textFieldRetailPrice.setColumns(10);
		
		txtEnterDealerPrice = new JTextField();
		txtEnterDealerPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				txtEnterDealerPrice.setText("");
				txtEnterDealerPrice.setForeground(Color.BLACK);
			}
		});
		txtEnterDealerPrice.setForeground(Color.LIGHT_GRAY);
		txtEnterDealerPrice.setText("Enter Dealer Price");
		txtEnterDealerPrice.setBounds(178, 194, 148, 25);
		contentPane.add(txtEnterDealerPrice);
		txtEnterDealerPrice.setColumns(10);
		
		txtEnterCashPrice = new JTextField();
		txtEnterCashPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				txtEnterCashPrice.setText("");
				txtEnterCashPrice.setForeground(Color.BLACK);
			}
		});
		txtEnterCashPrice.setForeground(Color.LIGHT_GRAY);
		txtEnterCashPrice.setText("Enter Cash Price");
		txtEnterCashPrice.setHorizontalAlignment(SwingConstants.LEFT);
		txtEnterCashPrice.setBounds(178, 225, 148, 25);
		contentPane.add(txtEnterCashPrice);
		txtEnterCashPrice.setColumns(10);
		
		txtEnterLargeVolume = new JTextField();
		txtEnterLargeVolume.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				txtEnterLargeVolume.setText("");
				txtEnterLargeVolume.setForeground(Color.BLACK);
			}
		});
		txtEnterLargeVolume.setForeground(Color.LIGHT_GRAY);
		txtEnterLargeVolume.setText("Enter Large Volume Price");
		txtEnterLargeVolume.setBounds(178, 256, 148, 25);
		contentPane.add(txtEnterLargeVolume);
		txtEnterLargeVolume.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					add(); //calls the save method
					inventoryMenu.openInventoryTable();
					int check = JOptionPane.showConfirmDialog(null, "Do You Want to add another Inventory Item?",
							"Adding Inventory", JOptionPane.YES_NO_OPTION);
			        if (check == JOptionPane.YES_OPTION) {
			        	resetData();//Sets all data inputs fields to empty so that the user can add another client.
			        }
			        else {
			           
			           
			        }
				}
				catch(NullPointerException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(205, 317, 89, 23);
		contentPane.add(btnSave);
		
		connect = sqlConnection.databaseConnector();
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
	
	public void add() //The add method
	{
		try{
			int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to add this record?",
					"Add Inventory", JOptionPane.YES_NO_OPTION);
	        if (check == JOptionPane.YES_OPTION) {
				String query = "insert into inventory (ID, DESCRIPTION, TYPE, LUC, RP, DP, CP, LVP) values (?, ?, ?, ?, ?, ?, ? , ?)";
				PreparedStatement adding = connect.prepareStatement(query);
				adding.setString(1,txtEnterItemID.getText());
				adding.setString(2,txtEnterItemDescription.getText());
				adding.setString(3,textFieldItemType.getText());
				adding.setString(4,textFieldLUC.getText());
				adding.setString(5,textFieldRetailPrice.getText());
				adding.setString(6,txtEnterDealerPrice.getText());
				adding.setString(7,txtEnterCashPrice.getText());
				adding.setString(8,txtEnterLargeVolume.getText());
				
				adding.execute(); // executes the prepared statement
				adding.close(); //closes the connections
				
				
				}
		}
				catch (Exception e)
				{
					e.printStackTrace();
				}	
	}
	
	public void resetData() //Sets all GUI components to empty so new data can be inserted
	{
			txtEnterItemID.setText("ID");
			txtEnterItemDescription.setText("Description");
			textFieldItemType.setText("Type");
			textFieldLUC.setText("LUC");
			textFieldRetailPrice.setText("Retail Price");
			txtEnterDealerPrice.setText("Dealer Price");
			txtEnterCashPrice.setText("Cash Price");
			txtEnterLargeVolume.setText("Large Volume Price");

	}
}
