package com.project1.HospitalManagementSystem;

import java.sql.*;
import java.util.*;

public class HospitalManagementSystem {
 private static final String url="jdbc:mysql://localhost:3306/hospital";
 private static final String  username="root";
 private static final String password="Kaif@123";
 public static void main(String[] args) {
 try {
	 Class.forName("com.mysql.cj.jdbc.Driver");
 }
 catch(ClassNotFoundException e) {
	 System.out.println(e);
 }
 
 Scanner sc=new Scanner(System.in);
 try {
	 Connection con=DriverManager.getConnection(url,username,password);
	 Patient patient=new Patient(con,sc);
	 Doctor doctor=new Doctor(con,sc);
	 Staff staff=new Staff(sc,con);
	 Medicine medicine=new Medicine(con,sc);
	 System.out.println("HOSPITAL MANAGEMENT SYSTEM");
	 while(true) {
		 System.out.println("\n-----------------------------\n");
		 System.out.println("1. Patient Management");
		 System.out.println("2. Doctor Management");
		 System.out.println("3. Staff Management");
		 System.out.println("4. Medicine Management");
		 System.out.println("5. Book Appointment"); 
		 System.out.println("6. Exit");
		 System.out.println("Enter Choice");
		 int ch=sc.nextInt();
		 switch(ch) {
		 case 1:
			  System.out.println("Patient Management");
			  System.out.println("1. Add Patient");
			  System.out.println("2. View Patient");
			  System.out.println("3. Delete Patient");
			  System.out.println("Enter Choice");
			  int pch=sc.nextInt();
			  if(pch==1) patient.addPatient();
			  else if(pch==2) patient.viewPatient();
			  else if(pch==3) patient.deletePatient();
			  else System.out.println("Invalid Choice");
			 break;
		 case 2:
			  System.out.println("Doctor Management");
			  System.out.println("1. Add Doctor");
			  System.out.println("2. View Doctor");
			  System.out.println("3. Delete Doctor");
			  System.out.println("Enter Choice");
			  int dch=sc.nextInt();
			  if(dch==1) doctor.addDoctor();
			  else if(dch==2) doctor.viewDoctor();
			  else if(dch==3) doctor.deleteDoctor();
			  else System.out.println("Invalid Choice");
			  			 break;
		 case 3:
			  System.out.println("Staff Management");
			  System.out.println("1. Add Staff");
			  System.out.println("2. View Staff");
			  System.out.println("3. Delete Staff");
			  System.out.println("Enter Choice");
			  int sch=sc.nextInt();
			  if(sch==1) staff.addStaff();
			  else if(sch==2) staff.viewStaff();
			  else if(sch==3) staff.deleteStaff();
			  else System.out.println("Invalid Choice");
			 break;
		 case 4:
			 System.out.println("Medicine Management");
			  System.out.println("1. Add Medicine");
			  System.out.println("2. View Medicines");
			  System.out.println("3. Delete Medicine");
			  System.out.println("Enter Choice");
			  int mch=sc.nextInt();
			  if(mch==1) medicine.addMedicine();
			  else if(mch==2) medicine.viewMedicine();
			  else if(mch==3) medicine.deleteMedicine();
			  else System.out.println("Invalid Choice");
			 break;
		 case 5:
			 bookAppointment(patient,doctor,sc,con);
			 break;
		 case 6:
			 //exit
			 System.out.println("THANK YOU FOR USING HOSPITAL MANAGEMENT SYSTEM !!");
			 return;
		 default:
			 System.out.println("Enter Valid Choice:");
		 }
	 }
 }
 catch(SQLException e) {
	 System.out.println(e);
 }
}
 public static void bookAppointment(Patient patient,Doctor doctor,Scanner sc,Connection con) {
	 System.out.println("Enter Patient Id:");
	 int patientId=sc.nextInt();
	 System.out.println("Enter Doctor Id:");
	 int doctorId=sc.nextInt();
	 System.out.println("Enter Apointment Date (YYYY-MM-DD):");
	 String apointmentDate=sc.next();	
	 if(patient.getPatientById(patientId)&&doctor.getDoctorById(doctorId)) {
		 if(checkDoctorAvivablity(doctorId,apointmentDate,con)) {
			 String query="INSERT INTO appointments(patient_id,doctor_id,appointment_date) VALUES (?,?,?)";
			  try {
			 PreparedStatement ps=con.prepareStatement(query);
			 ps.setInt(1, patientId);
			 ps.setInt(2, doctorId);
			 ps.setString(3, apointmentDate);
			 int rows=ps.executeUpdate();
			 if(rows>0) {
				 System.out.println("Apppointment Booked");
				 }
			 else {
				 System.out.println("Failed To Book.");
			 }
			 }
			  catch(SQLException e) {
				  System.out.println(e);
			  }
			 }
		 else {
			 System.out.println("Doctor Not Avilable!!");
		 }
		 }
		 else {
			 System.out.println("Eitehr Patient or Doctor Does'nt Exist !!");
		 }
	 
	 
 

 }
 public static boolean checkDoctorAvivablity(int doctorId,String appointmentDate,Connection con) {
	 String query="SELECT COUNT(*) FROM appointments WHERE doctor_id= ? AND appointment_date= ?";
	 try {
		 PreparedStatement ps=con.prepareStatement(query);
		 ps.setInt(1, doctorId);
		 ps.setString(2,appointmentDate);
		 ResultSet rs=ps.executeQuery();	
		 if(rs.next()) {
			 int count=rs.getInt(1);
			 if(count==0) {
				 return true;
			 }
			 else {
				 return false;
			 }
		 }
	 }
	 catch(SQLException e) {
		 System.out.println(e);
	 }
	 return false;
 }
 }
