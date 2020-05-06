<%@ page import="model.Patient" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
		<script src="Components/patients.js"></script>
		
		<meta charset="ISO-8859-1">
		<title>User management</title>
	</head>
	<body>
	<div class="bg-light text-dark">
		<div class="container">
			<div Class="row">
				<div class="col">
					<h1>User management</h1>
					 		<form id="patientForm" name="patientForm" method="post" action="managePatient.jsp">
						  		<b>First name:</b> <input id="fname" name="fname" type="text" class="form-control" placeholder="Enter firstname" ><br>
						  		<b>	Last name:</b> <input id="lname" name="lname" type="text" class="form-control" placeholder="Enter lastname"><br>
						 		<b>	Address:</b> <input id="address" name="address" type="text" class="form-control" placeholder="Enter address"><br>
								<b>	Email:</b> <input id="email" name="email" type="text" class="form-control" placeholder="Enter email"><br>
								<b>	Phone number:</b> <input id="phone" name="phone" type="text" class="form-control" placeholder="Enter phone"><br>
								<b>	NIC:</b> <input id="nic" name="nic" type="text" class="form-control" placeholder="Enter nic"><br>
								<b>	Birthday:</b> <input id="dob" name="dob" type="text" class="form-control" placeholder="Enter birthday"><br>
								<b>	User name:</b> <input id="username" name="username" type="text" class="form-control" placeholder="Enter username"><br>
								<b>	Password:</b> <input id="password" name="password" type="text" class="form-control" placeholder="Enter password"><br> 
						  		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
							 	<input type="hidden" id="hiddenSave" name="hiddenSave" value="">
						 	</form>
						 	<div id="alertSuccess" class="alert alert-success">
						 		<% out.print(session.getAttribute("statusMsg")); %>
						 	</div>
						 	<div id="alertError" class="alert alert-success">
						 		<% out.print(session.getAttribute("statusMsg")); %>
						 	</div>
					 <br>
					 <%
					 		Patient patientObj = new Patient();
					 		out.print(patientObj.readUsers());
					 %>
				</div>
			</div>
			</div>
		</div>
	</body>
</html>