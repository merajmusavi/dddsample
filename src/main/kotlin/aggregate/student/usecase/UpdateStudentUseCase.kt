package aggregate.student.usecase

import aggregate.student.Student
import aggregate.student.model.command.UpdateStudentCommand
import common.CommonUseCase
import repository.StudentRepository
import java.lang.IllegalArgumentException

class UpdateStudentUseCase(private val repo: StudentRepository) :
    CommonUseCase<UpdateStudentCommand, Unit> {


    override suspend fun execute(command: UpdateStudentCommand): Result<Unit> {
        val existingStu = repo.get(command.idCard)
        return if (existingStu != null) {
            val resultStu = existingStu.updateStudent(
                command.name,
                command.age
            )
            repo.updateStudent(resultStu.getOrThrow())
            Result.success(Unit)
        } else {
            Result.failure(IllegalArgumentException("student not found"))
        }
    }



}