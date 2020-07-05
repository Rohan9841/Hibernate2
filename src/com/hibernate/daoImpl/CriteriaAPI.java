package com.hibernate.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import com.hibernate2.dao.StudentDao;
import com.hibernate2.model.Student;
import com.hibernate2.utility.HibernateUtility;

public class CriteriaAPI implements StudentDao {

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
		// TODO Auto-generated method stub

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
			Criteria criteria = session.createCriteria(Student.class);
			Criterion criterion = Restrictions.eq("roll", roll);
			criteria.add(criterion);
			student = (Student) criteria.uniqueResult();
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
			Criteria criteria = session.createCriteria(Student.class);
			students = criteria.list();
			System.out.println("All students successfully listed");
			return students;
		} catch (Exception e) {
			e.printStackTrace();
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
			Criteria criteria = session.createCriteria(Student.class);
			Criterion criterion1 = Restrictions.gt("roll", roll);
			Criterion criterion2 = Restrictions.gt("marks", marks);
			Criterion criterion3 = Restrictions.and(criterion1,criterion2);
			criteria.add(criterion3);
			students = criteria.list();
			System.out.println("All students successfully listed");
			return students;

		} catch (Exception e) {
			e.printStackTrace();
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
			Criteria criteria = session.createCriteria(Student.class);
			Projection projection1 = Projections.property("name");
			Projection projection2 = Projections.property("marks");
			ProjectionList pList = Projections.projectionList();
			pList.add(projection1);
			pList.add(projection2);
			criteria.setProjection(pList);
			objects = criteria.list();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteStudent(int roll) {
		// TODO Auto-generated method stub

	}

}
