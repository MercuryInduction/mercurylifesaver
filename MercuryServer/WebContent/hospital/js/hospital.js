function registration (){
	alert("inside registration");
	var username=$('#UserName').val();
	alert(username);
	var password=$('#Password').val();
	alert(password);
	var repassword=$('#RePassword').val();
	alert(repassword);
	var contactnumber=$('#ContactNumber').val();
	alert(contactnumber);
	var hospitalname=$('#HospitalName').val();
	alert(hospitalname);
	var hospitalloc=$('#HospitalLoc').val();
	alert(hospitalloc);
	//var data="/registerHospital/{uid}""&{username}&{password}&{hospialName}&{hospitalLoc}/";	
	var uid = "hospital"+contactnumber;
	alert("all data gathered"+uid);
	
	var data=uid+"&"+username+"&"+password+"&"+contactnumber+"&"+hospitalname+"&"+hospitalloc;	
		ajaxRequest(updateRegistration,"registrationservice/registerHospital/", data);		
	

}

function updateRegistration(){
	alert("You are registered !!!");
	self.location="login.html";
}

function login(){
	alert("inside login");
	var username=$('#UserName').val();
	alert(username);
	var password=$('#Password').val();
	alert(password);

	var uid = username+password;
	alert("all data gathered"+uid);
	
	var data=uid+"&"+username+"&"+password;	
		ajaxRequest(updateLogin,"registrationservice/loginHospital/", data);		
	

}

function updateLogin(obj){	
	
	alert("Login Successfully!!!");
	self.location="Hospital.html";
}