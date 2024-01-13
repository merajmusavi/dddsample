package aggregate.student.usecase

import aggregate.student.Student
import common.CommonUseCase
import repository.StudentRepository
import java.lang.IllegalArgumentException

class DeleteStudentUseCase(private val repo: StudentRepository) :
    CommonUseCase<DeleteStudentUseCase.DeleteStudentCommand, Student> {


    override suspend fun execute(command: DeleteStudentCommand): Result<Student> {
        val existingStu = repo.findStudent(command.idCard)
        return if (existingStu) {
            repo.deleteStudent(command.idCard)
            val deletedStudent = Student.fill(command.name, command.age, command.idCard)
            return Result.success(deletedStudent)

        } else {
            Result.failure(IllegalArgumentException("student not found"))
        }
    }

    data class DeleteStudentCommand(val name: String, val age: Int, val idCard: Long)


}