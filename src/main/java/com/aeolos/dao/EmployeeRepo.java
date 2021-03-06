package com.aeolos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.aeolos.listener.LocalEntityManagerFactory;
import com.aeolos.model.Employee;
import com.aeolos.model.Callback;
import com.aeolos.model.Timeslot;

public class EmployeeRepo implements IEmployeeRepo {

	EntityManager em = LocalEntityManagerFactory.createEntityManager();

	@Override
	public Employee getEmployee(long id) {
		try {
			Employee test = new Employee();
			test = em.find(Employee.class, id);
			return test;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Employee> getStoredEmployee() {
		try {
			Query query = em.createStoredProcedureQuery("GetAllEmployees",
					Employee.class);
			List<Employee> result = query.getResultList();
			return result;
		} finally {
			em.close();
		}
	}

	@Override
	public Employee save(Employee employee) {
		try {
			em.persist(employee);
			em.flush();
			return employee;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Callback> getAllCallbacks() {
		try {
			Query query = em.createStoredProcedureQuery("GetAllCallbacks",
					Callback.class);
			List<Callback> result = query.getResultList();
			return result;
		} finally {
			em.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Callback> getCallbacksByDateTime(String cbdate, String cbtime) {
		try {
			em.getEntityManagerFactory().getCache().evictAll();
			StoredProcedureQuery query = em.createStoredProcedureQuery(
					"GetCallbacksByDateTime", Callback.class);
			query.registerStoredProcedureParameter("paramDate", String.class,
					ParameterMode.IN);
			query.registerStoredProcedureParameter("paramTime", String.class,
					ParameterMode.IN);
			query.setParameter("paramDate", cbdate);
			query.setParameter("paramTime", cbtime);
			query.execute();
			List<Callback> result = query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Callback getCallback(long id) {
		try {
			em.getEntityManagerFactory().getCache().evictAll();
			Callback result = new Callback();
			result = em.find(Callback.class, id);
			return result;
		} finally {
			em.close();
		}
	}

	@Override
	public String deleteCallback(long id) {
		try {
			em.getTransaction().begin();
			StoredProcedureQuery query = em
					.createStoredProcedureQuery("DeleteCallback");
			query.registerStoredProcedureParameter("paramid", Long.class,
					ParameterMode.IN);
			query.setParameter("paramid", id);
			query.execute();
			em.getTransaction().commit();
			return "Success";
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.getCause();
			return e.toString();
		} finally {
			em.close();
		}
	}

	@Override
	public Callback updateCallback(long id, String notes) {
		try {
			em.getTransaction().begin();
			StoredProcedureQuery query = em
					.createStoredProcedureQuery("UpdateCallbackNotes");
			query.registerStoredProcedureParameter("paramid", Long.class,
					ParameterMode.IN);
			query.registerStoredProcedureParameter("paramnotes", String.class,
					ParameterMode.IN);
			query.setParameter("paramid", id);
			query.setParameter("paramnotes", notes);
			query.execute();
			em.getTransaction().commit();

			em.getTransaction().begin();
			Callback result = new Callback();
			result = em.find(Callback.class, id);
			em.getTransaction().commit();
			return result;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.getCause();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Callback SaveCallback(Callback callback) {
		try {
			em.getTransaction().begin();
			em.persist(callback);
			em.flush();
			em.getTransaction().commit();
			return callback;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Timeslot> getTimeslot(String paramdate) {
		try {
			em.getEntityManagerFactory().getCache().evictAll();
			em.getTransaction().begin();
			StoredProcedureQuery query = em.createStoredProcedureQuery(
					"GetTimeslotByDate", Timeslot.class);
			query.registerStoredProcedureParameter("paramdate", String.class,
					ParameterMode.IN);
			query.setParameter("paramdate", paramdate);
			query.execute();
			em.getTransaction().commit();
			List<Timeslot> result = query.getResultList();
			return result;
		} finally {
			em.close();
		}
	}

	@Override
	public String updateTimeSlot(String date, String slot) {
		try {
			em.getTransaction().begin();
			StoredProcedureQuery query = em
					.createStoredProcedureQuery("UpdateTimeSlot");
			query.registerStoredProcedureParameter("fieldname", String.class,
					ParameterMode.IN);
			query.registerStoredProcedureParameter("paramdate", String.class,
					ParameterMode.IN);
			query.setParameter("fieldname", slot);
			query.setParameter("paramdate", date);
			query.execute();
			em.getTransaction().commit();
			return "Success";
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.getCause();
			return "Error";
		} finally {
			em.close();
		}
	}

	@Override
	public String updateCallbackStatus(Long paramid, String paramstatus) {
		try {
			em.getEntityManagerFactory().getCache().evictAll();
			em.getTransaction().begin();
			StoredProcedureQuery query = em
					.createStoredProcedureQuery("UpdateCallbackStatus");
			query.registerStoredProcedureParameter("paramid", Long.class,
					ParameterMode.IN);
			query.registerStoredProcedureParameter("paramstatus", String.class,
					ParameterMode.IN);
			query.setParameter("paramid", paramid);
			query.setParameter("paramstatus", paramstatus);
			query.execute();
			em.getTransaction().commit();
			return "Success";
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.getCause();
			return "Error";
		} finally {
			em.close();
		}
	}

}
