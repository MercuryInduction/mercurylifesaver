function registration (){
	
	var username=$('#username').val();
	var password=$('#password').val();
	var repassword=$('#repassword').val();
	var contactno=$('#contactno').val();
	var inscompname=$('#inscompname').val();

	//("/registerInsurance/{uid}&{username}&{password}&{compName}/")	
	var uid = "insurance"+contactno;
	alert("all data gathered"+uid);
	
	var data=uid+"&"+username+"&"+password+"&"+contactno+"&"+inscompname;	
		ajaxRequest(updateRegistration,"registrationservice/registerInsurance/", data);		
	

}

function updateRegistration(){
	alert("You are registered !!!");
	self.location="login.html";
}

function login(){
	alert("inside login");
	var username=$('#username').val();
	alert(username);
	var password=$('#password').val();
	alert(password);

	var uid = username+password;
	alert("all data gathered"+uid);
	
	var data=uid+"&"+username+"&"+password;	
		ajaxRequest(updateLogin,"registrationservice/loginInsurance/", data);		
	

}

function updateLogin(obj){	
	
	alert("Login Successfully!!!");
	self.location="insurance.html";
}