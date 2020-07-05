package com.hibernate2.test;

import java.util.List;

import com.hibernate.daoImpl.CriteriaAPI;
import com.hibernate2.dao.StudentDao;
import com.hibernate2.model.Student;

public class Test {
	
	public static void main(String[] args) {
		
//		StudentDao hqlDao = new HQL();
//		StudentDao sqlDao = new SQL();
		
//		Student student = new Student("Ram",74);
//		hqlDao.insertStudent(student);
		
//		Student student = hqlDao.getStudent(1);
//		student.setMarks(88);
//		hqlDao.updateStudent(student);
		
//		System.out.println(hqlDao.getAllStudents());
//		System.out.println(hqlDao.getStudentsWithRollAndMarksGreaterThan(1,70));
//		List<Object[]> objectArrays = hqlDao.getNameAndMarksOfAllStudents();
//		
//		for(Object[] objectArray : objectArrays) {
//			for(Object object : objectArray) {
//				System.out.print(object + " ");
//			}
//			System.out.println();
//		}
		
//		hqlDao.updateNameAndMarks(2, "newName", 100);
//		hqlDao.deleteStudent(2);
		
//		---------------------------------SQL--------------------------------------
//		Student student = new Student("Shyam",88);
//		sqlDao.insertStudent(student);
//		Student student = sqlDao.getStudent(1);
//		student.setMarks(66);
//		sqlDao.updateStudent(student);
		
//		List<Student> students = sqlDao.getAllStudents();
//		for(Student student: students) {
//			System.out.println(student);
//		}
		
//		List<Student> students = sqlDao.getStudentsWithRollAndMarksGreaterThan(1, 88);
//		for(Student student: students) {
//			System.out.println(student);
//		}
		
//		List<Object[]> objectArrays = sqlDao.getNameAndMarksOfAllStudents();
//		for(Object[] objArray : objectArrays) {
//			for(Object object : objArray) {
//				System.out.print(object + " ");
//			}
//			System.out.println();
//		}
		
//		sqlDao.updateNameAndMarks(1, "new Ram", 95);
//		sqlDao.deleteStudent(1);
		
		
//		_______________________________CRITERIA API------------------------------------------------------
		
	
		StudentDao criteriaApi = new CriteriaAPI();
//		Student student = new Student("Rohan",94);
//		criteriaApi.insertStudent(student);
//		System.out.println(criteriaApi.getStudent(1));
//		List<Student> students = criteriaApi.getAllStudents();
//		for(Student student : students) {
//			System.out.println(student);
//		}
		
//		List<Student> students = criteriaApi.getStudentsWithRollAndMarksGreaterThan(1, 55);
//		for(Student student : students) {
//			System.out.println(student);
//		}
		
		List<Object[]> results = criteriaApi.getNameAndMarksOfAllStudents();
		for(Object[] objArray: results) {
			for(Object object : objArray) {
				System.out.print(object + " ");
			}
			System.out.println();
		}
		
	}
}
