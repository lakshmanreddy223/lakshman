package com.icm.pogeneration.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.icm.pogeneration.model.PODownload;
import com.icm.pogeneration.service.PODownloadService;
import com.icm.pogeneration.service.impl.PODownloadServiceImpl;

/**
 * @author raju
 * Servlet implementation class PODownload
 */
@WebServlet("/download")
public class PODownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PODownloadController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 
		 PODownloadService poDownloadService = null;
		 PODownload download = null;
		 
	    // String uri = request.getRequestURI();         //   "/POGeneration/download"
	    // String tkn = request.getQueryString();        //   "tkn=54835435834"
         String tokenId = request.getParameter("tkn");   //        54835435834
         /*
          * Commented By Raju
         // That will validate the uri of download link if required
         if(uri.equalsIgnoreCase("/POGeneration/download")) {
        	 
         }*/
         
         if(tokenId!=null) {
        	poDownloadService = new PODownloadServiceImpl();
        	
		    download = poDownloadService.downloadPO(tokenId);
		    if(download!=null){
			    String filename = download.getFileName();
			    String path = PODownload.getPath();
			    if(download.isStatus()) {
				   	response.setContentType("APPLICATION/OCTET-STREAM");
				    response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				    FileInputStream fileInputStream = new FileInputStream(path+"/"+ filename);
				    int i;
				    while ((i = fileInputStream.read()) != -1) {
				      out.write(i);
				    }
				    fileInputStream.close();
				    out.close();
			    }
			 }
         }else {
 			JSONObject jsonObject = new JSONObject();
 			try {
 				jsonObject.put("key", "error");
 				response.getWriter().print(jsonObject.toString());	
 			} catch (JSONException e) {
 				e.printStackTrace();
 			}
 		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
