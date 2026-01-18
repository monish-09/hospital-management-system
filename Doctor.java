package com.project1.HospitalManagementSystem;

import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	private Connection con;
	private Scanner sc;
  public Doctor(Connection con,Scanner sc) {
	  this.con=con;
	  this.sc=sc;
  }
  public void addDoctor() {
	  System.out.print("Enter Doctor Name:");
	  String name=sc.next();
	 
	  System.out.print("Enter Specillization:");
	  String specilization=sc.next();
	  try {
		  String query="INSERT INTO patients(name,specilzation) VALUES (?,?)";
		  PreparedStatement ps=con.prepareStatement(query);
		  ps.setString(1,name);
		  ps.setString(2,specilization);
		  int rows=ps.executeUpdate();
		  if(rows>0) {
			  System.out.println("Doctor Added Succesfully !!");
		  }
		  else {
			  System.out.println("Failed To Add Doctor !!");
		  }
	  }
	  catch(SQLException e) {
		  System.out.println(e);
	  }
	  
  }
  public void viewDoctor() {
	  String query="SELECT * FROM doctors";
	  try {
		  PreparedStatement ps=con.prepareStatement(query);
		  ResultSet rs=ps.executeQuery();	
		  System.out.println("Doctors:");
		  System.out.println("+------------+-----------------+-----------------+");
		  System.out.println("| Doctor Id  | Name            | Specilization   |");
		  System.out.println("+------------+-----------------+-----------------+");
		  while(rs.next()) {
			  int id=rs.getInt("id");
			  String name=rs.getString("name");
			  String specilization=rs.getString("specilization");
			  System.out.printf("| %-11s| %-15s | %-15s |\n",id,name,specilization);
			  System.out.println("+------------+-----------------+-----------------+");
		  }
	  }
	  catch(SQLException e) {
		  System.out.println(e);
	  }
  }
  public void deleteDoctor() {
	  System.out.println("Enter Doctor id:");
	  int id=sc.nextInt();	  
	  try {
		  String query="DELETE FROM doctor WHERE id= ?";
		  PreparedStatement ps=con.prepareStatement(query);
		  ps.setInt(1, id);
		  int rows=ps.executeUpdate();
		  if(rows>0) {
			  System.out.println("Doctor Deleted Succesfully");
		  }
		  else {
			  System.out.println("No Doctor Found With This Id !!");
		  }
	  }
	  catch(SQLException e) {
		  System.out.println(e);
	  }
  }
  public boolean getDoctorById(int id) {
	  String query="SELECT * FROM doctors WHERE id= ?";
	  try {
		  PreparedStatement ps=con.prepareStatement(query);
		  ps.setInt(1, id);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()) {
			  return true;
		  }
		  else {
			  return false;}
	  }
	  catch(SQLException e) {
		  System.out.println(e);
	  }
	  return false;
  }
  
}
