package com.mercury.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mercury.dataBase.connection.ConnectDataBase;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * Servlet implementation class DeleteData
 */
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void deleteFromDb(HttpServletResponse response){
    	
    	ConnectDataBase conn = new ConnectDataBase();
    	DB db = conn.connectDb();
    	
    	 DBCollection getColl = db.getCollection("mycol");
         System.out.println("Collection mycol selected successfully");
    	
    	
         DBCollection getColl1 = db.getCollection("userdatacomplete_a1");
         getColl1.drop();
         
         
    	DBObject doc = getColl.findOne(); //get first document
    	getColl.remove(doc);
    	System.out.println("Collection mycol deleted successfully");
    	
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		deleteFromDb(response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
