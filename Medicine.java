package com.project1.HospitalManagementSystem;
import java.util.*;
import java.sql.*;

public class Medicine {
	private static Scanner sc;
	private static Connection con;
	public Medicine(Connection con,Scanner sc) {
		this.con=con;
		this.sc=sc;
	}
	public void addMedicine() {
		System.out.println("Enter Medicine Name:");
		String name=sc.next();
		System.out.println("Enter Category:");
		String category=sc.next();
		System.out.println("Enter Quantity:");
		int quantity=sc.nextInt();
		System.out.println("Enter Price (in Rs):");
		int price=sc.nextInt();
		System.out.println("Enter Expiry Date(YYYY-MM-DD):");
		String expiry_date=sc.next();
		try {
			String query="INSERT INTO medicine (name,category,quantity,price,expiry_date) VALUES (?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, category);
			ps.setInt(3, quantity);
			ps.setInt(4, price);
			ps.setString(5, expiry_date);
			int rows=ps.executeUpdate();
			if(rows>0) {
				System.out.println("Medicine Added Succesfully !!");
			}
			else {
				System.out.println("Falied To Add Medicine !!");
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	public  void viewMedicine() {
		try {
			String query="SELECT * FROM medicine";
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			System.out.println("Medicine");
			System.out.println("+--------+----------------+----------+----------+-----------+---------------------+-------------+");
			System.out.println("| Med_id | Medicine_Name  | Category | Quantity | Price(Rs) |   Added_on          | Expiry_date |");
			System.out.println("+--------+----------------+----------+----------+-----------+---------------------+-------------+");
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String category=rs.getString("category");
				int quantity=rs.getInt("quantity");
				double price=rs.getDouble("price");
				String added_on=rs.getString("added_on");
				String expiry_date=rs.getString("expiry_date"); 
				System.out.printf("|%-8s|%-16s|%-10s|%-10s|%-11s|%-21s|%-13s|\n",id,name,category,quantity,price,added_on,expiry_date);
				System.out.println("+--------+----------------+----------+----------+-----------+---------------------+-------------+");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		}
		public void deleteMedicine() {
			  System.out.println("Enter Medicine Name:");
			  String name=sc.next();	  
			  try {
				  String query="DELETE FROM medicine WHERE name= ?";
				  PreparedStatement ps=con.prepareStatement(query);
				  ps.setString(1, name);
				  int rows=ps.executeUpdate();
				  if(rows>0) {
					  System.out.println("Medicine Deleted Succesfully");
				  }
				  else {
					  System.out.println("No Medicine Found !!");
				  }
			  }
			  catch(SQLException e) {
				  System.out.println(e);
			  }
		  }
	
}
