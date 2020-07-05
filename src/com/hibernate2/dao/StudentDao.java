package com.hibernate2.dao;

import java.util.List;

import com.hibernate2.model.Student;

public interface StudentDao {
	
	void insertStudent(Student student);
	void updateStudent(Student student);
	Student getStudent(int roll);
	List<Student> getAllStudents();
	List<Student> getStudentsWithRollAndMarksGreaterThan(int roll, int marks);
	List<Object[]> getNameAndMarksOfAllStudents();
	void updateNameAndMarks(int roll, String name, int marks);
	void deleteStudent(int roll);
}
