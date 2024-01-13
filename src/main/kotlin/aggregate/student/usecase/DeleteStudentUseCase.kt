package aggregate.student.usecase

import aggregate.student.Student
import aggregate.student.model.command.DeleteStudentCommand
import common.CommonUseCase
import repository.StudentRepository
import java.lang.IllegalArgumentException

class DeleteStudentUseCase(private val repo: StudentRepository) :
    CommonUseCase<DeleteStudentCommand, Unit> {


    override suspend fun execute(command: DeleteStudentCommand): Result<Unit> {
        val existingStu = repo.get(command.idCard)
        return if (existingStu!= null) {
            repo.deleteStudent(command.idCard)
            return Result.success(Unit)

        } else {
            Result.failure(IllegalArgumentException("student not found"))
        }
    }



}