/*package com.mercury.web;



import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
 

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.annotation.WebServlet;

import org.apache.catalina.connector.Request;

import com.mercury.dataBase.connection.ConnectDataBase;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


@WebServlet("/InsertDataServlet")

public class InsertData extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
    public InsertData() {
        super();
 
    }
 
 
    private void insertToDb(HttpServletRequest request) throws IOException {

    	    ConnectDataBase ab = new ConnectDataBase();
    	    DB db=ab.connectDb();
    	    
           //boolean auth = db.authenticate(myUserName, myPassword);
		   //  System.out.println("Authentication: "+auth);
		     DBCollection setColl = db.createCollection("mycol", null);
             System.out.println("Collection created successfully");
         
             DBCollection getColl = db.getCollection("mycol");
             System.out.println("Collection mycol selected successfully");
 
            
            String fname=request.getParameter("name");
 			String city=request.getParameter("city");
 			String uid=request.getParameter("uid");
 		    String uname=request.getParameter("username");
 		    String pass=request.getParameter("password");
 		    String email=request.getParameter("email");   			
 			
 			System.out.println("name is :  "+fname);
             
             
             
             BasicDBObject doc = new BasicDBObject("Uid", uid).
             append("Username", uname).
             append("Password",pass).
             append("Email",email).
             append("Name", uname).
             append("City",city);
                
             getColl.insert(doc);                
             System.out.println("~Document inserted successfully");
             
             //showDb(response,db);
                 
	}
    
    
    private void dataProcess()
    {
    	 DB db=connectDb();
    	 
    	 DBCollection coll = db.getCollection("mycol");
         System.out.println("Collection mycol selected successfully");
    	 
    	
    	BasicDBList coordinates = new BasicDBList();
    	coordinates.put(0, -73.97);
    	coordinates.put(1, 40.77);
    	
    	 	
    	coll.insert(new BasicDBObject("name", "Central Park")
    	                .append("loc", new BasicDBObject("type", "Point").append("coordinates", coordinates))
    	                .append("category", "Parks"));

    	coordinates.put(0, -73.88);
    	coordinates.put(1, 40.78);
    	coll.insert(new BasicDBObject("name", "La Guardia Airport")
    	        .append("loc", new BasicDBObject("type", "Point").append("coordinates", coordinates))
    	        .append("category", "Airport"));

    	

    	// Find whats within 500m of my location
    	BasicDBList myLocation = new BasicDBList();
    	myLocation.put(0, -73.965);
    	myLocation.put(1, 40.769);
    	
    	DBObject myDoc = coll.findOne();
    	
    	myDoc = coll.findOne(
    	            new BasicDBObject("loc",
    	                new BasicDBObject("$near",
    	                        new BasicDBObject("$geometry",
    	                                new BasicDBObject("type", "Point")
    	                                    .append("coordinates", myLocation))
    	                             .append("$maxDistance",  500)
    	                        )
    	                )
    	            );
    	System.out.println(myDoc.get("name"));
    }
    
    public void dropDatabase()
    {
    	 ConnectDataBase ab = new ConnectDataBase();
 	     DB db=ab.connectDb();
    	 db.dropDatabase();
    	 System.out.println(" Successfully dropped ");
   	
    }
    
    
    private void showResult(HttpServletResponse response) throws IOException{   
 
    	// Get current time
        Calendar calendar = new GregorianCalendar();
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if(calendar.get(Calendar.AM_PM) == 0)
          am_pm = "AM";
        else
          am_pm = "PM";
   
        String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
      
        PrintWriter out = response.getWriter();
        String title = "Auto Refresh Header Setting";
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
        "transitional//en\">\n";
        out.println(docType +
          "<html>\n" +
          "<head><title>" + title + "</title></head>\n"+
          "<body bgcolor=\"#f0f0f0\">\n" +
          "<h1 align=\"center\">" + title + "</h1>\n" +
          "<p>Current Time is: " + CT + "</p>\n");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    
    	        insertToDb(request);
    			response.setStatus(HttpServletResponse.SC_OK);
    	    	response.setContentType("text/xml");
    	    	
    	    	String str = "abc";
    	    	response.getWriter().write(str); 
    	    	//response.getOutputStream().println("<root><data>Inserted Successfully</data></root>");
    	    	
    	    	PrintWriter out = response.getWriter();  
    	    	out.write("InsertedSuccessfully");
    	    	out.write("200");
    	    	
    	    	//response.set
    	    	
    	    //	response.getOutputStream().println("<root><data>Inserted Successfully</data></root>");
    	
        //dropDatabase();
    	insertToDb(response);
    	response.setStatus(HttpServletResponse.SC_OK);
    	response.setContentType("text/xml");
        response.getOutputStream().println("<root><data>Inserted Successfully</data></root>");
    	System.out.println();
    	

 
    }
 
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        try {
            int length = request.getContentLength();
            byte[] input = new byte[length];
            ServletInputStream sin = request.getInputStream();
            int c, count = 0 ;
            while ((c = sin.read(input, count, input.length-count)) != -1) {
                count +=c;
            }
            sin.close();
 
            String recievedString = new String(input);
            response.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
 
            Integer doubledValue = Integer.parseInt(recievedString) * 2;
 
            writer.write(doubledValue.toString());
            writer.flush();
            writer.close();
 
 
 
        } catch (IOException e) {
 
 
            try{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
                response.getWriter().close();
            } catch (IOException ioe) {
            }
        }  
        }
 
}*/