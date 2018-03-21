/*==============================================================
 * 
 * Class:  sqlConnection                     Program: Sara Computer Centralized System
 * 
 * Author: Mohammad Amr Khan                     IBO Candidate Number: 001118-0046
 * 
 * Purpose Of Class: Establishes the MySQL connection to the online hosted MySQL database
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
import java.sql.*;

import javax.swing.*;

public class sqlConnection
{
	static Connection connect = null;
	static Statement stmt = null;
	
	public static void main (String args[]) throws SQLException
	{
		createInventoryTable();
		createPOTable();
		createEmployeeTable();
		createclientTable();
		
	}
	
	public static void createclientTable() throws SQLException //creates the client table if it does not exist
	{
		Connection connect = databaseConnector();
		PreparedStatement create = connect.prepareStatement("CREATE TABLE IF NOT EXISTS Client" +
				 "(CID VARCHAR (255) not NULL,"
				+ " Name VARCHAR (255) not NULL,"
				+ "Contact VARCHAR (255) not NULL, Telephone VARCHAR (255) not NULL,"
				+ " Type VARCHAR (255) not NULL,"
				+ " Balance double, Rebate VARCHAR (255))");
				
		create.executeUpdate();
	}
	
	public static void createEmployeeTable() throws SQLException //creates the employee table if it does not exist
	{
		Connection connect = databaseConnector();
		PreparedStatement create = connect.prepareStatement("CREATE TABLE IF Not EXISTS"
				+ " EmployeeData(ID VARCHAR (255) not NULL,"
				+ " Name VARCHAR (255) not NULL,"
				+ "Surname VARCHAR (255) not NULL, Username VARCHAR"
				+ " (255) not NULL, Password VARCHAR (255) not NULL,"
				+ " Designation VARCHAR (255) not NULL, DOB DATE , Nationality VARCHAR (255) not NULL,"
				+ "Rights VARCHAR (255) not NULL)");
				
		create.executeUpdate();
	}
	
	public static void createInventoryTable() throws SQLException //creates the inventory table if it does not exist
	{
		Connection connect = databaseConnector();
		PreparedStatement create = connect.prepareStatement("CREATE TABLE IF NOT EXISTS inventory"
				+ "(ID VARCHAR (255) not NULL,"
				+ "Description VARCHAR (255) not NULL, Type VARCHAR (255) not NULL,"
				+ " LUC INTEGER, RP INTEGER, DP INTEGER, CP INTEGER, LVP INTEGER, PRIMARY KEY(ID))");
		create.executeUpdate();
	}
	
	public static void createPOTable() throws SQLException //creates the Purchase order table if it does not exist
	{
		Connection connect = databaseConnector();
		PreparedStatement create = connect.prepareStatement("CREATE TABLE IF NOT EXISTS purchase_Order"
				+ "(Vendor VARCHAR (255) not NULL,"
				+ "PO_Number INTEGER, Amount INTEGER, Status VARCHAR (255) not NULL)");
		create.executeUpdate();
	}
	
	public static Connection databaseConnector() //returns the database connection
	{ 
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); /*Defines the class for the connection to the mysql database.
			 									sqlite-jdbc.jar files must be added to create the connection successfully. 
			 									The .jar files can be found in the Reference Libraries.*/								
			final String DB_URL = "jdbc:mysql://sql6.freemysqlhosting.net/sql6104388";
			final String DB_USER = "sql6104388";
			final String DB_PASS = "pD1NPpHLSt";
			Connection connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); //gets the connection
			return connect;	
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e); //dialog box that shows the error(s) that occurred.//
			return null;
		}
			    
	}
}


