package org.jsp.hb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hb.dto.Batch;
import org.jsp.hb.dto.Student;

public class batchDao {
	EntityManager m = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction t = m.getTransaction();
	
	public Batch saveB(Batch b) {
		m.persist(b);
		t.begin();
		t.commit();
		return b;
	}
	public Batch updateB(Batch b) {
		m.merge(b);
		t.begin();
		t.commit();
		return b;
	}
	public void deleteB(int id) {
		Batch b = m.find(Batch.class, id);
		for(Student s : b.getStudents()) {
			s.getBatches().remove(b);
		}
		b.getStudents().clear();
		m.merge(b);
		m.remove(b);
		t.begin();
		t.commit();
	}
	public Batch fetchBacth(int id) {
		return m.find(Batch.class, id);
		
	}
	public List<Batch> fetchBatchByStudentID(int s_id){
		Query q = m.createQuery("select s.batches from Batch b where s.id=?1");
		q.setParameter(1, s_id);
		return q.getResultList();
	}
	public void viewBatch(Batch b) {
		System.out.println(b.getId());
		System.out.println(b.getName());
		System.out.println("- - - - - - - - -");
	}
	public void viewAllBatch(){
		Query q = m.createQuery("select b from Batch b ");
		List<Batch> bs = q.getResultList();
		if(bs.size()>0) {
			for(Batch b : bs) {
				viewBatch(b);
			}
		}
	}

}
