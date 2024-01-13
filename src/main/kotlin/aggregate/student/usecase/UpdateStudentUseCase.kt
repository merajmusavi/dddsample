package aggregate.student.usecase

import aggregate.student.Student
import common.CommonUseCase
import repository.StudentRepository
import java.lang.IllegalArgumentException

class UpdateStudentUseCase(private val repo: StudentRepository) :
    CommonUseCase<UpdateStudentUseCase.SaveStudentCommand, Student> {


    override suspend fun execute(command: SaveStudentCommand): Result<Student> {
        val existingStu = repo.findStudent(command.idCard)
        return if (existingStu) {
            val stu = Student.fill(command.name, command.age, command.idCard)
            val updatedStu = Student(stu.studentName, stu.studentAge, stu.studentIdCard)
            repo.updateStudent(updatedStu)
            Result.success(updatedStu)
        } else {
            Result.failure(IllegalArgumentException("student not found"))
        }
    }

    data class SaveStudentCommand(val name: String, val age: Int, val idCard: Long)


}