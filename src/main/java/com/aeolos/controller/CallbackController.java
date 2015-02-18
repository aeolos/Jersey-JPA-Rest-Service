package com.aeolos.controller;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;  
import javax.ws.rs.POST;
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.Produces;  
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;  

import com.aeolos.dao.EmployeeRepo;
import com.aeolos.model.Callback;
import com.aeolos.service.EmployeeService;

@Path("/callback") 
public class CallbackController {
	
	private EmployeeRepo repo = new EmployeeRepo();
	private EmployeeService service = new EmployeeService(repo);
		
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Callback> store() {  
    	return service.GetAllCallbacks();
    }
    
    
    @GET  
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Callback> read(@QueryParam("paramdate") String cbdate, @QueryParam("paramtime") String cbtime) {   	
    	try {
			List<Callback> callbacks = service.GetCallbacksByDateTime(cbdate, cbtime);
			return callbacks;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    @GET
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)  
    public Callback updateCallback(@QueryParam("id") Long id, @QueryParam("note") String note) {  
    	return service.UpdateCallback(id, note);
    }
    
    @GET  
    @Produces(MediaType.APPLICATION_JSON)  
    @Path("{id}")  
    public Callback read(@PathParam("id") long id) {   	
    	return service.GetCallback(id);
    } 
    
    @GET  
    @Produces(MediaType.APPLICATION_JSON)  
    @Path("/delete/{id}")  
    public String delete(@PathParam("id") long id) {   	
    	return service.DeleteCallback(id);
    } 
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)  
    public Callback save(
    		@FormParam("employeenumber") String employeenumber,
    		@FormParam("customer") String customer,
    		@FormParam("mobile") String mobile,
    		@FormParam("landline") String landline,
    		@FormParam("cbtype") String cbtype,
    		@FormParam("cbreason") String cbreason,
    		@FormParam("cbdate") String cbdate,
    		@FormParam("cbtime") String cbtime
    		) {  
    	Callback callback = new Callback();
    	callback.setEmployeenumber(employeenumber);
    	callback.setCustomer(customer);
    	callback.setMobile(mobile);
    	callback.setLandline(landline);
    	callback.setCbtype(cbtype);
    	callback.setCbreason(cbreason);
    	callback.setCbdate(cbdate);
    	callback.setCbtime(cbtime);
    	return service.SaveCallback(callback);
    }
   
}
