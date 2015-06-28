package com.mercury.web;



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

import com.mercury.dataBase.connection.ConnectDataBase;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


@WebServlet("/ShowDataServlet")

public class ShowData extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
    public ShowData() {
        super();
 
    }
    
    

    public void dropDatabase()
    {
    	 ConnectDataBase ab = new ConnectDataBase();
 	     DB db=ab.connectDb();
    	 db.dropDatabase();
    	 System.out.println(" Successfully dropped ");
   	
    }
    
    public void showDb(HttpServletResponse response) throws IOException{   
   	 		
        ConnectDataBase conn = new ConnectDataBase();
    	DB db=conn.connectDb();
        DBCollection getColl = db.getCollection("globalStatus");
        System.out.println("Collection mycol selected successfully");
 
        String data="";                
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/json");
            //    response.getOutputStream().println("<root><data>raktim</data></root>");        
        DBCursor cursor = getColl.find();
        int i=1;
        while (cursor.hasNext()) { 
            data+=cursor.next().toString();
            data+="\n";
            i++;
           }
        System.out.println("Collection mycol displayed successfully");
        response.getOutputStream().println(data);
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
    	showDb(response);
    	//dropDatabase();
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