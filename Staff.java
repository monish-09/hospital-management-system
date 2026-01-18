package com.project1.HospitalManagementSystem;
import java.util.*;
import java.sql.*;

public class Staff {
	private static Scanner sc;
	private static Connection con;
	public Staff(Scanner sc,Connection con) {
		this.sc=sc;
		this.con=con;
	}
	public void addStaff() {
		System.out.println("Enter Staff Name:");
		String name=sc.next();
		System.out.println("Enter Gender:");
		String gender=sc.next();
		System.out.println("Enter Role:");
		String role=sc.next();
		System.out.println("Enter Staff's Salary(in Rs):");
		int salary=sc.nextInt();
		try {
			String query="INSERT INTO staff (name,gender,role,salary) VALUES (?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, gender);
			ps.setString(3, role);
			ps.setInt(4, salary);
			int rows=ps.executeUpdate();
			if(rows>0) {
				System.out.println("Staff Added Succesfully !!");
			}
			else {
				System.out.println("Falied To Add Staff !!");
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
}
	public void viewStaff() {
		try {
			String query="SELECT * FROM staff";
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			System.out.println("Staff");
			System.out.println("+-----------+------------------+---------+--------------+-----------+");
			System.out.println("| Staff Id  | Staff Name       | Gender  | Role         | Salary    |");
			System.out.println("+-----------+------------------+---------+--------------+-----------+");
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				String role=rs.getString("role");
				double salary=rs.getDouble("salary");
				System.out.printf("|%-11s|%-18s|%-9s|%-14s|%-11s|\n",id,name,gender,role,salary);
				System.out.println("+-----------+------------------+---------+--------------+-----------+");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	public void deleteStaff() {
		  System.out.println("Enter Staff Id:");
		  int id=sc.nextInt();	  
		  try {
			  String query="DELETE FROM staff WHERE id= ?";
			  PreparedStatement ps=con.prepareStatement(query);
			  ps.setInt(1, id);
			  int rows=ps.executeUpdate();
			  if(rows>0) {
				  System.out.println("Staff Deleted Succesfully");
			  }
			  else {
				  System.out.println("No Staff Found With This Id !!");
			  }
		  }
		  catch(SQLException e) {
			  System.out.println(e);
		  }
	  }
}
