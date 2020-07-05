package com.hibernate.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate2.dao.StudentDao;
import com.hibernate2.model.Student;
import com.hibernate2.utility.HibernateUtility;

public class HQL implements StudentDao {

	@Override
	public void insertStudent(Student student) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.save(student);
			transaction.commit();
			System.out.println("Student inserted");
		} catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
		}

	}

	@Override
	public void updateStudent(Student student) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.update(student);
			transaction.commit();
			System.out.println("Student Updated");
		} catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
		}
	}

	@Override
	public Student getStudent(int roll) {
		Session session = null;
		Transaction transaction = null;
		Student student = null;
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			student = (Student) session.load(Student.class, roll);
			transaction.commit();
			System.out.println("Student Returned");
			return student;
		} catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
			return student;
		}
	}

	@Override
	public List<Student> getAllStudents() {
		Session session = null;
		Transaction transaction = null;
		List<Student> students = null;
		
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("from Student");
			students = query.list();
			System.out.println("All students successfully listed");
			return students;
		}catch(Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
			return students;
		}
		
	}

	@Override
	public List<Student> getStudentsWithRollAndMarksGreaterThan(int roll, int marks) {
		Session session = null;
		Transaction transaction = null;
		List<Student> students = null;
		
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("from Student where roll > ? and marks > ?");
			query.setInteger(0, roll);
			query.setInteger(1, marks);
			students = query.list();
			System.out.println("All students successfully listed");
			return students;
		}catch(Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
			return students;
		}
	}

	@Override
	public List<Object[]> getNameAndMarksOfAllStudents() {
		Session session = null;
		Transaction transaction = null;
		List<Object[]> objectArrays = null;
		
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("select name, marks from Student");
			objectArrays = query.list();
			System.out.println("All object arrays successfully listed");
			return objectArrays;
		}catch(Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
			return objectArrays;
		}
	}

	@Override
	public void updateNameAndMarks(int roll, String name, int marks) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("update Student set name = ?, marks = ? where roll = ?");
			query.setString(0, name);
			query.setInteger(1, marks);
			query.setInteger(2, roll);
			int result = query.executeUpdate();
			transaction.commit();
			System.out.println("Name and marks successfully updated");
		}catch(Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
		}
		
	}

	@Override
	public void deleteStudent(int roll) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("delete Student where roll = :sRoll");
			query.setInteger("sRoll", roll);
			int result = query.executeUpdate();
			transaction.commit();
			System.out.println("Student successfully deleted");
		}catch(Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
		}
		
	}
	
	
	
}
