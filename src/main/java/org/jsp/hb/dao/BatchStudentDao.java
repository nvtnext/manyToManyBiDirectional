package org.jsp.hb.dao;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.hb.dto.Batch;
import org.jsp.hb.dto.Student;

public class BatchStudentDao {
	EntityManager m = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction t = m.getTransaction();
	
	public void setBatchStudent(int b_id, int s_id) {
		Batch b = m.find(Batch.class, b_id);
		Student s = m.find(Student.class, s_id);
		if(b!=null && s!=null) {
			b.getStudents().add(s);
			s.getBatches().add(b);
			m.persist(s);
			t.begin();
			t.commit();
		}
	}

}
