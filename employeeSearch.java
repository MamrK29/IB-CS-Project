/*==============================================================
 * 
 * Class:  employeeSearch                    Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: used to search for employee data
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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class employeeSearch extends JFrame {

	Connection connect = null;
	private JPanel contentPane;
	private JTextField txtEnterSearchCondition;
	private JTable table;
	private JTextField txtEnterEmployeeId;
	private JTextField txtEnterFirstName;
	private JTextField txtEnterSurname;
	private JTextField txtDesignation;
	private JTextField txtAcessRights;
	private JTextField txtNationality;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeSearch frame = new employeeSearch();
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
	public employeeSearch() {
		connect = sqlConnection.databaseConnector();
		setBounds(100, 100, 685, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEnterSearchCondition = new JTextField();
		txtEnterSearchCondition.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				search();
			}
		});
		txtEnterSearchCondition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEnterSearchCondition.setBounds(177, 11, 177, 23);
		contentPane.add(txtEnterSearchCondition);
		txtEnterSearchCondition.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{//gets data from table
					int row = table.getSelectedRow();
					String click = (table.getModel().getValueAt(row, 0).toString());
					
					String sql = "select * from EmployeeData where ID='" +click+"'";
					PreparedStatement pst = connect.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					if(rs.next()){
						String eID =rs.getString("ID");
						txtEnterEmployeeId.setText(eID);
						
						String fName =rs.getString("Name");
						txtEnterFirstName.setText(fName);
						
						String sName =rs.getString("Surname");
						txtEnterSurname.setText(sName);
						
						String jTitle =rs.getString("Designation");
						txtDesignation.setText(jTitle);
						
						String accessRights =rs.getString("Rights");
						txtAcessRights.setText(accessRights);
						
						String nationality =rs.getString("Nationality");
						txtNationality.setText(nationality);

					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		scrollPane.setBounds(30, 45, 606, 255);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{//gets record from table
					int row = table.getSelectedRow();
					String click = (table.getModel().getValueAt(row, 0).toString());
					
					String sql = "select * from EmployeeData where ID='" +click+"'";
					PreparedStatement pst = connect.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					if(rs.next()){
						String eID =rs.getString("ID");
						txtEnterEmployeeId.setText(eID);
						
						String fName =rs.getString("Name");
						txtEnterFirstName.setText(fName);
						
						String sName =rs.getString("Surname");
						txtEnterSurname.setText(sName);
						
						String jTitle =rs.getString("Designation");
						txtDesignation.setText(jTitle);
						
						String accessRights =rs.getString("Rights");
						txtAcessRights.setText(accessRights);
						
						String nationality =rs.getString("Nationality");
						txtNationality.setText(nationality);

					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//updates/edits record
				try
				{int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to update this record?",
						"Update Employee Data", JOptionPane.YES_NO_OPTION);//confirmation prompt
		        if (check == JOptionPane.YES_OPTION) {
					String id = txtEnterEmployeeId.getText();
					String name = txtEnterFirstName.getText();
					String surName = txtEnterSurname.getSelectedText();
					String job = txtDesignation.getSelectedText();
					String right = txtAcessRights.getSelectedText();
					String ethnic = txtNationality.getSelectedText();
					String query = "update EmployeeData set ID='"+ethnic+"', Name ='"+name+"',"
							+ " Surname ='"+surName+"', Designation ='"+job+"', Rights ='"+right+"', Nationality = '"+ethnic+"'" 
							+ " WHERE ID=?";
					PreparedStatement pst = connect.prepareStatement(query);
					pst.setString(1, txtEnterEmployeeId.getText());
					pst.execute(); // executes the prepared statement
					pst.close(); //closes the connections
					
				}
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(547, 335, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//deletes record
				try {
					int check = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete this record?",
							"Deleting Employee Data", JOptionPane.YES_NO_OPTION);//confirmation record
			        if (check == JOptionPane.YES_OPTION) {
					String sql = "DELETE FROM EmployeeData WHERE ID=?";
					PreparedStatement pst = connect.prepareStatement(sql);
					pst.setString(1, txtEnterEmployeeId.getText());
					pst.executeUpdate();
			        } 
				}
			        catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(547, 369, 89, 23);
		contentPane.add(btnDelete);
		
		txtEnterEmployeeId = new JTextField();
		txtEnterEmployeeId.setBounds(114, 311, 122, 20);
		contentPane.add(txtEnterEmployeeId);
		txtEnterEmployeeId.setColumns(10);
		
		txtEnterFirstName = new JTextField();
		txtEnterFirstName.setBounds(114, 336, 122, 20);
		contentPane.add(txtEnterFirstName);
		txtEnterFirstName.setColumns(10);
		
		txtEnterSurname = new JTextField();
		txtEnterSurname.setText("");
		txtEnterSurname.setBounds(114, 359, 122, 20);
		contentPane.add(txtEnterSurname);
		txtEnterSurname.setColumns(10);
		
		txtDesignation = new JTextField();
		txtDesignation.setBounds(114, 384, 122, 20);
		contentPane.add(txtDesignation);
		txtDesignation.setColumns(10);
		
		txtAcessRights = new JTextField();
		txtAcessRights.setBounds(363, 311, 128, 20);
		contentPane.add(txtAcessRights);
		txtAcessRights.setColumns(10);
		
		txtNationality = new JTextField();
		txtNationality.setBounds(363, 336, 128, 20);
		contentPane.add(txtNationality);
		txtNationality.setColumns(10);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(10, 314, 94, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 339, 65, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 362, 65, 14);
		contentPane.add(lblSurname);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(10, 387, 65, 14);
		contentPane.add(lblDesignation);
		
		JLabel lblAccessRights = new JLabel("Access Rights");
		lblAccessRights.setBounds(264, 314, 89, 14);
		contentPane.add(lblAccessRights);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setBounds(264, 339, 65, 14);
		contentPane.add(lblNationality);
		
		JLabel lblEnterSearchCondition = new JLabel("Enter Search Condition");
		lblEnterSearchCondition.setBounds(29, 16, 138, 14);
		contentPane.add(lblEnterSearchCondition);
		createMenu();
		
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
	
	public void search()
	{
		try{
			String search = "select ID, Name, Surname, Username, Designation, Rights, Nationality from EmployeeData where ID=?";
			PreparedStatement pst = connect.prepareStatement(search);
			pst.setString(1, txtEnterSearchCondition.getText());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		try{
			String search = "select ID, Name, Surname, Username, Designation, Rights, Nationality from EmployeeData where Name=?";
			PreparedStatement pst = connect.prepareStatement(search);
			pst.setString(1, txtEnterSearchCondition.getText());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		try{
			String search = "select ID, Name, Surname, Username, Designation, Rights, Nationality from EmployeeData where Surname=?";
			PreparedStatement pst = connect.prepareStatement(search);
			pst.setString(1, txtEnterSearchCondition.getText());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		try{
			String search = "select ID, Name, Surname, Username, Designation, Rights, Nationality from EmployeeData where Designation=?";
			PreparedStatement pst = connect.prepareStatement(search);
			pst.setString(1, txtEnterSearchCondition.getText());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		try{
			String search = "select ID, Name, Surname, Username, Designation, Rights, Nationality from EmployeeData where Rights=?";
			PreparedStatement pst = connect.prepareStatement(search);
			pst.setString(1, txtEnterSearchCondition.getText());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		try{
			String search = "select ID, Name, Surname, Username, Designation, Rights, Nationality from EmployeeData where Nationality=?";
			PreparedStatement pst = connect.prepareStatement(search);
			pst.setString(1, txtEnterSearchCondition.getText());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		try{
			String search = "select ID, Name, Surname, Username, Designation, Rights, Nationality from EmployeeData where Username=?";
			PreparedStatement pst = connect.prepareStatement(search);
			pst.setString(1, txtEnterSearchCondition.getText());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				table.setModel(DbUtils.resultSetToTableModel(rs));// class from r2sxml.jar
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
