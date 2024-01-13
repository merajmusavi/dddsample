package repository

import aggregate.student.Student

interface StudentRepository {
    fun saveStudent(stu:Student):Result<Student>
    fun findStudent(idCard: Long) :Student
    fun deleteStudent(idCard: Long)
    fun updateStudent(stu:Student)

    fun get(idCard: Long):Student?
}