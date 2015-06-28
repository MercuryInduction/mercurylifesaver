package com.mercury.dataBase.connection;



import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.annotation.WebServlet;


import com.mongodb.DB;
import com.mongodb.MongoClient;


@WebServlet("/ConnectServlet")

public class ConnectDataBase extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
    public ConnectDataBase() {
        super();
 
    }
 
    public DB connectDb(){	
    	try{   
   		     // To connect to mongodb server
             MongoClient mongoClient = new MongoClient("10.204.32.162" , 27017 );
             // Now connect to your databases
             DB db = mongoClient.getDB( "test" );
   		     System.out.println("Connect to database successfully");   
   		     return db;	
           }
    	   catch(Exception e){
   	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
   	       }
		return null;  
        }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    	response.setStatus(HttpServletResponse.SC_OK);
    	response.setContentType("text/xml");
    	
    	DB db=connectDb();	
    	if(db!=null){
            response.getOutputStream().println("<root><data>Success</data></root>");
    	}
    	else
    	{
    		   response.getOutputStream().println("<root><data>Failed</data></root>");
    	}
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
 
}