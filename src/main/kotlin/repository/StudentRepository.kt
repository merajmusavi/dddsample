package repository

import aggregate.student.Student

interface StudentRepository {
    fun save(stu:Student):Result<Student>//save
    fun delete(idCard: Long)
    fun update(stu:Student)
    fun get(idCard: Long):Student?
}