package aggregate.student.usecase

import aggregate.student.model.command.UpdateStudentCm
import common.CommonUseCase
import repository.StudentRepository
import java.lang.IllegalArgumentException

class UpdateStudentUc(private val repo: StudentRepository) :
    CommonUseCase<UpdateStudentCm, Unit> {


    override suspend fun execute(command: UpdateStudentCm): Result<Unit> {
        val existingStu = repo.get(command.idCard)
        return if (existingStu != null) {
            val resultStu = existingStu.updateStudent(
                command.name,
                command.age
            )
            repo.update(resultStu.getOrThrow())
            Result.success(Unit)
        } else {
            Result.failure(IllegalArgumentException("student not found"))
        }
    }



}