function registration (){

	var username=$('#username').val();
	var password=$('#password').val();
	var repassword=$('#repassword').val();
	var dob=$('#dob').val();
	var mobile=$('#mobile').val();
	var address=$('#address').val();
	var pin=$('#pin').val();
	var insno=$('#insno').val();
	var inscomp=$('#inscomp').val();

	//("/registerEndUser/{uid}&{username}&{password}&{mob}&{address}&{pin}&{insuranceNo}&{insureCompName}/")
	var uid = "eu"+mobile;
	alert("all data gathered"+uid);
	
	var data=uid+"&"+username+"&"+password+"&"+mobile+"&"+address+"&"+pin+"&"+insno+"&"+inscomp;	
		ajaxRequest(updateRegistration,"registrationservice/registerEndUser/", data);		
	

}

function updateRegistration(){
	alert("You are registered !!!");
	self.location="login.html";
}

function login(){
	alert("inside login");
	var username=$('#username').val();
	var password=$('#password').val();

	var uid = username+password;
	alert("all data gathered"+uid);
	
	var data=uid+"&"+username+"&"+password;	
		ajaxRequest(updateLogin,"registrationservice/loginEndUser/", data);		
	

}

function updateLogin(obj){	
	
	alert("Login Successfully!!!");
	self.location="enduser.html";
}