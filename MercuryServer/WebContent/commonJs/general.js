
//var commonurl="http://192.168.1.2:8080/";
//var signin_url1=$('#txt_urlconfig').val();
//signin_url1="http://default-environment-x2sbbfjeua.elasticbeanstalk.com/";
var kandylogin="";
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
    }
    return "";
}
signin_url1="http://mercuryserverv2.mybluemix.net/rest/";
var commonurl=signin_url1;
alert("commonurl"+commonurl);

function getMessage(){
   // alert("device.uuid"+deviceUID);
    var data="223058";
    delete getMsg;
    //var urrl="http:/192.168.43.50:8080/Mercury_web/rest/UserInfoService/showUserData/";
    var urrl="http:/192.168.43.50:8080/Mercury_web/rest/UserInfoService/showUserData/";
    getMsg=ajaxRequest(handleResponse,urrl,data);
}
//Common ajax request
function ajaxRequest(updateFunction,requrl,data){
  alert("inside ajax request"+data);
    var url=commonurl+requrl+data;
	alert("url"+url);
    var method="GET";
    var contentType="application/json";
//  createRequest(url, "GET");
    xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) {
        xhr.open(method, url, true);
        xhr.setRequestHeader("Content-type",contentType); 

    } else if (typeof XDomainRequest != "undefined") {
        // XDomainRequest for IE.
        xhr = new XDomainRequest();
        xhr.open(method, url);
    } else {
        alert("error CORS Not Supported");
        xhr = null;
    }
	
	//xhr.onload = updateFunction();
    xhr.onload = function() {
        var text = xhr.responseText;
        alert("da"+text);
       var obj = jQuery.parseJSON(text);
       updateFunction(obj);

    };

    xhr.onerror = function() {
        alert('Request Fail');
    }; 

     xhr.send();
     xhr.onreadystatechange = handleResponse; 

}
function handleResponse() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var xmlDoc = xhr.resonseXML;
        alert(xmlDoc.getElementsByTagName("Name"));
        alert("200");

    }
    else{
    	//alert("Failed ---- Failed ---- Failed");
    	}
}



