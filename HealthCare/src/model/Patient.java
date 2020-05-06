package model;
import java.sql.*;

public class Patient {
	
	public Connection connect()
	{
		 Connection con = null;
	
		 try
		 {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb?useTimezone=true&serverTimezone=UTC","root", "");
			 //For testing
			 System.out.print("Successfully connected");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	
		 return con;
	}
	
	public String insertUser(String fname, String lname, String address, String email, String phone, String nic, String dob, String un, String pw) {
			
			String output = "";
			
			try {
				Connection con = connect();
				
				if(con == null){
					return "Error while connecting to the database";
					}
						
					// create a prepared statement
					String query = " insert into patient(`pid`,`fname`,`lname`,`address`,`email`,`phone`,`nic`,`dob`,`username`,`password`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
						
					//binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, fname);
					preparedStmt.setString(3, lname);
					preparedStmt.setString(4, address);
					preparedStmt.setString(5, email);
					preparedStmt.setString(6, phone);
					preparedStmt.setString(7, nic);
					preparedStmt.setString(8, dob);
					preparedStmt.setString(9, un);
					preparedStmt.setString(10, pw);
						
					//execute the statement
					preparedStmt.execute();
					con.close();
					
					String newUser = readUsers();
					output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
				}
			catch(Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while inserting.\"}";
				System.err.println(e.getMessage());
			}
			return output;
			}

		public String readUsers(){
			String output = "";
			
			try{
				
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
					}
				// Prepare the html table to be displayed
				output = "<table class= table  border='1'><tr><th>First Name</th>"
						+ "<th>Last name</th>"
						+ "<th>Address</th>"
						+ "<th>Email</th>"
						+ "<th>Phone no:</th>"
						+ "<th>NIC</th>"
						+ "<th>Date of birth</th>"
						+ "<th>Username</th>"
						+ "<th>Password</th>"
						+ "<th>Update</th>"
						+ "<th>Remove</th></tr>";
				
				String query = "select * from patient";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				//iteration through the rows in the result set
				while (rs.next()){
					String pid = Integer.toString(rs.getInt("pid"));
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					String address = rs.getString("address");
					String email = rs.getString("email"); 
					String phone = rs.getString("phone"); 
					String nic = rs.getString("nic"); 
					String dob = rs.getString("dob"); 
					String username = rs.getString("username"); 
					String password = rs.getString("password"); 
					
					//add into the html table
					output += "<tr><td><input id='hidItemIDUpdate' name= 'hidItemIDUpdate' type= 'hidden'  value= '" + pid + "'>" + fname + "</td>";
					output += "<td>" + lname + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + phone + "</td>";
					output += "<td>" + nic + "</td>";
					output += "<td>" + dob + "</td>";
					output += "<td>" + username + "</td>";
					output += "<td>" + password + "</td>";
					
					//buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'>" 
							+ "</td> <td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-pid='" + pid + "'>" 
							+ "</td></tr>";
					}
				con.close();
				
				//complete the html table
				output += "</table>";
				}
			catch (Exception e)
			{
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		public String updateUser(String pid, String fname, String lname, String address, String email, String phone, String nic, String dob, String un, String pw) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE patient SET fname=?,lname=?,address=?,email=?,phone=?,nic=?,dob=?,username=?,password=? WHERE pid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, fname);
				preparedStmt.setString(2,lname);
				preparedStmt.setString(3,address);
				preparedStmt.setString(4,email);
				preparedStmt.setString(5,phone);
				preparedStmt.setString(6,nic);
				preparedStmt.setString(7,dob);
				preparedStmt.setString(8,un);
				preparedStmt.setString(9,pw);
				preparedStmt.setInt(10,Integer.parseInt(pid));
				
				//execute the statement
				
				preparedStmt.execute();
				con.close();
				
				String newUser = readUsers();
				output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
				
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String deletePatient(String pid)
		{
			String output = "";
			
			try {
				Connection con = connect();
				
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				

				// create a prepared statement
				String query = "delete from patient where pid=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(pid));
				
				//execute the statement
				preparedStmt.execute();
				con.close();
				
				String newUser = readUsers();
				output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
				
			}
			catch(Exception e){
				output = "{\"status\":\"error\", \"data\":\"Error while deleting.\"}";
				System.err.println(e.getMessage());
			}
			return output;	
		}

}
