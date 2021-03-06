package com.aeolos.service;

import java.util.List;

import com.aeolos.model.Callback;
import com.aeolos.model.Employee;
import com.aeolos.model.Timeslot;

public interface IEmployeeService {
	
	Employee read(long id);
	List<Employee> store();
	Callback GetCallback(long id);
	List<Callback> GetAllCallbacks();
	Callback SaveCallback(Callback callback);
	List<Callback> GetCallbacksByDateTime(String cbdate, String cbtime);
	String DeleteCallback(long id);
	Callback UpdateCallback(long id, String notes);
	List<Timeslot> GetTimeSlot(String paramdate);
	String UpdateTimeSlot(String paramdate, String paramslot);
	String UpdateCallbackStatus(Long paramid, String paramstatus);
}
