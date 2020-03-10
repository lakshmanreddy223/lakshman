package com.icm.pogeneration.controller;

import java.util.Date;

import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.simple.parser.ParseException;

import com.icm.pogeneration.model.POGeneration;
import com.icm.pogeneration.model.ResponsePO;
import com.icm.pogeneration.service.POGenerationService;
import com.icm.pogeneration.service.impl.POGenerationServiceImpl;
import com.icm.pogeneration.util.CustomDate;
import com.icm.pogeneration.util.POGenerateValidate;

/**
 * 
 * @author Hemant
 *
 */
@Path("/webservices")
public class POGenerationController {
	    
	    @POST
		@Path("/generate")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response generate(POGeneration poGeneration, @Context UriInfo ui)throws ParseException{
	    System.out.println("controller");
	    POGenerationService poGenerationService = null;		
		Response response=null;	
        boolean check = POGenerateValidate.validate(poGeneration.getPonumber(), poGeneration.getDate(), poGeneration.getPotype(), poGeneration.getName()/*, poGeneration.getAddress()*/,poGeneration.getCity(),
        		poGeneration.getState(),poGeneration.getPincode(),poGeneration.getAllocationList());
		if(check) { 
				//poGeneration.setDate(CustomDate.getDate(new Date()));			 
				  try {
						poGenerationService = new POGenerationServiceImpl();
						POGeneration result= poGenerationService.generatePO(poGeneration);
						if(result.isStatus()) {
							String tokenURL = ui.getBaseUri().toString().replace("rest/", "")+"download?tkn="+result.getToken();
							
							result.setDownloadUrl(tokenURL);
							
							ResponsePO responses = new ResponsePO();
							System.out.println(result.getPonumber());
							responses.setPonumber(result.getPonumber());
							responses.setTokenURL(tokenURL);
							responses.setFilename(result.getFileName());
							responses.setTotalamount(result.getTotalamount());
							return Response.status(200).entity(responses).build();
						}
						response = Response.status(400).entity("Invalid Input").build();
				  }catch(Exception e) {
					  response = Response.status(404).entity("Error Occured").build();
					  e.printStackTrace();
				  }
		}    
		    response = Response.status(400).entity("Invalid Input").build();
			return response;
	    }
	    @POST
	    @Path("/update")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response update(POGeneration poGeneration, @Context UriInfo ui) throws ParseException{
	    
	    POGenerationService poGenerationService = null;
    	Response response=null;
        boolean check = POGenerateValidate.validate(poGeneration.getPonumber(), poGeneration.getDate(), poGeneration.getPotype(), poGeneration.getName(),/* poGeneration.getAddress(),*/poGeneration.getCity(),
        		poGeneration.getState(),poGeneration.getPincode(), poGeneration.getAllocationList());
	    	if(check) {
					try {
						poGenerationService = new POGenerationServiceImpl();
						POGeneration result= poGenerationService.updatePO(poGeneration);
						if(result.isStatus()) {
							String tokenURL = ui.getBaseUri().toString().replace("rest/", "")+"download?tkn="+result.getToken();
							result.setDownloadUrl(tokenURL);
                            ResponsePO responses = new ResponsePO();
							
							responses.setPonumber(result.getPonumber());
							responses.setTokenURL(tokenURL);
							responses.setFilename(result.getFileName());
							responses.setTotalamount(result.getTotalamount());
							response = Response.status(200).entity(responses).build();
							return response;		
						}
					}catch(Exception e) {
						response = Response.status(404).entity("Error Occured").build();
						e.printStackTrace();
					}
             }
	    	response = Response.status(400).entity("Invalid Input").build();
			return response;
	    }	    	    
}
