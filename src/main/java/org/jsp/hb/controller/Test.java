package org.jsp.hb.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hb.dao.BatchStudentDao;
import org.jsp.hb.dao.StudntDao;
import org.jsp.hb.dao.batchDao;
import org.jsp.hb.dto.Batch;
import org.jsp.hb.dto.Student;

public class Test {
	static Scanner s = new Scanner(System.in);
	static batchDao dao = new batchDao();
	static StudntDao dao1 = new StudntDao();
	static BatchStudentDao dao2 = new BatchStudentDao();
	public static void main(String[] args) {
		boolean b = true;
		while(b) {
			System.out.println();
			System.out.println("1. Save Batch");
			System.out.println("2. Update Batch");
			System.out.println("3. Save Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Batch");
			System.out.println("6. Delete Student");
			System.out.println("7. fetch Batch By ID ");
			System.out.println("8. fetch Student By ID ");
			System.out.println("9. fetch Student By Batch ID ");
			System.out.println("10. Set Batch and Student");
			System.out.println();
			System.out.print("Choice ;");
			int n = s.nextInt();
			switch (n) {
			case 1:
				saveB();
				break;
			case 2:
				updateB();
				break;
			case 3:
				saveS();
				break;
			case 4:
				updateS();
				break;
			case 5:
				deleteB();
				break;
			case 6:
				deleteS();
				break;
			case 7:
				fetchBatchById();
				break;
			case 8:
				fetchStudentById();
				break;
			case 9:
				fetchStudentByBatchID();
				break;
			case 10:
				setBS();
				break;

			default:
				break;
			}
		}
	}
	public static void saveB() {
		System.out.println("Enter Batch Name");
		String name = s.next();
		Batch b = new Batch();
		b.setName(name);
		dao.saveB(b);
				
				
	}
	public static void updateB() {
		dao.viewAllBatch();
		System.out.println("Enter Batch id");
		int id = s.nextInt();
		Batch b = dao.fetchBacth(id);
		System.out.println("Enter Name:");
		String name = s.next();
		b.setName(name);
		dao.updateB(b);
	}
	public static void saveS() {
		System.out.println("Enter Student Name");
		String name = s.next();
		Student s = new Student();
		s.setName(name);
		dao1.saveS(s);
	}
	public static void updateS() {
		dao1.viewAllStudent();
		System.out.println("Enter Student id ");
		int id = s.nextInt();
		Student st = dao1.fetchStudentById(id);
		System.out.println("Enter Student Name");
		String name = s.next();
		st.setName(name);
		dao1.updateS(st);
	}
	public static void deleteB() {
		dao.viewAllBatch();
		System.out.println("Enter Batch Id");
		int id = s.nextInt();
		dao.deleteB(id);
		System.out.println("Deleted");
	}
	public static void deleteS() {
		dao1.viewAllStudent();
		System.out.println("Enter Student Id");
		int id = s.nextInt();
		dao1.deleteStudent(id);
		System.out.println("Deleted");
	}
	public static void fetchBatchById() {
		System.out.println("Enter Batch ID ");
		int id = s.nextInt();
		Batch b = dao.fetchBacth(id);
		dao.viewBatch(b);
	}
	public static void fetchStudentById() {
		System.out.println("Enter Student ID ");
		int id = s.nextInt();
		Student s = dao1.fetchStudentById(id);
		dao1.viewS(s);
	}
	public static void fetchStudentByBatchID() {
		System.out.println("Enter Batch ID ");
		int id = s.nextInt();
		List<Student> ss = dao1.fetchStudentByBatchID(id);
		if(ss.size()>0) {
			for(Student s : ss) {
				dao1.viewS(s);
			}
		}
	}
	public static void setBS() {
		System.out.println("Enter batch Id");
		int b_id = s.nextInt();
		System.out.println("Enter Student Id ");
		int s_id = s.nextInt();
		dao2.setBatchStudent(b_id, s_id);
	}


}
