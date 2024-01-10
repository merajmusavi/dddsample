package repository

import aggregate.student.Student

interface StudentRepository {
    fun saveStudent(stu:Student):Result<Student>
    fun findStudent(idCard: Long) :Boolean
    fun deleteStudent(idCard: Long) : Boolean
    fun updateStudent(stu:Student)
}