package com.project1.HospitalManagementSystem;
import java.sql.*;
import java.util.*;

public class Patient {
	private Connection con;
	private Scanner sc;
  public Patient(Connection con,Scanner sc) {
	  this.con=con;
	  this.sc=sc;
  }
  public void addPatient() {
	  System.out.print("Enter Patient Name:");
	  String name=sc.next();
	  System.out.print("Enter Patient's Age:");
	  int age=sc.nextInt();
	  System.out.print("Enter Patient's Gender:");
	  String gender=sc.next();
	  try {
		  String query="INSERT INTO patients(name,age,gender) VALUES (?,?,?)";
		  PreparedStatement ps=con.prepareStatement(query);
		  ps.setString(1,name);
		  ps.setInt(2, age);
		  ps.setString(3,gender);
		  int rows=ps.executeUpdate();
		  if(rows>0) {
			  System.out.println("Patient Added Succesfully !!");
		  }
		  else {
			  System.out.println("Failed To Add Patient !!");
		  }
	  }
	  catch(SQLException e) {
		  System.out.println(e);
	  }
	  
  }
  public void viewPatient() {
	  String query="SELECT * FROM patients";
	  try {
		  PreparedStatement ps=con.prepareStatement(query);
		  ResultSet rs=ps.executeQuery();	
		  System.out.println("Patients:");
		  System.out.println("+------------+-----------------+--------+--------+");
		  System.out.println("| Patient Id | Name            | Age    | Gender |");
		  System.out.println("+------------+-----------------+--------+--------+");
		  while(rs.next()) {
			  int id=rs.getInt("id");
			  String name=rs.getString("name");
			  int age=rs.getInt("age");
			  String gender=rs.getString("gender");
			  System.out.printf("|%-12s|%-17s|%-8s|%-8s|\n",id,name,age,gender);
			  System.out.println("+------------+-----------------+--------+--------+");
		  }
	  }
	  catch(SQLException e) {
		  System.out.println(e);
	  }
  }
  public void deletePatient() {
	  System.out.println("Enter Patient id:");
	  int id=sc.nextInt();	  
	  try {
		  String query="DELETE FROM patient WHERE id= ?";
		  PreparedStatement ps=con.prepareStatement(query);
		  ps.setInt(1, id);
		  int rows=ps.executeUpdate();
		  if(rows>0) {
			  System.out.println("Patient Deleted Succesfully");
		  }
		  else {
			  System.out.println("No Patient Found With This Id !!");
		  }
	  }
	  catch(SQLException e) {
		  System.out.println(e);
	  }
  }
  public boolean getPatientById(int id) {
	  String query="SELECT * FROM patients WHERE id= ?";
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
