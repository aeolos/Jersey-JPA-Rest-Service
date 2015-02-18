package com.aeolos.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.aeolos.dao.EmployeeRepo;
import com.aeolos.model.Callback;
import com.aeolos.model.Employee;

public class EmployeeService implements IEmployeeService{
 
	private EmployeeRepo repo;
	
	public EmployeeService(EmployeeRepo repo){
		this.repo = repo;
	}
	
	@Override
	public Employee read(long id) {   	
    	return repo.getEmployee(id);
    }  
    
    @Override
	public List<Employee> store() {  
    	return repo.getStoredEmployee();
    }
    
    public List<Callback> GetAllCallbacks() {
    	return repo.getAllCallbacks();
    }
    
    public List<Callback> GetCallbacksByDateTime(String cbdate, String cbtime) {
    	try {
			return repo.getCallbacksByDateTime(cbdate, cbtime);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
	@Override
	public Callback GetCallback(long id) {   ;	
    	return repo.getCallback(id);
    } 
	
	@Override
	public Callback UpdateCallback(long id, String notes) {   	
    	return repo.updateCallback(id, notes);
    } 
	
	@Override
	public String DeleteCallback(long id) {   	
    	return repo.deleteCallback(id);
    } 
	
	@Override
	public Callback SaveCallback(Callback callback) {   	
    	return repo.SaveCallback(callback);
    } 
	
}