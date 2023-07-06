package org.jsp.hb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hb.dto.Batch;
import org.jsp.hb.dto.Student;

public class StudntDao {
	EntityManager m = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction t = m.getTransaction();
	
	
	public Student updateS(Student s) {
		m.merge(s);
		t.begin();
		t.commit();
		return s;
	}
	public Student fetchStudentById(int id) {
		return m.find(Student.class, id);
	}
	public List<Student> fetchStudentByBatchID(int id ){
		Query q = m.createQuery("select b.students from Batch b where b.id=?1");
		q.setParameter(1, id);
		return q.getResultList();
	}
	public void deleteStudent(int id) {
		Student s = m.find(Student.class, id);
		for(Batch b : s.getBatches()) {
			b.getStudents().remove(s);
		}
		s.getBatches().clear();
		m.merge(s);
		m.remove(s);
		t.begin();
		t.commit();
		
		
	}
	public void viewS(Student s ) {
		System.out.println(s.getId());
		System.out.println(s.getName());
		System.out.println("- - - - - - - - - - - ");
	}
	public void viewAllStudent() {
		Query q = m.createQuery("select s from Student s");
		List<Student> ss = q.getResultList();
		if(ss.size()>0) {
			for(Student s : ss) {
				viewS(s);
			}
		}
				
	}
	public void saveS(Student s) {
		// TODO Auto-generated method stub
		m.persist(s);
		t.begin();
		t.commit();
		
		
	}
	

}
