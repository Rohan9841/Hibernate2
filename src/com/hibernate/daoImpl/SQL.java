package com.hibernate.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import com.hibernate2.dao.StudentDao;
import com.hibernate2.model.Student;
import com.hibernate2.utility.HibernateUtility;

public class SQL implements StudentDao {

	@Override
	public void insertStudent(Student student) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.persist(student);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
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
			SQLQuery query = session.createSQLQuery("select * from Student_table");
			query.addEntity(Student.class);
			students = query.list();
			System.out.println("All students successfully listed");
			return students;
		} catch (Exception ex) {
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
			SQLQuery query = session.createSQLQuery("SELECT * FROM Student_table where roll > ? and marks > ?");
			query.setInteger(0, roll);
			query.setInteger(1, marks);
			query.addEntity(Student.class);
			students = query.list();
			System.out.println("All students successfully listed");
			return students;
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return students;
		}
	}

	@Override
	public List<Object[]> getNameAndMarksOfAllStudents() {
		Session session = null;
		Transaction transaction = null;
		List<Object[]> objects = null;
		
		try {
			session = HibernateUtility.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			SQLQuery query = session.createSQLQuery("select name, marks from student_table");
			query.addScalar("name", StandardBasicTypes.STRING);
			query.addScalar("marks",StandardBasicTypes.INTEGER);
			objects = query.list();
			System.out.println("Name and marks successfully listed");
			return objects;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return objects;
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
//			Query query = session.createQuery("update Student set name = ?, marks = ? where roll = ?");
			SQLQuery query = session.createSQLQuery("update student_table set name = ?, marks = ? where roll = ?");
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
			SQLQuery query = session.createSQLQuery("delete from student_table where roll = :sRoll");
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
