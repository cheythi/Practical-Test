package com;

import model.Patient;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Patients")
public class PatientService {
	
	Patient patientObj = new Patient();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return patientObj.readUsers();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("fname") String fname,
			@FormParam("lname") String lname,
			@FormParam("address") String address,
			@FormParam("email") String email,
			@FormParam("phone") String phone,
			@FormParam("nic") String nic,
			@FormParam("dob") String dob,
			@FormParam("username") String un,
			@FormParam("password") String pw)
		{
			String output = patientObj.insertUser(fname, lname, address, email, phone, nic, dob, un, pw);
			return output;
	} 
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
		// Convert the input string to a JSON object 
		JsonObject patientObject = new JsonParser().parse(userData).getAsJsonObject();
		
		// Read the values from the JSON object 
		String pid = patientObject.get("pid").getAsString(); 
		String fname = patientObject.get("fname").getAsString();
		String lname = patientObject.get("lname").getAsString();
		String address = patientObject.get("address").getAsString();
		String email =patientObject.get("email").getAsString();
		String phone =patientObject.get("phone").getAsString();
		String nic =patientObject.get("nic").getAsString();
		String dob =patientObject.get("dob").getAsString();
		String un =patientObject.get("username").getAsString();
		String pw =patientObject.get("password").getAsString();
		
		String output = patientObj.updateUser(pid, fname, lname, address, email, phone, nic, dob, un, pw);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String userData)
	{  
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String pid = doc.select("pid").text();  
		
		 String output = patientObj.deletePatient(pid);  
		 
		 return output; 
		 } 
}
