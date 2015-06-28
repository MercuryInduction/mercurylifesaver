package com.mercury.google;





import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class UploadData {

  private static String CLIENT_ID = "808966689374-5p47e7cl93j2bjtvf6id3ir52nrta994.apps.googleusercontent.com";
  private static String CLIENT_SECRET = "DymBbVzYCAcN0GRbHFOrulGm";

  private static String REDIRECT_URI = "https://www.example.com/oauth2callback";
  
  public void cloudUpload() throws IOException {
	  
	  
    HttpTransport httpTransport = new NetHttpTransport();
    JsonFactory jsonFactory = new JacksonFactory();
   
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
        .setAccessType("online")
        .setApprovalPrompt("auto").build();
    
    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
    System.out.println("Please open the following URL in your browser then type the authorization code:");
    System.out.println("\n\n\n\n Url is   " + url);
    

    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
  
    //String code = br.readLine();
    String code = "4/MoT5SKcAGstyb8Dj-G5Kv_cPt8oIC8jsnjl_F7ytbkI.4guDmHvTog4ecp7tdiljKKZBG-_vkwI";
    
    System.out.println("After code "+code);
    
    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

    System.out.println("\nBefore code \n");
    
    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
    
    System.out.println("alll code \n");
    //Create a new authorized API client
    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();

    System.out.println("dsds code \n");
    
    //Insert a file  
    File body = new File();
    body.setTitle("My document");
    body.setDescription("A test document");
    body.setMimeType("text/plain");
    
    java.io.File fileContent = new java.io.File("document.txt");
    FileContent mediaContent = new FileContent("text/plain", fileContent);

    File file = service.files().insert(body, mediaContent).execute();
    System.out.println("File ID: " + file.getId());
   
  }
  
  public  void openDownloadUrl(String url)
  {
	//String url = "http://www.google.com";
	    String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
 
	try{
 
	    if (os.indexOf( "win" ) >= 0) {
 
	        // this doesn't support showing urls in the form of "page.html#nameLink" 
	        rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);
 
	    } else if (os.indexOf( "mac" ) >= 0) {
 
	        rt.exec( "open " + url);
 
            } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {
 
	        // Do a best guess on unix until we get a platform independent way
	        // Build a list of browsers to try, in this order.
	        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
	       			             "netscape","opera","links","lynx"};
 
	        // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
	        StringBuffer cmd = new StringBuffer();
	        for (int i=0; i<browsers.length; i++)
	            cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
 
	        rt.exec(new String[] { "sh", "-c", cmd.toString() });
 
           } else {
                return;
           }
       }catch (Exception e){
	    return;
       }
      return;		
}
  
  
  public List<File> retrieveAllFiles() throws IOException {
	  
	  
	    HttpTransport httpTransport = new NetHttpTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
	   
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
	        .setAccessType("online")
	        .setApprovalPrompt("auto").build();
	    
	    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
	    System.out.println("Please open the following URL in your browser then type the authorization code:");
	    System.out.println("\n\n\n\n Url is   " + url);
	    

	    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	  
	    //String code = br.readLine();
	    String code = "4/G-fRrd7WiVUwG8Qv2UzbKDd60BpZ1wyRDAtxoGAmswE.wuD74Ww0ZwIXcp7tdiljKKbpXpjwkwI";
	    
	    System.out.println("After code "+code);
	    
	    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

	    System.out.println("\nBefore code \n");
	    
	    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
	    
	    System.out.println("alll code \n");
	    //Create a new authorized API client
	    Drive services = new Drive.Builder(httpTransport, jsonFactory, credential).build();
	  
	    List<File> result = new ArrayList<File>();
	    Files.List request = services.files().list();

	    do {
	      try {
	        FileList files = request.execute();

	        result.addAll(files.getItems());
	        request.setPageToken(files.getNextPageToken());
	      } catch (IOException e) {
	        System.out.println("An error occurred: " + e);
	        request.setPageToken(null);
	      }
	    } while (request.getPageToken() != null &&
	             request.getPageToken().length() > 0);
       
	    return result;
	    
	    /*
	    for(Iterator<File> iterator = result.iterator(); iterator.hasNext();) {
	    	File x = iterator.next();
	    	
	    	System.out.println("\nFileId : "+x.getId());
	    	//do some stuff
	    	}
*/	    
	    
	  }

  
  
  
  public  void downloadFile() throws IOException {
	  
	  
	    HttpTransport httpTransport = new NetHttpTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
	   
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
	        .setAccessType("online")
	        .setApprovalPrompt("auto").build();
	    
	    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
	    System.out.println("Please open the following URL in your browser then type the authorization code:");
	    System.out.println("\n\n\n\n Url is   " + url);
	    

	    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	  
	    //String code = br.readLine();
	    String code = "4/hR4-mNT9l6BqPpyTF715ji9YcJ12ZqqxG38VHt5KR6M.4nLzKc1OOL0Scp7tdiljKKaLh5jwkwI";
	    
	    System.out.println("After code "+code);
	    
	    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

	    System.out.println("\nBefore code \n");
	    
	    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
	    
	    System.out.println("alll code \n");
	    //Create a new authorized API client
	    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();

	  /*  File body = new File();
	    body.setTitle("My document");
	    body.setDescription("A test document");
	    body.setMimeType("text/plain");
	    
	    java.io.File fileContent = new java.io.File("document.txt");
	    FileContent mediaContent = new FileContent("text/plain", fileContent);
	  
	  
	    String downloadUrl = body.getExportLinks().get("text/plain");
	    System.out.println("downloadurl: "+downloadUrl);
	  */ 
	  
	      
	     List<File> files = retrieveAllFiles();
	     for(Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
		    	File x = iterator.next();
		    	
		    	  File file1 = service.files().get(x.getId()).execute();
		    	 
			      String downloadUrl1 = file1.getWebContentLink();
			      
			      System.out.println("DownloadUrl : "+downloadUrl1);
			      openDownloadUrl(downloadUrl1);
		    	
		    	
		    	System.out.println("\nFileId : "+x.getId());
		    	//do some stuff
		    	}
	    
	      
/*	      File file1 = service.files().get("0ByznGzxu3w6NLV9rZm9Gbk42SXM").execute();
 
	      String downloadUrl1 = file1.getWebContentLink();
	      
	      System.out.println("DownloadUrl : "+downloadUrl1);
	      openDownloadUrl(downloadUrl1); // it will open and download the files      
*/	      
	      
	    /*  System.out.println("Title: " + file1.getTitle());
	      System.out.println("Description: " + file1.getDescription());
	      System.out.println("MIME type: " + file1.getMimeType());
	    */
	    
	    
/*	    if (file1.getDownloadUrl() != null && file1.getDownloadUrl().length() > 0) {
	      try {
	        HttpResponse resp =
	            service.getRequestFactory().buildGetRequest(new GenericUrl(file1.getDownloadUrl()))
	                .execute();
	        return resp.getContent();
	      } catch (IOException e) {
	        // An error occurred.
	        e.printStackTrace();
	        return null;
	      }
	    } else {
	      // The file doesn't have any content stored on Drive.
	      return null;
	    }*/
	  }
}