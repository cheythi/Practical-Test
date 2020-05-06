$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	 	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

	// Form validation-------------------
	 var status = validateItemForm();
	 if (status != true)
	  {
		  $("#alertError").text(status);
		  $("#alertError").show();
	  return;
	  }
	 
	// If valid-------------------------

	 var type = ($("#hiddenSave").val() == "") ? "POST" : "PUT";
	 
	 $.ajax(
			 {
			  url : "PatientAPI",
			  type : type,
			  data : $("#patientForm").serialize(),
			  dataType : "text",
			  complete : function(response, status)
			  {
				  onPatientSaveComplete(response.responseText, status);
			  }
			 });
	}); 

	function onPatientSaveComplete(response, status)
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			 $("#alertSuccess").text("Successfully saved.");
			 $("#alertSuccess").show();
			 
			 $("#divPatientGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
		else if (status == "error")
		{
			 $("#alertError").text("Error while saving.");
			 $("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
		}
		
		$("#hiddenSave").val("");
		$("#patientForm")[0].reset();

	}
	 

	$(document).on("click", ".btnRemove", function(event)
		{
			$.ajax(
			{
				 url : "PatientAPI",
				 type : "DELETE",
				 data : "pid=" + $(this).data("pid"),
				 dataType : "text",
				 complete : function(response, status)
				 {
					 onPatientDeleteComplete(response.responseText, status);
				 }
			});
		});
	
	function  onPatientDeleteComplete(response, status)
	{
		if (status == "success")
		 {
			 var resultSet = JSON.parse(response);
			 if (resultSet.status.trim() == "success")
			 {
				 $("#alertSuccess").text("Successfully deleted.");
				 $("#alertSuccess").show();
				 
				 $("#divPatientGrid").html(resultSet.data);
			 } else if (resultSet.status.trim() == "error")
			 {
				 $("#alertError").text(resultSet.data);
				 $("#alertError").show();
			 }
		 } 
		else if (status == "error")
		 {
			 $("#alertError").text("Error while deleting.");
			 $("#alertError").show();
		 } 
		else
		 {
			 $("#alertError").text("Unknown error while deleting..");
			 $("#alertError").show();
		 } 
	}


//UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
		 $("#hiddenSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
		 $("#fname").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#lname").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#address").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#phone").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#nic").val($(this).closest("tr").find('td:eq(5)').text());
		 $("#dob").val($(this).closest("tr").find('td:eq(6)').text());
		 $("#username").val($(this).closest("tr").find('td:eq(7)').text());
		 $("#password").val($(this).closest("tr").find('td:eq(8)').text());
	});

//CLIENT-MODEL================================================================
function validateItemForm()
{
	// FIrst name
	if ($("#fname").val().trim() == "")
	{
		return "Enter first name";
	}
	var fn = $("#fname").val().trim();
	if ($.isNumeric(fn))
	{
		return "Enter valid first name";
	}
	// Last name
	if ($("#lname").val().trim() == "")
	{
		return "Enter last name";
	}
	var ln = $("#lname").val().trim();
	if ($.isNumeric(ln))
	{
		return "Enter valid lastname";
	}
	//Address
	if ($("#address").val().trim() == "")
	{	
		return "Enter address";		
	}
	//Email
	if ($("#email").val().trim() == "")
	{	
		return "Enter email";		
	}
	var em = $("#email").val().trim();
	var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(!regex.test(em)) 
	{
		    return "Enter valid email address";
	}
	//phone
	if ($("#phone").val().trim() == "")
	{	
		return "Enter phone number";		
	}
	var ph = $("#phone").val().trim();
	if (!$.isNumeric(ph))
	{
		return "Enter valid phone number";
	}
	var ph1 = $("#phone").val().trim();
	var reg = /^\d{10}$/;
	if (!reg.test(ph1))
	{
		return "Invalid phone number"
	}
	//nic
	if ($("#nic").val().trim() == "")
	{	
		return "Enter NIC";		
	}
	var nic = $("#nic").val().trim();
	var regex = /^[0-9]{9}[vVxX]$/;
	if(!regex.test(nic)) 
	{
		    return "Enter valid NIC";
	}
	//dob
	if ($("#dob").val().trim() == "")
	{	
		return "Enter Birth day";		
	}
	//user name
	if ($("#username").val().trim() == "")
	{	
		return "Provide username";		
	}
	//Password
	if ($("#password").val().trim() == "")
	{	
		return "Provide password";		
	}
	
	 return true
}

